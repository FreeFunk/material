// pages/databank/caseDetail/caseDetail.js
var template = require('../../template/template.js');
const commservice = require('../../../utils/commservice');
const app = getApp();
var discoverObj = {};
var leftHeight = 0;
var rightHeight = 0;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        ktxWindowHeight: getApp().globalData.ktxWindowHeight,
        //底部导航栏
        selectNav: {
            pitchOnMaterial: false,
            pitchOnFind: true,
            pitchOnMine: false, //发现和 我的 变成未选中；
        },
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        leftList: [],
        rightList: [],
        similarRecommendationList: [],
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        //获取当前的数据;
        var selectCase = app.globalData.selectCase;
        console.log(selectCase);
        this.setData({
            previewImgList: [this.data.imageHost + selectCase.orgImageUrl], //原图list;
            selectCase: selectCase,
        });
        this.similarRecommendation(selectCase.caseClsId);
    },
    calculateHeight: function () {
        //1.计算图片的高度;
        var imgHeight = 750 * this.data.selectCase.imageHeight / this.data.selectCase.imageWidth;
        //2.收藏文字的高度;
        var collectDivHeight = 200;
        //3.同类推荐的高度（文字）
        var sameRecmondDivHeight = 80;
        //4.得到最高的高度;leftHeight/rightHeight;
        var waterFullHeight = 0;
        if (leftHeight > rightHeight) {
            waterFullHeight = leftHeight;
        } else {
            waterFullHeight = rightHeight;
        }
        var pageHeight = 0;
        pageHeight = imgHeight + collectDivHeight + sameRecmondDivHeight + waterFullHeight - 200;
        this.setData({
            pageHeight: pageHeight
        });
    },
    //下载
    //正在下载
    downloadIng: function () { //触发函数
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
                                        wx.showToast({
                                            title: '授权成功！',
                                            icon: 'none'
                                        })
                                        console.log('获取权限成功，给出再次点击图片保存到相册的提示。')
                                    } else {
                                        wx.showToast({
                                            title: '获取权限失败，授权之后方可使用',
                                            icon: 'none'
                                        })
                                        console.log('获取权限失败，给出不给权限就无法正常使用的提示')
                                    }
                                }
                            })
                        }
                    }
                })
            }
        });
    },
    // 瀑布流1列变2列;
    waterFullList: function (list) {
        var leftList = this.data.leftList;
        var rightList = this.data.rightList;
        for (var i = 0; i < list.length; i++) {
            if (list[i].imageWidth == 0 || list[i].imageHeight == 0) {
                continue
            } else {
                var imgHeight = 368 * list[i].imageHeight / list[i].imageWidth;
                if (rightHeight < leftHeight) {
                    rightList.push(list[i]);
                    rightHeight += imgHeight;
                } else {
                    leftList.push(list[i]);
                    leftHeight += imgHeight;
                }
            }
        }
        this.calculateHeight();
        this.setData({
            leftList: leftList,
            rightList: rightList,
        });
    },
    /**
     * 同类推荐;
     */
    similarRecommendation: function (caseClsId) {
        leftHeight = 0;
        rightHeight = 0;
        this.setData({
            leftList: [], //左侧的数据;
            rightList: [], //左侧的数据;
        });
        commservice.generatePageObj(discoverObj, app.globalData.appHost + '/materialCase/findListPage.jsn', 9);
        discoverObj.param = {
            'queryObj.type': 'CASE',
            "queryObj.caseClsId": caseClsId
        };
        commservice.getNextPageObjectByCondition(discoverObj).then((res) => {
            console.log(res);
            this.setData({
                similarRecommendationList: res
            });
            this.waterFullList(res);
        });
    },
    onShow: function () {
        let pages = getCurrentPages();
        let currPage = pages[pages.length - 1]; //当前页
        var selectCase = currPage.data.selectCase;
        this.setData({
            selectCase: selectCase
        });
    },
    nextPage: function () {
        var that = this;
        var pageObj = discoverObj;
        if ((pageObj.currentPage >= pageObj.totalPage && pageObj.totalPage >= 0) || pageObj.isWorking) {
            return;
        } else {
            pageObj.currentPage++;
            return commservice.getPageObjectByCondition(pageObj).then(function (list) {
                console.log(list);
                that.waterFullList(list);
            });
        }
    },
    /**
     * 图片预览
     * @param {} event 
     */
    previewImages: function (event) {
        var imageUrl = event.currentTarget.dataset.imageurl;
        var previewImgList = event.currentTarget.dataset.previewimglist;
        wx.previewImage({
            current: getApp().globalData.imageHost + imageUrl, // 当前显示图片的http链接
            urls: previewImgList // 需要预览的图片http链接列表
        })
    },
    /**
     * 点击同类推荐
     */
    clickSimilarRecommendation: function (event) {
        var item = event.currentTarget.dataset.item;
        console.log(item);
        app.globalData.selectCase = item;
        wx.redirectTo({
            //加载页面地址
            url: '/pages/discover/discoverCase/discoverCase',
            success: function (res) {
                // 隐藏导航栏加载框
                wx.hideNavigationBarLoading();
            }
        })
    },
    onUnload: function () {
        var pages = getCurrentPages();
        var prevPage = pages[pages.length - 2]; //上一页
        prevPage.setData({
            selectCase: this.data.selectCase,
        })
    },
    //收藏;
    collectSuccess: function () {
        //变化收藏还是取消收藏;
        var that = this;
        var isAddRes = 0;
        if (this.data.selectCase.isCollection == 0) {
            isAddRes = 1;
        }
        this.data.selectCase.isCollection = isAddRes;
       
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
                    that.setData({
                        selectCase: that.data.selectCase
                    });
                } else {}
            },
            fail: function (res) {}
        });

    },

    jumpToSourceOfCurrentImage: function () {
        wx.navigateTo({
            url: './discoverCaseDetail/discoverCaseDetail'
        })
    },

    /**
     * 底部导航栏界面跳转
     * @param {*} event 
     */
    navBottomJump: function (event) {
        template.navBottomJump(event);
    },
})