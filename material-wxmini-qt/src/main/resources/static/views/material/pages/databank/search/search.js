// 材料库-查询
var template = require('../../template/template'); //底部导航栏引入
const commService = require('../../../utils/commservice'); //请求接口的引入;
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        inputShowed: true,
        materialRecomendation: [], //推荐标签列表
        //底部导航栏
        selectNav: {
            pitchOnMaterial: true,
            pitchOnFind: false,
            pitchOnMine: false, //发现和 我的 变成未选中；
        },
        searchDivFlag: false,
        materialListBySearch: [],
        ktxWindowHeight: getApp().globalData.ktxWindowHeight,
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var that = this;
        //热门推荐
        const queryObj = {};
        commService.request({
            url: app.globalData.appHost + '/material/listpage.jsn',
            data: queryObj,
            method: 'POST',
            success: function (res) {
                if (res.data.success) {
                    that.setData({
                        materialRecomendation: res.data.list
                    });
                } else {
                    console.log(res.errMsg);
                }
            },
            fail: function (res) {
                console.log(res);
            }
        });
    },


    /**
     * 退出搜索;
     */
    cancelSearch: function () {
        wx.navigateBack({})
    },


    /**
     * 点击标签回到材料库主页;
     */
    // 
    searchMaterial: function (event) {
        const materialLabel = event.currentTarget.dataset.materiallabel;
        app.globalData.selectMaterial = materialLabel;
        wx.reLaunch({
            url: '../dataBankHome/dataBankHome?searchDirection=' + materialLabel.materialName,
        });
    },

    searchByInput: function (event) {
        this.setData({
            searchDivFlag: true
        });
        var inputContent = event.detail.value;
        var that = this;
        if (inputContent != "" && inputContent != undefined) {
            var searchObj = {};
            commService.generatePageObj(searchObj, app.globalData.appHost + '/material/listpage.jsn', 10);
            searchObj.param = {
                'queryObj.materialLabel': inputContent
            };
            commService.getNextPageObjectByCondition(searchObj).then((list) => {
                var materialListBySearch = list;
                that.setData({
                    materialListBySearch: materialListBySearch
                });
            });
        } else {
            that.setData({
                materialListBySearch: []
            });
        }

    },
    loseInput: function () {
        this.setData({
            searchDivFlag: false
        });
    },

    //底部导航栏界面跳转;
    navBottomJump: function (event) {
        event.currentTarget.dataset.pagetype = 'noreturn';
        template.navBottomJump(event);
    },
})