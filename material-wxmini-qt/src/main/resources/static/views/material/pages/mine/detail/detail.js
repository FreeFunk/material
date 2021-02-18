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
            pitchOnMaterial: true,
            pitchOnFind: false,
            pitchOnMine: false, //发现和 我的 变成未选中；
        },
        //正在下载;
        downloadIngFlag: false,
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        current: 0,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        const currentCaseId = options.selectId //这个是选中的案例id;
        const that = this;
        //查询编码+图片
        commservice.request({
            url: app.globalData.appHost + '/materialCase/loadById.jsn',
            data: {
                id: currentCaseId
            },
            method: 'POST',
            success: function (res) {
                if (res.data.success) {
                    console.log(res.data.data);
                    var previewImgList = [];
                    for (var i = 0; i < res.data.data.materialCaseImageList.length; i++) {
                        previewImgList.push(that.data.imageHost + res.data.data.materialCaseImageList[i].orgImageUrl);
                    }
                    that.setData({
                        currentCase: res.data.data,
                        allSwiperData: res.data.data.materialCaseImageList,
                        previewImgList: previewImgList,
                        currentStandards:res.data.data.materialCaseImageList[0].imageTitle,
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
        var imageUrl = event.currentTarget.dataset.imageurl;
        var previewImgList = event.currentTarget.dataset.previewimglist;
        wx.previewImage({
            current: getApp().globalData.imageHost + imageUrl, // 当前显示图片的http链接
            urls: previewImgList // 需要预览的图片http链接列表
        })
    },
    //收藏;
    collectSuccess: function (event) {
        var currentCase = this.data.currentCase;
        var isAddRes = 0;
        if (currentCase.isCollection == "1") {
            isAddRes = 0;
            currentCase.isCollection = 0;
        } else {
            isAddRes = 1;
            currentCase.isCollection = 1;
        }
        //设置成已收藏的图片；
        this.setData({
            currentCase: currentCase
        });

        var collectionObj = {
            collectionCls: 'CASE', //收藏分类(案例：CASE，色卡：COLOR_MAP)
            relationId: currentCase.id, //关联分类ID
            collectionTitle: app.globalData.selectMaterial.materialName + "-" + currentCase.caseClsName, //收藏标题
            imageUrl: currentCase.showImageUrl, //封面图
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
    getCurrentImgIndex: function (e) {
        if (e.detail.source === 'touch') {
            this.setData({
              current: e.detail.getCurrentImgIndex,
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
        var current = this.data.current;
        var imageUrl = this.data.imageHost + this.data.allSwiperData[current].orgImageUrl;
        wx.downloadFile({
            url: imageUrl, //需要下载的图片url
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