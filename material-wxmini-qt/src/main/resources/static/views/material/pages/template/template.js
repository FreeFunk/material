//底部导航栏的跳转;
const navBottomJump = event => {
	var whichbtn = event.currentTarget.dataset.whichbtn;
	if(getApp().globalData.selectedNavButton==whichbtn){
		return ;
	}
	var pagetype = event.currentTarget.dataset.pagetype;
	switch (whichbtn) {
		//跳转到材料库界面
		case "1":
			var urlPath = "/pages/databank/dataBankHome/dataBankHome";
			getApp().globalData.selectedNavButton = 1;
			break;
		//跳转到发现界面
		case "2":
			var urlPath = "/pages/discover/discoverHome/discoverHome";
			getApp().globalData.selectedNavButton = 2;
			break;
		//跳转到我的界面
		case "3":
			var urlPath = "/pages/mine/mine";
			getApp().globalData.selectedNavButton = 3;
			break;
	}
	if (pagetype == 'noreturn') {
		wx.reLaunch({
			url: urlPath,
		})
	} else {
		// wx.redirectTo({
		// 	url: urlPath,
		// })
		wx.reLaunch({
			url: urlPath,
		})
	}

};
/**
 * 使用实例;
 //底部导航栏界面跳转;
  navBottomJump: function (event) {
    template.navBottomJump(event);
  },
 */
module.exports = {
	navBottomJump: navBottomJump
}