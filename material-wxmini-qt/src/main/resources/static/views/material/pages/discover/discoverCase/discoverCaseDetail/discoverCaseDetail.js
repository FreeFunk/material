// pages/databank/caseDetail/caseDetail.js
var template = require('../../../template/template.js');
const commservice = require('../../../../utils/commservice');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        //底部导航栏
        selectNav: {
            pitchOnMaterial: true,
            pitchOnFind: false,
            pitchOnMine: false, //发现和 我的 变成未选中；
        },
        //正在下载;
        downloadIngFlag: false,
        imageHost: app.globalData.imageHost, //图片的路径的前缀
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        console.log(app.globalData.selectCase);
        this.setData({
            selectCase: app.globalData.selectCase,
            previewimglist: [this.data.imageHost + app.globalData.selectCase.orgImageUrl]
        });
    },

    //收藏;
    collectSuccess: function (event) {
        var isAddRes = 0;
        var that = this;
        var selectCase = this.data.selectCase;
        if (selectCase.isCollection == "0") {
            isAddRes = 1;
        }
        selectCase.isCollection = isAddRes;
        this.setData({
            selectCase: selectCase
        });
        var collectionObj = {
            collectionCls: 'CASE', //收藏分类(案例：CASE，色卡：COLOR_MAP)
            relationId: selectCase.id, //关联分类ID
            collectionTitle: selectCase.caseName, //收藏标题
            imageUrl: selectCase.showImageUrl, //封面图
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
                    var pages = getCurrentPages();
                    var prevPage = pages[pages.length - 2]; //上一页
                    that.data.selectCase.isCollection = isAddRes
                    prevPage.setData({
                        selectCase: that.data.selectCase,
                    })
                    that.setData({
                        isCollection: isAddRes
                    });
                } else {}
            },
            fail: function (res) {}
        });
    },
    /**
     * 图片预览
     * @param {} event 
     */
    previewImages: function (event) {
        wx.previewImage({
            current: this.data.imageHost + this.data.selectCase.orgImageUrl, // 当前显示图片的http链接
            urls: this.data.previewimglist // 需要预览的图片http链接列表
        })
    },
    /**
     * 改变当前轮播图的index；
     */
    getCurrentImgIndex: function (e) {
        if (e.detail.source === 'touch') {
            this.setData({
                current: e.detail.current,
                currentStandards: this.data.allSwiperData[e.detail.current].imageTitle,
            })
        }
    },
    //正在下载
    downloadImg: function () {
        //触发函数
        var that = this;
        that.setData({
            downloadIngFlag: true
        });
        wx.downloadFile({
            url: this.data.imageHost + this.data.selectCase.orgImageUrl, //需要下载的图片url
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
                        });
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
            }
        });
    },
    /**
     * 底部导航栏界面跳转
     * @param {*} event 
     */
    navBottomJump: function (event) {
        template.navBottomJump(event);
    },
})