// pages/databank/caseDetail/caseDetail.js
var template = require('../../template/template');
const commservice = require('../../../utils/commservice');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        //底部导航栏
        selectNav: {
            pitchOnMaterial: false,
            pitchOnFind: false,
            pitchOnMine: true, //发现和 我的 变成未选中；
        },
        //正在下载;
        downloadIngFlag: false,
        imageHost: app.globalData.imageHost, //图片的路径的前缀
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        //获取数据;
        var currentCase = app.globalData.selectCase;
        console.log(currentCase);
        this.setData({
            relationId:currentCase.relationId,
            isCollection: 1,
        });
        const that = this;
        //查询编码+图片
        commservice.request({
            url: app.globalData.appHost + '/materialCase/loadById.jsn',
            data: {
                id: currentCase.relationId
            },
            method: 'POST',
            success: function (res) {
                if (res.data.success) {
                    console.log(res.data.data);
                    that.setData({
                        currentCase: res.data.data,
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
    previewImages: function () {
        wx.previewImage({
            current: this.data.imageHost + this.data.currentCase.orgImageUrl, // 当前显示图片的http链接
            urls: [this.data.imageHost + this.data.currentCase.orgImageUrl] // 需要预览的图片http链接列表
        })
    },
    //收藏;
    collectSuccess: function () {
        var isAddRes = 0;
        if (this.data.isCollection == 0) {
            isAddRes = 1;
        }
        this.setData({
            isCollection: isAddRes
        });
        var collectionObj = {
            collectionCls: 'CASE', //收藏分类(案例：CASE，色卡：COLOR_MAP)
            relationId: this.data.relationId, //关联分类ID
            collectionTitle: this.data.currentCase.collectionTitle, //收藏标题
            imageUrl: this.data.currentCase.showImageUrl, //封面图
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
                } else {}
            },
            fail: function (res) {}
        });
    },
    /**
     * 改变当前轮播图的index；
     */
    // getCurrentImgIndex: function (e) {
    //     if (e.detail.source === 'touch') {
    //         this.setData({
    //             current: e.detail.getCurrentImgIndex,
    //             currentStandards: this.data.allSwiperData[e.detail.current].imageTitle,
    //         })
    //     }
    // },
    //正在下载
    downloadImg: function () {
        //触发函数
        var that = this;
        that.setData({
            downloadIngFlag: true
        });
        wx.downloadFile({
            url: this.data.imageHost + this.data.currentCase.imageUrl, //需要下载的图片url
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