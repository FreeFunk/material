//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs);
    //获取手机的信息;
    wx.getSystemInfo({
      success: res => {
        var systemInfo = res;
        // px转换到rpx的比例
        var pxToRpxScale = 750 / systemInfo.windowWidth;
        // 状态栏的高度
        var ktxStatusHeight = systemInfo.statusBarHeight * pxToRpxScale;
        this.globalData.ktxStatusHeight = ktxStatusHeight;
        // 导航栏的高度
        var navigationHeight = 44 * pxToRpxScale;
        this.globalData.navigationHeight = navigationHeight;
        // window的宽度
        var ktxWindowWidth = systemInfo.windowWidth * pxToRpxScale;
        this.globalData.ktxWindowWidth = ktxWindowWidth;
        // window的高度
        var ktxWindowHeight = systemInfo.windowHeight * pxToRpxScale;
        this.globalData.ktxWindowHeight = ktxWindowHeight;
        // 屏幕的高度
        var ktxScreentHeight = systemInfo.screenHeight * pxToRpxScale;
        this.globalData.ktxScreentHeight = ktxScreentHeight;
        // 底部tabBar的高度
        var tabBarHeight = ktxScreentHeight - ktxStatusHeight - navigationHeight - ktxWindowHeight;
        this.globalData.tabBarHeight = tabBarHeight;
      },
    });
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    pitchOnMaterial: true,
    pitchOnFind: false,
    pitchOnMine: false,
    selectMaterial: "",
    searchLabelName: "", //发现页搜索内容;
    selectCase: {}, //选中查看案例详情的对象;
    selectCardList: [],//案例和色卡详情左右滑动初始list
    caseObj: {},
    colorCardObj: {},
    selectedTitleTag: {}, //这个是选中的头部的tag对象;
    //appHost: "https://mini.yiwei-fs.com:9000",
    appHost: "https://m.yiwei-fs.com:9000",
    imageHost: "https://m.yiwei-fs.com:9004"
  }
})