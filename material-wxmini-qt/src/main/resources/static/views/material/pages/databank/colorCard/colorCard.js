// 色卡贴图
var template = require('../../template/template.js');
const app = getApp();
var commservice = require('../../../utils/commservice');
const colorCardObj = {};
var leftHeight = 0;
var rightHeight = 0;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        openAllTagsFlag: false,
        ktxWindowHeight: getApp().globalData.ktxWindowHeight,
        //底部导航栏
        selectNav: {
            pitchOnMaterial: true,
            pitchOnFind: false,
            pitchOnMine: false, //发现和 我的 变成未选中；
        },
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        noDataNowFlag: false, //暂无数据;
        thisTagNoDataFlag: false,
        leftList: [],
        rightList: [],
    },
    onShow: function () {
        var that = this;
        var leftList = that.data.leftList;
        var rightList = that.data.rightList;
        let pages = getCurrentPages();
        let currPage = pages[pages.length - 1]; //当前页
        var caseId = currPage.data.currentCaseId;
        var isCol = currPage.data.isCol;
        for (var i = 0; i < leftList.length; i++) {
            if (leftList[i].id == caseId) {
                leftList[i].isCollection = isCol;
            }
        }
        for (var i = 0; i < rightList.length; i++) {
            if (rightList[i].id == caseId) {
                rightList[i].isCollection = isCol;
            }
        }
        this.setData({
            leftList: leftList,
            rightList: rightList
        });
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var withTag = options.withTag;
        if (withTag == "true") {
            this.setData({
                withTag: true,
                selectedTitleTag: app.globalData.selectedTitleTag,
            });
        } else {
            this.setData({
                withTag: false
            });
        }
        this.selectAllTitleTag();
    },
    //查询所有的tag;
    selectAllTitleTag: function () {
        const that = this;
        const selectMaterialId = app.globalData.selectMaterial.id;
        const titleTagsObj = {
            'queryObj.materialId': selectMaterialId,
            'queryObj.type': 'COLOR_MAP'
        };
        commservice.request({
            url: app.globalData.appHost + '/materialCaseCls/listpage.jsn',
            method: 'POST',
            data: titleTagsObj,
            success: function (res) {
                if (res.data.success) {
                    if (res.data.list.length > 0) {
                        if (!that.data.withTag) {
                            that.setData({
                                selectedTitleTag: res.data.list[0],
                                titleTags: res.data.list,
                                noDataNowFlag: false,
                            });
                        } else {
                            // 设置移动的值;
                            var scrollX = 0;
                            for (var i = 0; i < res.data.list.length; i++) {
                                if (that.data.selectedTitleTag.id == res.data.list[i].id) {
                                    break;
                                } else {
                                    scrollX = scrollX + 80;
                                }
                            }
                            that.setData({
                                titleTags: res.data.list,
                                noDataNowFlag: false,
                                scrollX: scrollX,
                            });
                        }
                        that.selectProductColor();
                    } else {
                        that.setData({
                            noDataNowFlag: true,
                        });
                    }
                } else {}
            },
            fail: function (res) {}
        });
    },
    //点击tag标签;
    clickTitleTags: function (e) {
        //点击当前选中的标签不进行数据刷新;
        if (this.data.selectedTitleTag.id == e.currentTarget.dataset.item.id) {
            return;
        }
        if (e.currentTarget.dataset.type == 'show') {
            const selectedTitleTag = e.currentTarget.dataset.item;
            this.setData({
                selectedTitleTag: selectedTitleTag,
                openAllTagsFlag: false
            });
            this.selectProductColor();
        } else {
            var num = 0;
            for (var i = 0; i < this.data.titleTags.length; i++) {
                if (this.data.titleTags[i].id == e.currentTarget.dataset.item.id) {
                    break;
                }
                if (this.data.titleTags[i].id == this.data.selectedTitleTag.id) {
                    continue;
                } else {
                    num++;
                }
            }
            var scrollX = 0+num *80;
            const selectedTitleTag = e.currentTarget.dataset.item;
            this.setData({
                selectedTitleTag: selectedTitleTag,
                openAllTagsFlag: false,
                scrollX: scrollX,
            });
            this.selectProductColor();
        }

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
                    rightHeight += imgHeight;
                } else {
                    leftList.push(list[i]);
                    leftHeight += imgHeight;
                }
            }
        }
        this.setData({
            leftList: leftList,
            rightList: rightList,
        });
    },
    //色卡图片查询 ---使用分页查询；
    selectProductColor: function () {
        leftHeight = 0;
        rightHeight = 0;
        this.setData({
            leftList: [], //左侧的数据;
            rightList: [], //左侧的数据;
        });
        //分页查询；
        commservice.generatePageObj(colorCardObj, app.globalData.appHost + '/materialCase/listpage.jsn', 12);
        colorCardObj.param = {
            'queryObj.type': 'COLOR_MAP',
            'queryObj.caseClsId': this.data.selectedTitleTag.id,
        };
        commservice.getNextPageObjectByCondition(colorCardObj).then((res) => {
            // console.log(res);
            app.globalData.colorCardObj = colorCardObj;
            if (res != undefined && res.length > 0) {
                this.waterFullList(res);
                this.setData({
                    thisTagNoDataFlag: false,
                    coloCardList: res,
                });
            } else {
                this.setData({
                    thisTagNoDataFlag: true
                });
            }
        });
    },

    /**
     * 滑到底部追加数据;
     */
    nextPage: function () {
        var that = this;
        var pageObj = colorCardObj;
        if ((pageObj.currentPage >= pageObj.totalPage && pageObj.totalPage >= 0) || pageObj.isWorking) {
            return;
        } else {
            pageObj.currentPage++;
            return commservice.getPageObjectByCondition(pageObj).then(function (list) {
                app.globalData.colorCardObj = colorCardObj;
                console.log(list);
                that.setData({
                    coloCardList: that.data.coloCardList.concat(list),
                });
                if (list.length > 0) {
                    that.waterFullList(list);
                }
            });
        }
    },
    /**
     * 展开/关闭所有类型的tag;
     */
    openAllTags: function () {
        var openAllTagsFlag = this.data.openAllTagsFlag;
        this.setData({
            openAllTagsFlag: !openAllTagsFlag
        });
    },

    /**
     * 跳转到色卡详情
     * @param {} event 
     */
    jumpToColorCardDetail: function (event) {
        const selectColorCard = event.currentTarget.dataset.item;
        app.globalData.selectCardList = this.data.coloCardList;
        //添加记录到足迹中; 
        commservice.request({
            url: app.globalData.appHost + '/materialUserFootmark/addOrUpdate.jsn',
            method: 'POST',
            data: {
                footmarkCls: 'COLOR_MAP', //足迹分类
                relationId: selectColorCard.id, //关联分类ID
                footmarkTitle: selectColorCard.caseName, //足迹标题
                imageUrl: selectColorCard.showImageUrl //封面图
            },
            success: function (res) {
                if (res.data.success) {
                    app.globalData.selectCase = selectColorCard;
                    wx.navigateTo({
                        url: '../colorCardDetail/colorCardDetail',
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
        //要收藏的案例id;
        var selectCaseId = event.currentTarget.dataset.item.id;
        console.log(event.currentTarget.dataset.item);
        var isAddRes = 0;
        var existInleftFlag = false;
        var leftList = this.data.leftList;
        var rightList = this.data.rightList;
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
            collectionCls: 'COLOR_MAP', //收藏分类(案例：CASE，色卡：COLOR_MAP)
            relationId: event.currentTarget.dataset.item.id, //关联分类ID
            collectionTitle: event.currentTarget.dataset.item.caseName, //收藏标题
            imageUrl: event.currentTarget.dataset.item.showImageUrl, //封面图
            isAdd: isAddRes, //1是要添加，0是要删除；
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
     * 蒙版使用的
     */
    preventD: function () {},
})