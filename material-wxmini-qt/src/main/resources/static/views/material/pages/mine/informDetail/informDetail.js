var template = require('../../template/template.js');
const commservice = require('../../../utils/commservice');
var app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        ktxWindowHeight: getApp().globalData.ktxWindowHeight,
        //底部导航栏
        selectNav: {
            pitchOnMaterial: false,
            pitchOnFind: false,
            pitchOnMine: true, //发现和 我的 变成未选中；
        },
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
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        //获取通告id；
        var infromId = options.infromId;
        var that = this;
        //根据通告id查询通告内容;
        commservice.request({
            url: app.globalData.appHost + '/materialSysMessage/loadById.jsn',
            data: {
                id: infromId
            },
            method: 'POST',
            success: function (res) {
                if (res.data.success) {
                    console.log(res.data.data);
                    that.setData({
                        infromObj: res.data.data,
                        previewImgList: [that.data.imageHost + res.data.data.messageImage]
                    });
                } else {}
            },
            fail: function (res) {}
        });
        var pages = getCurrentPages();
        var prevPage = pages[pages.length - 2]; //上一页
        prevPage.setData({
            infromId: infromId,
        })
    },
    /**
     * 底部导航栏界面跳转
     */
    navBottomJump: function (event) {
        template.navBottomJump(event);
    }
})