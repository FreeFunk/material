// 发现 - 主页;
var template = require('../../template/template');
const commservice = require('../../../utils/commservice');
const app = getApp();
var caseObj = {};
var leftHeight = 0;
var rightHeight = 0;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        ktxWindowHeight: getApp().globalData.ktxWindowHeight,
        //底部导航栏
        selectNav: {
            pitchOnMaterial: false,
            pitchOnFind: true,
            pitchOnMine: false, //发现和 我的 变成未选中；
        },
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        dataOver: false,
        leftList: [],
        rightList: [],
        showRecommendationFlag: false, //默认隐藏同类推荐;
        showCancelBtn: false
    },
    // 瀑布流1列变2列;
    waterFullList: function (list) {
        var leftList = this.data.leftList;
        var rightList = this.data.rightList;
        for (var i = 0; i < list.length; i++) {
            if (list[i].imageWidth == 0 || list[i].imageHeight == 0) {
                continue
            } else {
                var imgHeight = 368 * list[i].imageHeight / list[i].imageWidth;
                if (rightHeight < leftHeight) {
                    rightList.push(list[i]);
                    rightHeight += imgHeight + 128;
                } else {
                    leftList.push(list[i]);
                    leftHeight += imgHeight + 128;
                }
            }
        }
        this.setData({
            leftList: leftList,
            rightList: rightList,
        });
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function () {
        // this.setData({
        //     searchLabelName: app.globalData.searchLabelName
        // });
        this.selectCase();
    },
    onShow: function () {
        wx.hideHomeButton();
        var that = this;
        var leftList = that.data.leftList;
        var rightList = that.data.rightList;
        let pages = getCurrentPages();
        let currPage = pages[pages.length - 1]; //当前页
        var selectCase = currPage.data.selectCase;
        if (selectCase != undefined) {
            for (var i = 0; i < leftList.length; i++) {
                if (leftList[i].id == selectCase.id) {
                    leftList[i] = selectCase;
                }
            }
            for (var i = 0; i < rightList.length; i++) {
                if (rightList[i].id == selectCase.id) {
                    rightList[i] = selectCase;
                }
            }
            this.setData({
                leftList: leftList,
                rightList: rightList
            });
        }
    },

    /**
     * 案例查询 ---使用分页查询;
     */
    selectCase: function () {
        leftHeight = 0;
        rightHeight = 0;
        this.setData({
            leftList: [], //左侧的数据;
            rightList: [], //左侧的数据;
        });
        //分页查询；
        commservice.generatePageObj(caseObj, app.globalData.appHost + '/materialCase/findListPage.jsn', 8);
        caseObj.param = {
            'queryObj.type': 'CASE',
            'queryObj.caseLabel': this.data.searchLabelName,
            'queryObj.isHide': '0',
        };
        commservice.getNextPageObjectByCondition(caseObj).then((res) => {
            if (res != undefined) {
                this.setData({
                    caseList: res,
                });
                if (res.length == 0) {
                    this.setData({
                        showCancelBtn: true,
                    });
                }
                console.log(this.data.caseList);
                this.waterFullList(res);
            }
        });
    },
    /**
     * 滑到底部追加数据;
     */
    nextPage: function () {
        var that = this;
        var pageObj = caseObj;
        if ((pageObj.currentPage >= pageObj.totalPage && pageObj.totalPage >= 0) || pageObj.isWorking) {
            return;
        } else {
            pageObj.currentPage++;
            return commservice.getPageObjectByCondition(pageObj).then(function (list) {
                console.log(list);
                var caseList = that.data.caseList;
                if (list.length > 0) {
                    that.setData({
                        caseList: caseList.concat(list),
                    });
                    that.waterFullList(list);
                } else {
                    that.setData({
                        caseList: caseList,
                    });
                }
            });
        }
    },

    //展示同类推荐;
    showRecommendation: function () {
        this.setData({
            showCancelBtn: true,
        });
        var that = this;
        //热门推荐
        const queryObj = {
            'queryObj.labelType': 2
        };
        commservice.request({
            url: app.globalData.appHost + '/materialLabel/listpage.jsn',
            data: queryObj,
            method: 'POST',
            success: function (res) {
                if (res.data.success) {
                    that.setData({
                        showRecommendationFlag: true,
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
    //点击推荐的标签;
    clickRecomendationTag: function (event) {
        var labelname = event.currentTarget.dataset.labelname;
        //刷新数据;
        this.setData({
            searchLabelName: labelname,
            showRecommendationFlag: false,
            showCancelBtn: false,
        });
        this.selectCase();
    },
    //隐藏同类推荐;滑动的时候隐藏;
    hideRecommendation: function () {
        this.setData({
            showRecommendationFlag: false,
            searchDivFlag: false,
            showCancelBtn: false,
        });
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
        commservice.request({
            url: app.globalData.appHost + '/materialCase/listLable.jsn',
            data: {
                'caseLable': inputContent
            },
            method: 'POST',
            success: function (res) {
                if (res.data.success) {
                    that.setData({
                        showRecommendationFlag: true,
                        materialListBySearch: res.data.data
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
     * 点击标签
     * @param {*} event 
     */
    clickSearchLabel: function (event) {
        var item = event.currentTarget.dataset.item;
        //刷新数据;
        this.setData({
            searchLabelName: item,
            searchDivFlag: false,
            showRecommendationFlag: false,
            showCancelBtn: false,
        });
        this.selectCase();
    },
    //点击键盘的搜索按钮;
    searchConfirm: function (event) {
        var inputContent = event.detail.value;
        this.setData({
            searchLabelName: inputContent,
            searchDivFlag: false,
            showRecommendationFlag: false,
            showCancelBtn: false,
        });
        this.selectCase();
    },
    /**
     * 点击取消按钮;
     */
    cancelSearch: function () {
        this.setData({
            searchLabelName: "",
            searchDivFlag: false,
            showRecommendationFlag: false,
            showCancelBtn: false,
        });
        this.selectCase();
    },
    // /**
    //  * 跳转到 查询;
    //  */
    // searchForDisCover: function () {
    //     //搜索界面;
    //     // wx.navigateTo({
    //     //     url: '/pages/discover/discoverSearch/discoverSearch',
    //     // })
    //     //展开同类推荐;
    //     // materialRecomendation
    //     //搜索展示搜索列表;
    //     //点击搜索列表关闭同类推荐和搜索列表;
    // },

    /**
     * 跳转到案例
     */
    jumpToCase: function (event) {
        //传值过去;
        const selectCase = event.currentTarget.dataset.item;
        //添加记录到足迹中; 
        commservice.request({
            url: app.globalData.appHost + '/materialUserFootmark/addOrUpdate.jsn',
            method: 'POST',
            data: {
                footmarkCls: 'CASE', //足迹分类
                relationId: selectCase.caseClsId, //关联分类ID
                footmarkTitle: selectCase.caseName, //足迹标题
                imageUrl: selectCase.showImageUrl //封面图
            },
            success: function (res) {
                if (res.data.success) {
                    app.globalData.selectCase = selectCase;
                    wx.navigateTo({
                        url: '/pages/discover/discoverCase/discoverCase'
                    })
                }
            },
            fail: function (res) {}
        });
    },

    //收藏和取消收藏;
    collectSuccess: function (event) {
        this.setData({
            currentCaseId: null
        });
        const item = event.currentTarget.dataset.item; //要收藏的案例的id;
        console.log(item);
        var selectCaseId = item.id;
        var existInleftFlag = false; //是否在左侧那一列的标志;
        var isAddRes = 0;
        var leftList = this.data.leftList;
        var rightList = this.data.rightList;
        //循环左边的;
        for (var i = 0; i < leftList.length; i++) {
            if (selectCaseId == leftList[i].id) {
                //修改左侧的数据;
                var collectionBeforeFlag = leftList[i].isCollection;
                if (collectionBeforeFlag == 0) {
                    isAddRes = 1;
                }
                leftList[i].isCollection = isAddRes;
                this.setData({
                    leftList: leftList
                });
                break;
            }
        }
        if (!existInleftFlag) {
            for (var i = 0; i < rightList.length; i++) {
                if (selectCaseId == rightList[i].id) {
                    //修改左侧的数据;
                    var collectionBeforeFlag = rightList[i].isCollection;
                    if (collectionBeforeFlag == 0) {
                        isAddRes = 1;
                    }
                    rightList[i].isCollection = isAddRes;
                    this.setData({
                        rightList: rightList
                    });
                    break;
                }
            }
        }
        var collectionObj = {
            collectionCls: 'CASE', //收藏分类(案例：CASE，色卡：COLOR_MAP)
            relationId: item.id, //关联分类ID
            collectionTitle: item.caseName, //收藏标题
            imageUrl: item.showImageUrl, //封面图
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
     * 底部导航栏界面跳转
     * @param {*} event 
     */
    navBottomJump: function (event) {
        template.navBottomJump(event);
    },
    /**
     * 用来禁止报错的;
     */
    preventD: function () {}
})