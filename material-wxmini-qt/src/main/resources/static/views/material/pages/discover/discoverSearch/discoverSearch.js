// 发现-查询界面
var template = require('../../template/template');
const commService = require('../../../utils/commservice'); //请求接口的引入;
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    inputShowed: true,
    //底部导航栏
    selectNav: {
      pitchOnMaterial: false,
      pitchOnFind: true,
      pitchOnMine: false, //发现和 我的 变成未选中；
    },
    materialListBySearch: [],
    ktxWindowHeight: getApp().globalData.ktxWindowHeight,
  },
onShow:function(){
  app.globalData.searchLabelName = "";
},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      searchLabelName: app.globalData.searchLabelName
    });
    var that = this;
    //热门推荐
    const queryObj = {
      'queryObj.labelType': 2
    };
    commService.request({
      url: app.globalData.appHost + '/materialLabel/listpage.jsn',
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
  // 返回
  cancelSearch: function () {
    wx.navigateBack({
      delta: 0,
    })
  },
  /**
   * 开始搜索;
   */
  searchByInput: function (event) {
    this.setData({
      searchDivFlag: true
    });
    var inputContent = event.detail.value;
    var that = this;
    if (inputContent != "" && inputContent != undefined) {
      var searchObj = {};
      commService.generatePageObj(searchObj, app.globalData.appHost + '/materialCase/listpage.jsn', 10);
      searchObj.param = {
        'queryObj.type': 'CASE',
        'queryObj.isHide': 0,
        'queryObj.caseLabel': inputContent
      };
      commService.getNextPageObjectByCondition(searchObj).then((list) => {
        if (list.length == 0) {
          app.globalData.searchLabelName = "";
        } else {
          var materialListBySearch = list;
          that.setData({
            materialListBySearch: materialListBySearch
          });
        }
      });
    } else {
      that.setData({
        materialListBySearch: []
      });
    }
  },
  /**
   * 点击标签
   * @param {*} event 
   */
  clickSearchLabel: function (event) {
    var item = event.currentTarget.dataset.item;
    app.globalData.searchLabelName = item.caseName;
    wx.reLaunch({
      url: '/pages/discover/discoverHome/discoverHome',
    })
  },
  // 点击热门推荐标签;
  clickRecomendationTag: function (event) {
    var labelname = event.currentTarget.dataset.labelname;
    app.globalData.searchLabelName = labelname;
    wx.reLaunch({
      url: '/pages/discover/discoverHome/discoverHome',
    })
  },

  /**
   * 底部导航栏界面跳转
   * @param {*} event 
   */
  navBottomJump: function (event) {
    template.navBottomJump(event);
  },
  loseInput: function () {
    this.setData({
      searchDivFlag: false
    });
  },

})