// pages/mine/mine.js
var template = require('../template/template.js');
const commservice = require('../../utils/commservice');
const app = getApp();
var footmarkObj = {};
var collectObj = {};
var informObj = {};
var leftHeight = 0;
var rightHeight = 0;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        // 控制整个视频蒙版
        isShow: true,
        //控制按钮显示;
        pauseBtnFlag: true,
        pauseBtnSrc: "/images/icon/pause.png",
        //控制封面显示;
        showcoverImage: true,
        //底部导航栏
        selectNav: {
            pitchOnMaterial: false,
            pitchOnFind: false,
            pitchOnMine: true, //发现和 我的 变成未选中；
        },
        clearHistoryConfrimPage: false,
        showLeaveMessPageFlag: false,
        //删除收藏提示框;
        deleteCollectFlag: false,
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        unReadMsgNumFlag: false, //未读通告标志
        //收藏相关list
        collectionList: [],
        leftList: [],
        rightList: [],
    },
    onReady: function () {
        debugger
        this.videoContext = wx.createVideoContext('myVideo')
    },
    // 瀑布流1列变2列;
    waterFullList: function (list) {
        var leftList = this.data.leftList;
        var rightList = this.data.rightList;
        for (var i = 0; i < list.length; i++) {
            if (list[i].imageWidth == 0 || list[i].imageHeight == 0 || list[i].imageWidth == undefined || list[i].imageHeight == undefined) {
                continue
            } else {
                var imgHeight = 362 * list[i].imageHeight / list[i].imageWidth;
                if (rightHeight < leftHeight) {
                    rightList.push(list[i]);
                    rightHeight = rightHeight + imgHeight + 128;
                    continue;
                }
                if (leftHeight < rightHeight || leftHeight == rightHeight) {
                    leftList.push(list[i]);
                    leftHeight = leftHeight + imgHeight + 128;
                    continue;
                }
            }
        }
        if (leftList.length == 0 && rightList == 0) {
            this.setData({
                footprintList: [],
                collectionList: [],
                informList: []
            });
        }
        this.setData({
            leftList: leftList,
            rightList: rightList,
        });
    },
    onShow: function () {
        var leftList = this.data.leftList;
        var rightList = this.data.rightList;
        //回来之后上面黄色选中！
        if (this.data.collectPageFlag != undefined && this.data.footprintPageFlag != undefined && this.data.informPageFlag != undefined && this.data.usPageFlag != undefined) {
            this.setData({
                collectPageFlag: this.data.collectPageFlag,
                footprintPageFlag: this.data.footprintPageFlag,
                informPageFlag: this.data.informPageFlag,
                usPageFlag: this.data.usPageFlag,
            });
            // if (this.data.collectPageFlag) {
            //     let pages = getCurrentPages();
            //     let currPage = pages[pages.length - 1]; //当前页
            //     var relationId = currPage.data.relationId;
            //     var isCol = currPage.data.isCol;
            //     if (isCol != '1') {
            //         for (var i = 0; i < this.data.collectionList.length; i++) {
            //             if (this.data.collectionList[i].relationId == relationId) {
            //                 this.data.collectionList.splice(i, 1);
            //             }
            //         }
            //         this.setData({
            //             leftList: [],
            //             rightList: []
            //         });
            //         this.waterFullList(this.data.collectionList);
            //     }
            // }
            if (this.data.footprintPageFlag) {
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
            if (this.data.informPageFlag) {
                this.unReadMsgNum();
                let pages = getCurrentPages();
                let currPage = pages[pages.length - 1]; //当前页
                var infromId = currPage.data.infromId;
                var informList = this.data.informList;
                for (var i = 0; i < informList.length; i++) {
                    if (informList[i].id == infromId) {
                        informList[i].isRead = 1;
                    }
                }
                this.setData({
                    informList: informList
                });
                // for (var i = 0; i < leftList.length; i++) {
                //     if (leftList[i].id == infromId) {
                //         leftList[i].isRead = 1;
                //     }
                // }
                // for (var i = 0; i < rightList.length; i++) {
                //     if (rightList[i].id == infromId) {
                //         rightList[i].isRead = 1;
                //     }
                // }
                // this.setData({
                //     leftList: leftList,
                //     rightList: rightList
                // });
            }
        } else {
            this.collectPage();
            this.setData({
                collectPageFlag: true,
                footprintPageFlag: false,
                informPageFlag: false,
                usPageFlag: false,
            });
        }
        wx.hideHomeButton();
        var screenHeight = getApp().globalData.ktxWindowHeight;
        this.setData({
            ktxWindowHeight: screenHeight
        });
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var that = this;
        that.isPowerOrNo();
        wx.getUserInfo({
            success: function (res) {
                var userInfo = res.userInfo;
                app.globalData.userInfo = userInfo;
                that.setData({
                    userInfo: userInfo
                });
            }
        });
        var screenHeight = getApp().globalData.ktxWindowHeight;
        this.setData({
            ktxWindowHeight: screenHeight
        });

        //查询用户未读公告的数量
        this.unReadMsgNum();
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
                }
            }
        });
    },
    resetLeftAndRight: function () {
        leftHeight = 0;
        rightHeight = 0;
        this.setData({
            leftList: [],
            rightList: [],
        });
    },
    /**************************收藏相关**************************/
    //收藏 - 跳转收藏;
    collectPage: function (res) {
        if (this.data.collectPageFlag && res != 'go') {
            return;
        } else {
            this.resetLeftAndRight();
            //分页查询收藏;
            commservice.generatePageObj(collectObj, app.globalData.appHost + '/materialUserCollection/listpage.jsn', 6);
            collectObj.param = {};
            commservice.getNextPageObjectByCondition(collectObj).then((res) => {
                console.log(res);
                if (res != undefined) {
                    this.setData({
                        collectionList: res
                    });
                    this.waterFullList(res);
                } else {
                    this.setData({
                        collectionList: []
                    });
                }
            });
            //设置样式
            this.whichPageToShow("collect");
        }
    },
    //收藏 - 删除按钮；
    deleteCollect: function (event) {
     
        const collection = event.currentTarget.dataset.item;
        this.setData({
            deleteCollectFlag: true,
            aboutToDeleteCollect: collection,
        });
    },
    //收藏 - 删除取消;
    cancelDeleteCollect: function () {
        this.setData({
            deleteCollectFlag: false
        });
    },
    //收藏 - 删除成功;
    deleteCollectSuccess: function (event) {
        const that = this;
        const collectionId = this.data.aboutToDeleteCollect.id;
        //调用删除的接口;
        commservice.request({
            url: app.globalData.appHost + '/materialUserCollection/deleteByIds.jsn',
            data: {
                ids: collectionId
            },
            method: 'POST',
            success: function (res) {
                if (res.data.success) {
                    wx.showToast({
                        title: '删除成功',
                    })
                    that.setData({
                        deleteCollectFlag: false
                    });
                    that.collectPage('go');
                } else {
                    console.log(res.errMsg);
                }
            },
            fail: function (res) { }
        });
    },
    // 收藏 - 下一页;
    nextCollectPage: function () {
        var that = this;
        var pageObj = collectObj;
        if ((pageObj.currentPage >= pageObj.totalPage && pageObj.totalPage >= 0) || pageObj.isWorking) {
            return;
        } else {
            pageObj.currentPage++;
            return commservice.getPageObjectByCondition(pageObj).then(function (list) {
                console.log(list);
                var collectionList = that.data.collectionList;
                if (list.length > 0) {
                    that.waterFullList(list);
                    that.setData({
                        collectionList: collectionList.concat(list),
                    });
                } else {
                    that.setData({
                        collectionList: collectionList,
                    });
                }
            });
        }
    },

    /**************************足迹相关**************************/
    //足迹 - 跳转;
    footprintPage: function (res) {
        if (this.data.footprintPageFlag && res != 'go') {
            return;
        } else {
            this.resetLeftAndRight();
            //分页查询用户足迹;
            commservice.generatePageObj(footmarkObj, app.globalData.appHost + '/materialUserFootmark/listpage.jsn', 6);
            footmarkObj.param = {};
            commservice.getNextPageObjectByCondition(footmarkObj).then((res) => {
                if (res != undefined) {
                    console.log(res);
                    this.setData({
                        footprintList: res
                    });
                    this.waterFullList(res);
                } else {
                    this.setData({
                        footprintList: []
                    });
                }
            });
            //设置样式
            this.whichPageToShow("footprint");
        }
    },
    /**
     * 滑到底部追加数据;
     */
    nextFootmarkPage: function () {
        var that = this;
        var pageObj = footmarkObj;
        if ((pageObj.currentPage >= pageObj.totalPage && pageObj.totalPage >= 0) || pageObj.isWorking) {
            return;
        } else {
            pageObj.currentPage++;
            return commservice.getPageObjectByCondition(pageObj).then(function (list) {
                console.log(list);
                var footprintList = that.data.footprintList;
                if (list.length > 0) {
                    that.setData({
                        footprintList: footprintList.concat(list),
                    });
                    that.waterFullList(list);
                }
            });
        }
    },
    //足迹 - 收藏;
    footprintCollect: function (event) {
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
            collectionCls: item.footmarkCls, //收藏分类(案例：CASE，色卡：COLOR_MAP)
            relationId: item.relationId, //关联分类ID
            collectionTitle: item.footmarkTitle, //收藏标题
            imageUrl: item.imageUrl, //封面图
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
                } else { }
            },
            fail: function (res) { }
        });
    },
    //足迹 - 展示清楚记录选择框;
    footprintShowClearHistory: function () {
        this.setData({
            clearHistoryConfrimPage: true
        });
    },
    //足迹 - 清除数据;
    footmarkDel: function (event) {
        //数据变化;
        var that = this;
        var delType = event.currentTarget.dataset.time;
        wx.showLoading({
            title: '正在清除..',
        })
        this.setData({
            clearHistoryConfrimPage: false
        });
        commservice.request({
            url: app.globalData.appHost + '/materialUserFootmark/deleteVo.jsn',
            data: {
                type: delType
            },
            method: 'POST',
            success: function (res) {
                wx.hideLoading();
                wx.showToast({
                    title: '清除成功',
                    icon: 'success',
                    duration: 1900,
                })
                setTimeout(function () {
                    if (res.data.success) {
                        //重新加载数据;
                        that.footprintPage('go');
                    } else {
                        console.log(res.errMsg);
                    }
                }, 2000);

            },
            fail: function (res) { }
        });

    },
    //足迹 - 清楚记录取消;
    footprintClearHistoryCancel: function () {
        this.setData({
            clearHistoryConfrimPage: false
        });
    },
    //收藏和取消收藏;
    collectSuccess: function (event) {
        console.log(this.data.footprintList);
        const index = event.currentTarget.dataset.index; //要收藏的案例的id;
        var isAddRes = 1;
        if (this.data.footprintList[index].isCollection == "1") {
            //已经被收藏了;
            this.data.footprintList[index].isCollection = "0";
            isAddRes = 0;
        } else {
            //没有被收藏
            this.data.footprintList[index].isCollection = "1";
            isAddRes = 1;
        }
        //设置成已收藏的图片；
        this.setData({
            footprintList: this.data.footprintList
        });
        var collectionObj = {
            collectionCls: this.data.footprintList[index].footmarkCls, //收藏分类(案例：CASE，色卡：COLOR_MAP)
            relationId: this.data.footprintList[index].relationId, //关联分类ID
            collectionTitle: app.globalData.selectMaterial.materialName + "-" + this.data.footprintList[index].footmarkTitle, //收藏标题
            imageUrl: this.data.footprintList[index].imageUrl, //封面图
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
                } else { }
            },
            fail: function (res) { }
        });

    },

    /**
     * 图片预览
     * @param {} event 
     */
    previewUsPage: function (event) {
        var imageUrl = event.currentTarget.dataset.imageurl;
        var previewImgList = [getApp().globalData.imageHost + imageUrl];
        wx.previewImage({
            current: getApp().globalData.imageHost + imageUrl, // 当前显示图片的http链接
            urls: previewImgList // 需要预览的图片http链接列表
        })
    },
    // 通告专属瀑布流
    informPageWaterFull: function (list) {
        var leftList = this.data.leftList;
        var rightList = this.data.rightList;
        for (var i = 0; i < list.length; i++) {
            if (rightHeight < leftHeight) {
                rightList.push(list[i]);
                if (list[i].messageImage == 'undefined' || list[i].messageImage == '') {
                    rightHeight = rightHeight + 128;
                } else {
                    var imgHeight = 362 * list[i].imageHeight / list[i].imageWidth;
                    rightHeight = rightHeight + imgHeight + 128;
                }
                continue;
            } else {
                leftList.push(list[i]);
                if (list[i].messageImage == 'undefined' || list[i].messageImage == '') {
                    leftHeight = leftHeight + 128;
                } else {
                    var imgHeight = 362 * list[i].imageHeight / list[i].imageWidth;
                    leftHeight = leftHeight + imgHeight + 128;
                }
                continue;
            }
        }
        this.setData({
            leftList: leftList,
            rightList: rightList,
        });
    },
    //通告：
    informPage: function () {
        if (this.data.informPageFlag) {
            return;
        } else {
            leftHeight = 0, rightHeight = 0;
            this.setData({
                leftList: [], //左侧的数据;
                rightList: [], //左侧的数据;
            });
            //分页查询用户足迹;
            commservice.generatePageObj(informObj, app.globalData.appHost + '/materialSysMessage/listpage.jsn', 6);
            informObj.param = {};
            commservice.getNextPageObjectByCondition(informObj).then((res) => {
                console.log(res);
                this.setData({
                    informList: res
                });
                this.informPageWaterFull(res);
            });
            //界面跳转
            this.whichPageToShow('informPage');
        }
    },
    nextInformList: function () {
        var that = this;
        var pageObj = informObj;
        if ((pageObj.currentPage >= pageObj.totalPage && pageObj.totalPage >= 0) || pageObj.isWorking) {
            return;
        } else {
            pageObj.currentPage++;
            return commservice.getPageObjectByCondition(pageObj).then(function (list) {
                console.log(list);
                var informList = that.data.informList;
                if (list.length > 0) {
                    that.setData({
                        informList: informList.concat(list),
                    });
                    that.informPageWaterFull(list);
                } else {
                    that.setData({
                        informList: informList,
                    });
                }
            });
        }
    },
    //我们：
    usPage: function () {
        if (this.data.usPageFlag) {
            return;
        } else {
            //界面跳转
            this.whichPageToShow('usPage');
            //关于我们的数据查询：
            // materialAboutUs/listpage.jsn
            var that = this;
            commservice.request({
                url: app.globalData.appHost + '/materialAboutUs/listpage.jsn',
                method: 'POST',
                success: function (res) {
                    if (res.data.success) {
                        console.log(res.data.list);
                        that.setData({
                            aboutUsData: res.data.list[0],
                            aboutUsList: res.data.list,
                        });
                    } else {
                        console.log(res.errMsg);
                    }
                },
                fail: function (res) { }
            });
        }
    },
    previewMinePage: function () {
        var imageUrl = this.data.imageHost + this.data.aboutUsData.fileUrl;
        var previewImgList = [imageUrl];
        wx.previewImage({
            current: imageUrl, // 当前显示图片的http链接
            urls: previewImgList // 需要预览的图片http链接列表
        })
    },
    //留言
    leaveMessPage: function () {
        this.setData({
            showLeaveMessPageFlag: true
        });
    },
    //留言提交
    submitMess: function () {
        this.setData({
            showLeaveMessPageFlag: false
        });
    },

    /**
     * 查询用户未读公告的数量
     */
    unReadMsgNum: function () {
        var that = this;
        commservice.request({
            url: app.globalData.appHost + '/materialSysMessage/unReadMsgNum.jsn',
            method: 'POST',
            success: function (res) {
                if (res.data.success) {
                    if (res.data.data == 0) {
                        that.setData({
                            unReadMsgNumFlag: false
                        });
                    } else {
                        that.setData({
                            unReadMsgNumFlag: true
                        });
                    }
                } else { }
            },
            fail: function (res) { }
        });
    },

    /**
     * 点击封面自定义播放按钮时触发 video使用
     */
    bindplay() {
        //1.去掉蒙版 2.隐藏暂停图标 3.展示播放图标
        this.videoContext.play();
        this.setData({
            pauseBtnSrc: '/images/icon/run.png',
            isShow: false
        })
    },
    /**
     * 监听播放到末尾时触发 video使用
     */
    bindended() {
        console.log('bindended')
        this.setData({
            isShow: true
        })
        this.videoContext.ended();
    },
    /**
     * 监听暂停播放时触发 video使用
     */
    bindpause() {
        this.setData({
            isShow: true,
            showcoverImage: false,
            pauseBtnFlag: true,
            pauseBtnSrc: "/images/icon/pause.png",
        })
    },

    /**
     * 底部导航栏界面跳转
     */
    navBottomJump: function (event) {
        template.navBottomJump(event);
    },

    /**
     * 跳转样式
     */
    whichPageToShow: function (pageName) {
        //设置样式
        this.setData({
            collectPageFlag: false,
            footprintPageFlag: false,
            informPageFlag: false,
            usPageFlag: false,
        });
        switch (pageName) {
            case 'collect':
                //收藏界面
                this.setData({
                    collectPageFlag: true
                });
                break;
            case 'footprint':
                // 足迹界面
                this.setData({
                    footprintPageFlag: true
                });
                break;
            case 'informPage':
                //通告界面
                this.setData({
                    informPageFlag: true
                });
                break;
            case 'usPage':
                //我们界面
                this.setData({
                    usPageFlag: true
                });
                break;
        }
    },

    /**
     * 跳转到详情界面
     * @param { } event 
     */
    jumpToDetail: function (event) {
        var item = event.currentTarget.dataset.item;
        //通告跳转;
        var pagetype = event.currentTarget.dataset.pagetype;
        if (pagetype == 'inform') {
            //关联一条通告;
            commservice.request({
                url: app.globalData.appHost + '/materialUserMessageRelation/addOrUpdate.jsn',
                data: {
                    sysMessageId: item.id
                },
                method: 'POST',
                success: function (res) {
                    if (res.data.success) {
                        wx.navigateTo({
                            url: './informDetail/informDetail?infromId=' + item.id,
                        })
                    } else { }
                },
                fail: function (res) { }
            });
        } else if (pagetype == 'collect') {
            //收藏详情;
            app.globalData.selectCase = item;
            wx.navigateTo({
                url: './collectDetail/collectDetail',
            })
        } else {
            //足迹详情;
            app.globalData.selectCase = item;
            wx.navigateTo({
                url: './footmarkDetail/footmarkDetail',
            })
        }
    },

    /**
     * 蒙版，scrollview使用
     */
    preventD() { },

})