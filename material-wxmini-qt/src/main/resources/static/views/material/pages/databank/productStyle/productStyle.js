// 产品样式
var template = require('../../template/template.js');
const app = getApp();
var commservice = require('../../../utils/commservice');
var productObj = {};
var leftHeight = 0;
var rightHeight = 0;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        currentPageNum: 0,
        ktxWindowHeight: app.globalData.ktxWindowHeight,
        //底部导航栏
        selectNav: {
            pitchOnMaterial: true,
            pitchOnFind: false,
            pitchOnMine: false, //发现和 我的 变成未选中；
        },
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        productType: 1, //1是图片数据 2是文件数据;
        noDataNowFlag_image: false, //暂无数据的标志(介绍图片);
        noDataNowFlag_file: false, //暂无数据的标志(产品资料);
        leftList: [],
        rightList: [],
    },
    // 瀑布流1列变2列;
    waterFullList: function (list) {
        var leftList = this.data.leftList;
        var rightList = this.data.rightList;
        for (var i = 0; i < list.length; i++) {
            var imgHeight = 368 * list[i].imageHeight / list[i].imageWidth;
            if (rightHeight < leftHeight) {
                rightList.push(list[i]);
                rightHeight += imgHeight;
            } else {
                leftList.push(list[i]);
                leftHeight += imgHeight;
            }
        }
        this.setData({
            leftList: leftList,
            rightList: rightList,
        });
    },
    /**
     * 介绍图片
     */
    selectData: function () {
        leftHeight = 0;
        rightHeight = 0;
        this.setData({
            leftList: [],
            rightList: [],
        });
        commservice.generatePageObj(productObj, app.globalData.appHost + '/materialProductStyle/listpage.jsn', 200);
        productObj.param = {
            "queryObj.materialId": app.globalData.selectMaterial.id,
            "queryObj.productType": this.data.productType,
            // "queryObj.isShowHome": 0,
        };
        commservice.getNextPageObjectByCondition(productObj).then((res) => {
            console.log(res);
            //如果是图片;
            if (this.data.productType == 1) {
                if (res.length > 0) {
                    this.setData({
                        productImgList: res,
                        noDataNowFlag_image: false,
                    });
                    this.waterFullList(res);
                } else {
                    this.setData({
                        noDataNowFlag_image: true,
                    });
                }

            }
            //如果是产品资料;
            if (this.data.productType == 2) {
                if (res.length > 0) {
                    this.setData({
                        productFileList: res,
                        noDataNowFlag_file: false,
                    });
                } else {
                    this.setData({
                        noDataNowFlag_file: true,
                    });
                }
            }

        });
    },
    /**
     * 滑到底部追加数据;
     */
    nextPage: function () {
        var that = this;
        var pageObj = productObj;
        if ((pageObj.currentPage >= pageObj.totalPage && pageObj.totalPage >= 0) || pageObj.isWorking) {
            return;
        } else {
            pageObj.currentPage++;
            return commservice.getPageObjectByCondition(pageObj).then(function (list) {
                //如果是图片;
                if (this.data.productType == 1) {
                    var productImgList = that.data.productImgList;
                    if (list.length > 0) {
                        that.waterFullList(list);
                        that.setData({
                            productImgList: productImgList.concat(list),
                        });
                    }
                }
                //如果是产品资料;
                if (this.data.productType == 2) {
                    var productFileList = that.data.productFileList;
                    if (list.length > 0) {
                        that.setData({
                            productFileList: productFileList.concat(list),
                        });
                    } else {
                        that.setData({
                            productFileList: productFileList,
                        });
                    }
                }
            });
        }
    },
    /**
     * 查看介绍图片的详情
     */
    previewImg: function (event) {
        var previewImgList = [];
        for (var i = 0; i < this.data.productImgList.length; i++) {
            previewImgList.push(getApp().globalData.imageHost + this.data.productImgList[i].showImageUrl);
        }
        var showImageUrl = event.currentTarget.dataset.imageurl;
        wx.previewImage({
            current: getApp().globalData.imageHost + showImageUrl, // 当前显示图片的http链接
            urls: previewImgList // 需要预览的图片http链接列表
        })
    },

    /**
     * 预览文件;
     */
    previewFile: function (event) {
        this.setData({
            loadPreviewFileFlag: true,
        });
        var that = this;
        var filePath = this.data.imageHost + event.currentTarget.dataset.filepath;
        //var fileUrl = filePath;
        //var filesuffix = fileUrl.substring(fileUrl.lastIndexOf('.') + 1).toLocaleLowerCase();
        if (filePath != null) {
            wx.downloadFile({
                url: filePath, //文件路径
                success: function (res) {
                    console.log(res)
                    var tempFilePath = res.tempFilePath;
                    wx.openDocument({
                        filePath: tempFilePath,
                        success: function (res) {
                            that.setData({
                                loadPreviewFileFlag: false,
                            });
                        },
                        fail: function (res) {
                            that.setData({
                                loadPreviewFileFlag: false,
                            });
                            wx.showToast({
                                title: '文件打开失败',
                                icon: 'none'
                            });
                        },
                        complete: function (res) {
                            console.log(res);
                        }
                    })
                },
                fail: function (res) {
                    console.log('文件下载失败');
                },
                complete: function (res) {},
            })
        }

    },

    /**
     * 点击转发响应事件
     * @param {*} options 
     */
    copyText: function (event) {
        var copyContent = this.data.imageHost + event.currentTarget.dataset.text;
        wx.setClipboardData({
            data: copyContent,
            success: function (res) {
                wx.getClipboardData({
                    success: function (res) {
                        wx.showToast({
                            title: '复制成功'
                        })
                    }
                })
            }
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var screenHeight = getApp().globalData.ktxWindowHeight;
        this.setData({
            ktxWindowHeight: screenHeight
        });
        //产品介绍---介绍图片;
        this.selectData(this.data.productType);
    },
    //点击切换，滑块index赋值
    checkCurrent: function (e) {
        const that = this;
        if (that.data.currentPageNum === e.target.dataset.current) {
            return false;
        } else {
            that.setData({
                currentPageNum: e.target.dataset.current
            })
        }
    },
    //获取当前滑块的index
    bindchange: function (e) {
        this.setData({
            currentPageNum: e.detail.current
        })
        if (e.detail.current == 0) {
            this.data.productType = 1;
            this.selectData();
        } else {
            this.data.productType = 2;
            this.selectData();
        }
    },

    /**
     * 底部导航栏界面跳转
     * @param {*} event 
     */
    navBottomJump: function (event) {
        template.navBottomJump(event);
    },

    //收藏;
    collectSuccess: function () {
        wx.showToast({
            title: '收藏成功',
        })
    }
})