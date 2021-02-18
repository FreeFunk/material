// pages/databank/dataBankHome/dataBankHome.js
var template = require('../template/template.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    MainCur: 0,
    VerticalNavTop: 0,
    list: [],
    load: false,
    getPhoneNumFlag: false,
    //底部导航栏
    selectNav: {
      pitchOnMaterial: true,
      pitchOnFind: false,
      pitchOnMine: false, //发现和 我的 变成未选中；
    },
    getPermissionFlag: false
  },
  preventD: function () {}
})