//施工介绍
var template = require('../../template/template.js');
const commservice = require('../../../utils/commservice');
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        ktxWindowHeight: getApp().globalData.ktxWindowHeight,
        //底部导航栏
        selectNav: {
            pitchOnMaterial: true,
            pitchOnFind: false,
            pitchOnMine: false, //发现和 我的 变成未选中；
        },
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        fileType: 'png',
        noDataNowFlag: false,//暂无数据标志;
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        const that = this;
        commservice.request({
            url: app.globalData.appHost + '/materialBuildIntroduction/loadByMaterialId.jsn',
            method: 'POST',
            data: {
                materialId: app.globalData.selectMaterial.id
            },
            success: function (res) {
                if (res.data.success) {
                    if (res.data.data != undefined) {
                        var fileType = res.data.data.fileUrl.split(".")[1];
                        that.setData({
                            construction: res.data.data,
                            fileType: fileType,
                            noDataNowFlag: false,
                        });
                    } else {
                        that.setData({
                            noDataNowFlag: true,
                        });
                    }

                } else { }
            },
            fail: function (res) { }
        });
    },
    //底部导航栏界面跳转;
    navBottomJump: function (event) {
        template.navBottomJump(event);
    },
    /**
     * 图片预览
     * @param {} event 
     */
    previewImages: function (event) {
        var imageUrl = event.currentTarget.dataset.imageurl;
        var previewImgList = [imageUrl];
        wx.previewImage({
            current: imageUrl, // 当前显示图片的http链接
            urls: previewImgList // 需要预览的图片http链接列表
        })
    },
})