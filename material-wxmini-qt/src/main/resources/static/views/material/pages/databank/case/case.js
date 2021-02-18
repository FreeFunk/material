// pages/databank/case/case.js
var template = require('../../template/template.js');
const commservice = require('../../../utils/commservice');
const app = getApp();
const caseObj = {}; //定义个全局的这个对象存放分页查询使用的专属对象;
var leftHeight = 0,
    rightHeight = 0;
var query;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        openAllTagsFlag: false,
        //底部导航栏
        selectNav: {
            pitchOnMaterial: true,
            pitchOnFind: false,
            pitchOnMine: false, //发现和 我的 变成未选中；
        },
        ListData: [],
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        noDataNowFlag: false, //暂无数据的标志;
        thisTagNoDataFlag: false, //有tag但是没有图片数据显示暂无数据;
        leftList: [], //左侧的数据;
        rightList: [], //左侧的数据;
    },
    /**
     * 跳转到精品案例某一个详情
     */
    jumpToCaseDetail: function (e) {
        const selectCase = e.currentTarget.dataset.item;
        app.globalData.selectCase = selectCase;
        app.globalData.selectCardList = this.data.caseList;
        //添加记录到足迹中; 
        commservice.request({
            url: app.globalData.appHost + '/materialUserFootmark/addOrUpdate.jsn',
            method: 'POST',
            data: {
                footmarkCls: 'CASE', //足迹分类
                relationId: selectCase.id, //关联分类ID
                footmarkTitle: selectCase.caseName, //足迹标题
                imageUrl: selectCase.showImageUrl //封面图
            },
            success: function (res) {
                if (res.data.success) {
                    wx.navigateTo({
                        url: '/pages/databank/caseDetail/caseDetail',
                    })
                }
            },
            fail: function (res) {}
        });
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var withTag = options.withTag;
        if (withTag == "true") {
            this.setData({
                withTag: true
            });
        } else {
            this.setData({
                withTag: false
            });
        }
        var screenHeight = getApp().globalData.ktxWindowHeight;
        this.setData({
            ktxWindowHeight: screenHeight,
            selectedTitleTag: app.globalData.selectedTitleTag,
        });
        this.selectAllTitleTag();
    },

    /**
     * 查询所有的tagTitle
     */
    selectAllTitleTag: function () {
        const that = this;
        const selectMaterialId = app.globalData.selectMaterial.id;
        const titleTagsObj = {
            'queryObj.materialId': selectMaterialId,
            'queryObj.type': 'CASE'
        };
        commservice.request({
            url: app.globalData.appHost + '/materialCaseCls/listpage.jsn',
            method: 'POST',
            data: titleTagsObj,
            success: function (res) {
                console.log("所有标签");
                console.log(res.data.list);
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
                        that.selectCase();
                    } else {
                        that.setData({
                            noDataNowFlag: true
                        });
                    }
                } else {}
            },
            fail: function (res) {}
        });
    },

    //点击tag标签;
    clickTitleTags: function (e) {
        //点击已经选中的tag不进行数据刷新;
        if (this.data.selectedTitleTag.id == e.currentTarget.dataset.item.id) {
            return;
        } else {
            if (e.currentTarget.dataset.type == 'show') {
                const selectedTitleTag = e.currentTarget.dataset.item;
                this.setData({
                    selectedTitleTag: selectedTitleTag,
                    openAllTagsFlag: false,
                });
                this.selectCase();
            } else {
                //首先移动坐标;
                var num = 0;
                for (var i = 0; i < this.data.titleTags.length; i++) {
                    if (this.data.titleTags[i].id == e.currentTarget.dataset.item.id) {
                        break;
                    } if(this.data.titleTags[i].id == this.data.selectedTitleTag.id) {
                        continue;
                    }else {
                        num++;
                    }
                }
                var scrollX = 0+num *80;
                const selectedTitleTag = e.currentTarget.dataset.item;
                this.setData({
                    selectedTitleTag: selectedTitleTag,
                    openAllTagsFlag: false,
                    scrollX: scrollX
                });
                this.selectCase();
            }
        }
    },

    distributeLeftAndRightList: function (list) {
        var leftList = this.data.leftList;
        var rightList = this.data.rightList;
        for (var i = 0; i < list.length; i++) {
            if (list[i].imageWidth == 0 || list[i].imageHeight == 0) {
                continue
            } else {
                var imgHeight = 368 * list[i].imageHeight / list[i].imageWidth;
                if (rightHeight < leftHeight) {
                    rightList.push(list[i]);
                    rightHeight = rightHeight + imgHeight + 6;
                } else {
                    leftList.push(list[i]);
                    leftHeight = leftHeight + imgHeight + 6;
                }
            }
        }
        this.setData({
            leftList: leftList,
            rightList: rightList,
        });
    },
    //案例查询 ---使用分页查询；
    selectCase: function () {
        //分页查询；
        leftHeight = 0, rightHeight = 0;
        this.setData({
            leftList: [], //左侧的数据;
            rightList: [], //左侧的数据;
        });
        var that = this;
        commservice.generatePageObj(caseObj, app.globalData.appHost + '/materialCase/listpage.jsn', 12);
        caseObj.param = {
            'queryObj.type': 'CASE',
            'queryObj.caseClsId': this.data.selectedTitleTag.id,
        };
        commservice.getNextPageObjectByCondition(caseObj).then((res) => {
            console.log(res);
            app.globalData.caseObj = caseObj;
            if (res != undefined && res.length > 0) {
                // 创建对象
                that.distributeLeftAndRightList(res);
                that.setData({
                    caseList: res,
                    thisTagNoDataFlag: false
                });
            } else {
                that.setData({
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
        var pageObj = caseObj;
        if ((pageObj.currentPage >= pageObj.totalPage && pageObj.totalPage >= 0) || pageObj.isWorking) {
            return;
        } else {
            pageObj.currentPage++;
            return commservice.getPageObjectByCondition(pageObj).then(function (list) {
                console.log(list);
                app.globalData.caseObj = caseObj;
                that.setData({
                    caseList: that.data.caseList.concat(list),
                });
                that.distributeLeftAndRightList(list);
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
    //收藏和取消收藏;
    collectSuccess: function (event) {
        this.setData({
            currentCaseId: null
        });
        //要收藏的案例id;
        var selectCaseId = event.currentTarget.dataset.collectionobj.id;
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
            collectionCls: 'CASE', //收藏分类(案例：CASE，色卡：COLOR_MAP)
            relationId: event.currentTarget.dataset.collectionobj.id, //关联分类ID
            collectionTitle: event.currentTarget.dataset.collectionobj.caseName, //收藏标题
            imageUrl: event.currentTarget.dataset.collectionobj.showImageUrl, //封面图
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
    }
})