// pages/databank/dataBankHome/dataBankHome.js
var template = require('../../template/template.js');
var commservice = require('../../../utils/commservice');
const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    //底部导航栏标识符
    selectNav: {
      pitchOnMaterial: true, //选中材料库标识符
      pitchOnFind: false, //选中发现页标识符
      pitchOnMine: false //选中我的页面标识符
    },
    getPermissionFlag: false, //授权界面展示
    getPhoneNumFlag: false, //获取电话号码界面展示
    imageHost: app.globalData.imageHost, //图片的路径的前缀
    noDataNow: [1, 2, 3], //暂无数据时候使用的卡片
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.appUpdate();
    that.isPowerOrNo();
    //搜索框文字添加判断
    if (options.searchDirection != "" && options.searchDirection != undefined) {
      this.setData({
        searchDirection: options.searchDirection
      });
    }
    //首次进入小程序;
    if (app.globalData.userInfo == undefined) {
      wx.getSetting({
        success: (res) => {
          if (res.authSetting['scope.userInfo']) {
            commservice.wxLogin().then((res) => {
              //获取缓存中手机号授权的状态
              var phonePermisionStatus = wx.getStorageSync('phonePermision');
              if (phonePermisionStatus == "") {
                commservice.wxLogin().then((res) => {
                  that.initData(); //界面数据初始化;
                });
                that.setData({
                  getPermissionFlag: false, //授权界面展示
                  getPhoneNumFlag: true, //获取电话号码界面展示
                });
              } else {
                wx.getUserInfo({
                  success: function (res) {
                    app.globalData.userInfo = res.userInfo;
                  }
                })
                that.setData({
                  getPermissionFlag: false, //授权界面展示
                  getPhoneNumFlag: false, //获取电话号码界面展示
                });
                that.initData(); //界面数据初始化;
              }
            });
          } else {
            that.setData({
              getPermissionFlag: true, //授权界面展示
              getPhoneNumFlag: false, //获取电话号码界面展示
            });
          }
        }
      });
    } else {
      that.initData(); //界面数据初始化;
    }
  },
  appUpdate: function () {
    // 获取小程序更新机制兼容
    if (wx.canIUse('getUpdateManager')) {
      const updateManager = wx.getUpdateManager()
      updateManager.onCheckForUpdate(function (res) {
        // 请求完新版本信息的回调
        if (res.hasUpdate) {
          updateManager.onUpdateReady(function () {
            updateManager.applyUpdate();
          })
        }
      })
    } else {
      // 如果希望用户在最新版本的客户端上体验您的小程序，可以这样子提示
      wx.showModal({
        title: '提示',
        content: '当前微信版本过低，无法使用该功能，请升级到最新微信版本后重试。'
      })
    }
  },
  /**
   * 判断是否授权了
   */
  isPowerOrNo: function () {
    var that = this;
    // materialUser/getLoginUser.jsn
    commservice.request({
      url: app.globalData.appHost + '/materialUser/getLoginUser.jsn',
      method: 'POST',
      success: function (res) {
        if (res.data.success) {
          if (res.data.data != undefined && res.data.data.isPower == "1") {
            that.setData({
              isPowerFlag: true
            });
          } else {
            that.setData({
              isPowerFlag: false
            });
          }
        } else {

        }
      }
    });
  },

  onShow: function () {},

  /**
   * 点击搜索材料;
   */
  searchForMaterial: function () {
    wx.navigateTo({
      url: '/pages/databank/search/search',
      success: function (res) {
        // 通过eventChannel向被打开页面传送数据
        res.eventChannel.emit('acceptDataFromOpenerPage', {
          data: 'test'
        })
      }
    })
  },

  /**
   * 带着tag类型进行界面跳转;
   * @param {*} event 
   */
  jumpToCaseWithTag: function (event) {
    var item = event.currentTarget.dataset.item;
    app.globalData.selectedTitleTag = item;
    wx.navigateTo({
      url: '../case/case?withTag=' + true,
    })
  },
  /**
   * 带着tag类型进行界面跳转;
   * @param {*} event 
   */
  jumpToColorCardWithTag: function (event) {
    var item = event.currentTarget.dataset.item;
    app.globalData.selectedTitleTag = item;
    wx.navigateTo({
      url: '../colorCard/colorCard?withTag=' + true,
    })
  },

  /**
   * 获取权限;
   * @param {*} e 
   */
  bindGetUserInfo: function (e) {
    var that = this;
    if (e.detail.userInfo) {
      //用户按了允许授权按钮
      commservice.wxLogin().then(() => {
        var nickName = app.globalData.userInfo.nickName;
        var phoneNum = app.globalData.userInfo.phoneNum;
        var headPhoto = app.globalData.userInfo.headPhoto;
        var miniOpenId = app.globalData.userInfo.openId;
        //用户没有授权，就得添加用户到数据库里面去;
        commservice.request({
          url: app.globalData.appHost + '/materialUser/saveOrUpdate.jsn',
          method: 'POST',
          data: {
            phoneNum: phoneNum,
            nickName: nickName,
            headPhoto: headPhoto,
            miniOpenId: miniOpenId
          },
          success: function (res) {
            if (res.data.success) {
              console.log(res);
            } else {}
          },
          fail: function (res) {}
        });
      }).then(() => {
        that.initData(); //界面数据初始化;
        //获取缓存中手机号授权的状态
        var phonePermisionStatus = wx.getStorageSync('phonePermision');
        if (phonePermisionStatus == "") {
          that.setData({
            getPermissionFlag: false, //授权界面展示
            getPhoneNumFlag: true, //获取电话号码界面展示
          });
        } else {
          that.setData({
            getPermissionFlag: false, //授权界面展示
            getPhoneNumFlag: false, //获取电话号码界面展示
          });
        }
      });
    } else {
      //用户按了拒绝按钮
    }
  },
  /**
   * 获取手机号的按钮
   * @param {*} e 
   */
  getPhoneNum: function (e) {
    commservice.getPhoneNumber(e).then(() => {
      var nickName = app.globalData.userInfo.nickName;
      var phoneNum = app.globalData.userInfo.phoneNum;
      var headPhoto = app.globalData.userInfo.headPhoto;
      var miniOpenId = app.globalData.userInfo.openId;
      //用户没有授权，就得添加用户到数据库里面去;
      commservice.request({
        url: app.globalData.appHost + '/materialUser/saveOrUpdate.jsn',
        method: 'POST',
        data: {
          phoneNum: phoneNum,
          nickName: nickName,
          headPhoto: headPhoto,
          miniOpenId: miniOpenId
        },
        success: function (res) {
          if (res.data.success) {
            console.log(res);
          } else {}
        },
        fail: function (res) {}
      });
      wx.setStorageSync('phonePermision', true);
      this.setData({
        getPhoneNumFlag: false
      })
      // commservice.wxLogin().then((res) => {
      //   this.initData(); //界面数据初始化;
      // });
    });
  },

  /**
   * 点击左侧卡片的索引;
   */
  tabSelect: function (e) {
    //左侧样式的变化；
    if (e === undefined) {
      //默认选中第一个
      this.setData({
        selectMaterial: this.data.labelListData[0],
      });
      app.globalData.selectMaterial = this.data.labelListData[0];
    } else {
      if (this.data.selectMaterial.id == e.currentTarget.dataset.currentmaterial.id) {
        return;
      } else {
        app.globalData.selectMaterial = e.currentTarget.dataset.currentmaterial;
        this.setData({
          selectMaterial: e.currentTarget.dataset.currentmaterial,
        });
      }
      console.log(app.globalData.selectMaterial);
    }
    //2.右侧的值进行变化;
    this.setData({
      searchDirection: app.globalData.selectMaterial.materialName,
    });
    this.selectByCurrentMaterial(app.globalData.selectMaterial);
  },

  /**
   * 精品案例
   * @param {*选中的材料} selectMaterial 
   */
  selectCaseListData: function (selectMaterial) {
    var caseListDataPageInfoObj = {};
    this.setData({
      caseObjOne: null,
      caseObjTwo: null,
      caseObjThree: null
    });
    commservice.generatePageObj(caseListDataPageInfoObj, app.globalData.appHost + '/materialCaseCls/listpage.jsn', 3);
    caseListDataPageInfoObj.param = {
      'queryObj.materialId': selectMaterial.id,
      'queryObj.type': "CASE",
      'queryObj.isShowHome': 1,
    };
    commservice.getNextPageObjectByCondition(caseListDataPageInfoObj).then((list) => {
      console.log('产品案例')
      this.setData({
        caseObjOne: list[0],
        caseObjTwo: list[1],
        caseObjThree: list[2],
      });
    });
  },

  /**
   * 产品色卡
   * @param {*选中的材料} selectMaterial 
   */
  selectColorCardData: function (selectMaterial) {
    this.setData({
      colorCardListDataOne: null,
      colorCardListDataTwo: null,
      colorCardListDataThree: null
    });
    var colorCardPageInfoObj = {};
    commservice.generatePageObj(colorCardPageInfoObj, app.globalData.appHost + '/materialCaseCls/listpage.jsn', 6);
    colorCardPageInfoObj.param = {
      'queryObj.materialId': selectMaterial.id,
      'queryObj.type': "COLOR_MAP",
      'queryObj.isShowHome': 1,
    };
    commservice.getNextPageObjectByCondition(colorCardPageInfoObj).then((list) => {
      console.log(list)
      this.setData({
        colorCardListDataOne: list[0],
        colorCardListDataTwo: list[1],
        colorCardListDataThree: list[2],
      });
    });
  },

  /**
   * 轮播图
   * @param {*选中的材料} selectMaterial 
   */
  selectSwiperData: function (selectMaterial) {
    const that = this;
    commservice.request({
      url: app.globalData.appHost + '/materialBanner/listByObj.jsn',
      data: {
        materialId: selectMaterial.id,
      },
      method: 'POST',
      success: function (res) {
        if (res.data.success) {
          that.setData({
            swiperListData: res.data.list
          });
        } else {}
      },
      fail: function (res) {}
    });
  },
  /**
   * 选中左侧标签之后右侧的数据进行变化;
   */
  selectByCurrentMaterial: function (selectMaterial) {
    //轮播图
    this.selectSwiperData(selectMaterial);
    //精品案例;
    this.selectCaseListData(selectMaterial);
    //色卡贴图;
    this.selectColorCardData(selectMaterial);
    //产品样式
    this.productStyle(selectMaterial);
  },

  /**
   * 数据初始化
   */
  initData: function () {
    var that = this;
    //左侧的菜单;
    commservice.request({
      url: app.globalData.appHost + '/material/listpage.jsn',
      method: 'POST',
      success: function (res) {
        if (res.data.success) {
          console.log(res.data.list)
          that.setData({
            labelListData: res.data.list
          });
          //如果已经选中过非第一个标签那就将数据选中到之前选中的标签上;
          if (app.globalData.selectMaterial != "" && app.globalData.selectMaterial != undefined) {
            //选中的第几个
            that.setData({
              selectMaterial: app.globalData.selectMaterial
            });
            that.selectByCurrentMaterial(app.globalData.selectMaterial);
          } else {
            //默认选中第一条数据
            that.tabSelect();
          }
        } else {}
      },
      fail: function (res) {}
    });
  },

  /**
   * 界面跳转的方法汇集
   * @param {*} event 
   */
  jumpToSomeWhere: function (event) {
    var pagename = event.currentTarget.dataset.pagename
    switch (pagename) {
      //跳转到产品报价;
      case 'jumpToQuotine':
        var urlPath = '/pages/databank/quotine/quotine';
        break;
        //跳转到精品案例
      case 'jumpToCase':
        var urlPath = '/pages/databank/case/case';
        break;
        //跳转到色卡贴图
      case 'colorCard':
        var urlPath = '/pages/databank/colorCard/colorCard';
        break;
        //跳转到产品介绍
      case 'jumpToProductionDescription':
        var urlPath = '/pages/databank/productDescription/productDescription';
        break;
        //跳转到施工说明
      case 'jumpToConstruction':
        var urlPath = '/pages/databank/construction/construction';
        break;
    }
    wx.navigateTo({
      url: urlPath,
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
   * 分享按钮
   */
  onShareAppMessage: function () {
    wx.showShareMenu({
      withShareMenu: true,
      success: function () {},
      fail: function () {}
    });
  },

  /**
   * 蒙版使用的
   */
  preventD: function () {},

  /**
   * 产品样式;
   */
  productStyle: function (selectMaterial) {
    this.setData({
      productStyleObjOne: null,
      productStyleObjTwo: null,
      productStyleObjThree: null
    });
    var productStyleObj = {};
    commservice.generatePageObj(productStyleObj, app.globalData.appHost + '/materialProductStyle/listpage.jsn', 3);
    productStyleObj.param = {
      'queryObj.materialId': selectMaterial.id,
      'queryObj.productType': 1,
      'queryObj.isShowHome': 1,
    };
    commservice.getNextPageObjectByCondition(productStyleObj).then((list) => {
      console.log("产品样式");
      console.log(list);
      this.setData({
        productStyleObjOne: list[0],
        productStyleObjTwo: list[1],
        productStyleObjThree: list[2],
      });
    });
  },

  /**
   * 跳转到产品样式详情界面
   */
  jumpToProductStyle: function () {
    wx.navigateTo({
      url: '../productStyle/productStyle',
    })
  }

})