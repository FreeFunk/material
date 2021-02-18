// pages/databank/caseDetail/caseDetail.js
var template = require('../../template/template.js');
const commservice = require('../../../utils/commservice');
const app = getApp();
var current = 0;//选中的case所在的位置;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        towards: '',
        //底部导航栏
        selectNav: {
            pitchOnMaterial: true,
            pitchOnFind: false,
            pitchOnMine: false, //发现和 我的 变成未选中；
        },
        //正在下载;
        downloadIngFlag: false,
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        imgIndex: 0,
        duration: 0,
        allDataReady: false,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var selectCase = app.globalData.selectCase;
        var selectCardList = app.globalData.selectCardList;
        console.log(selectCase);
        var swiperHeight = 750 * selectCase.imageHeight / selectCase.imageWidth
        this.setData({
            swiperHeight: swiperHeight,
            selectCase: selectCase,
            selectCaseList: selectCardList,
            previewImgList: [this.data.imageHost + selectCase.orgImageUrl],//预览图的list
        });
        for (var i = 0; i < selectCardList.length; i++) {
            if (selectCase.id == selectCardList[i].id) {
                current = i;
            }
        }
        this.setData({
            current: current,
            indexNum: current,
        })
    },
    //收藏;
    collectSuccess: function () {
        var that = this;
        var isAddRes = 0;
        if (this.data.selectCase.isCollection == "0") {
            isAddRes = 1;
        }
        this.data.selectCase.isCollection = isAddRes;
        this.setData({
            selectCase: this.data.selectCase
        });
        var collectionObj = {
            collectionCls: 'CASE', //收藏分类(案例：CASE，色卡：COLOR_MAP)
            relationId: this.data.selectCase.id, //关联分类ID
            collectionTitle: this.data.selectCase.caseName, //收藏标题
            imageUrl: this.data.selectCase.showImageUrl, //封面图
            isAdd: isAddRes, //1是要添加，2是要删除；
        };
        // 调用收藏接口;
        commservice.request({
            url: app.globalData.appHost + '/materialUserCollection/addCollection.jsn',
            method: 'POST',
            data: collectionObj,
            success: function (res) {
                console.log(res);
                if (res.data.success) {
                    if (isAddRes == 1) {
                        //收藏成功
                        wx.showToast({
                            title: '收藏成功',
                        })
                    } else {
                        //取消收藏成功
                        wx.showToast({
                            title: '已取消',
                        })
                    }
                    var pages = getCurrentPages();
                    var prevPage = pages[pages.length - 2];   //上一页
                    prevPage.setData({
                        currentCaseId: that.data.selectCase.id,
                        isCol: isAddRes
                    })
                    that.setData({
                        selectCase: that.data.selectCase
                    });
                } else { }
            },
            fail: function (res) { }
        });

    },

    //正在下载
    downloadImg: function () {
        //触发函数
        var that = this;
        that.setData({
            downloadIngFlag: true
        });
        var imgUrl = this.data.imageHost + this.data.selectCase.orgImageUrl;
        wx.downloadFile({
            url: imgUrl, //需要下载的图片url
            success: function (res) { //成功后的回调函数
                wx.saveImageToPhotosAlbum({ //保存到本地
                    filePath: res.tempFilePath,
                    success(res) {
                        that.setData({
                            downloadIngFlag: false
                        })
                        wx.showToast({
                            title: '下载成功',
                        })
                    },
                    fail: function (err) {
                        that.setData({
                            downloadIngFlag: false
                        })
                        if (err.errMsg === "saveImageToPhotosAlbum:fail auth deny") {
                            wx.openSetting({
                                success(settingdata) {
                                    console.log(settingdata)
                                    if (settingdata.authSetting['scope.writePhotosAlbum']) {
                                        console.log('获取权限成功，给出再次点击图片保存到相册的提示。')
                                    } else {
                                        console.log('获取权限失败，给出不给权限就无法正常使用的提示')
                                    }
                                }
                            })
                        }
                    }
                })
            }, fail: function (err) {
                console.log(err);
            }
        });
    },

    /**
     * 图片预览
     * @param {} event 
     */
    previewImages: function (event) {
        var orgimg = this.data.imageHost + event.currentTarget.dataset.orgimg;
        var previewImageList = [orgimg];
        wx.previewImage({
            current: orgimg, // 当前显示图片的http链接
            urls: previewImageList // 需要预览的图片http链接列表
        })
    },

    /**
     * 底部导航栏界面跳转
     * @param {*} event 
     */
    navBottomJump: function (event) {
        template.navBottomJump(event);
    },

    /**
     * 监听左右滑动相关;
     */
    touchStart(e) {
        //如果已经是0了就不再请求了
        if (this.data.duration != 300) {
            this.setData({
                duration: 300,
            });
        }
        // console.log(e)
        this.setData({
            "startX": e.changedTouches[0].clientX,
            "startY": e.changedTouches[0].clientY
        });
    },
    touchEnd(e) {
        let x = e.changedTouches[0].clientX;
        let y = e.changedTouches[0].clientY;
        var turnFlag = this.getTouchData(x, y, this.data.startX, this.data.startY);
        var currentnum = this.data.indexNum;
        //如果向右滑动加载左侧已经有的数据;
        if (turnFlag == 'left') {
            //加载右侧的数据;
            if (currentnum == this.data.selectCaseList.length - 1 && !this.data.allDataReady) {
                var that = this;
                var pageObj = app.globalData.caseObj;
                if ((pageObj.currentPage >= pageObj.totalPage && pageObj.totalPage >= 0) || pageObj.isWorking) {
                    that.setData({
                        allDataReady: true
                    });
                    return;
                } else {
                    pageObj.currentPage++;
                    return commservice.getPageObjectByCondition(pageObj).then(function (list) {
                        console.log(list);
                        if (list.length == 0) {
                            that.setData({
                                allDataReady: true
                            });
                        }
                        that.setData({
                            selectCaseList: that.data.selectCaseList.concat(list),
                        });
                    });
                }
            } else if (currentnum == this.data.selectCaseList.length - 1 && this.data.allDataReady) {
                wx.showToast({
                    title: '暂无更多数据',
                    icon: 'none'
                })
            }
        }
    },
    //当swiper-item滑动的时候变化current的值;
    changeIndexNum: function (e) {
        var current = e.detail.current;
        this.setData({
            indexNum: current
        });
        var swiperHeight = 750 * this.data.selectCaseList[current].imageHeight / this.data.selectCaseList[current].imageWidth
        //设置文字、下载、收藏
        this.setData({
            selectCase: this.data.selectCaseList[current],
            swiperHeight: swiperHeight,
        });
    },
    /***
     * 判断用户滑动;
     * 左滑还是右滑;
     * 向左滑加载右侧list;
     * 向右滑加载左侧list;
     */
    getTouchData: (endX, endY, startX, startY) => {
        let turn = "";
        if (endX - startX > 50 && Math.abs(endY - startY) < 50) {      //右滑
            turn = "right";
        } else if (endX - startX < -50 && Math.abs(endY - startY) < 50) {   //左滑
            turn = "left";
        }
        return turn;
    }
})