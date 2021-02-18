// 报价
var template = require('../../template/template.js');
var commService = require('../../../utils/commservice');
const app = getApp();
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
        selectedProductId: 0,
        imageHost: app.globalData.imageHost, //图片的路径的前缀
        currentQuotineObj: [], //展现的文件列表;
        noDataNowFlag: false, //暂无数据
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var that = this;
        //获取所有的数据;
        commService.request({
            url: app.globalData.appHost + '/materialProductPrice/listpage.jsn',
            data: {
                "queryObj.materialId": app.globalData.selectMaterial.id
            },
            method: 'POST',
            success: function (res) {
                if (res.data.success) {
                    console.log(res.data.list);
                    if (res.data.list == 0) {
                        that.setData({
                            noDataNowFlag: true, //暂无数据
                        });
                    } else {
                        that.setData({
                            noDataNowFlag: false, //暂无数据
                            priceObjList: res.data.list, //本材料报价的所有数据;
                            selectedTitleTag: res.data.list[0], //当前选中的标签;
                            showFileList: res.data.list[0].productPriceFileViewList, //当前选中的标签对应的文件列表;
                        });
                    }

                } else {}
            },
            fail: function (res) {}
        });
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
     * 预览文件;
     */
    previewFile: function (event) {
        this.setData({
            loadPreviewFileFlag: true,
        });
        var that = this;
        var filePath = this.data.imageHost + event.currentTarget.dataset.filepath;
        var type = event.currentTarget.dataset.filetype;
        wx.downloadFile({
            url: filePath, //文件路径
            header: {},
            success: function (res) {
                console.log(res)
                var filePath = res.tempFilePath;
                console.log(filePath);
                wx.openDocument({
                    filePath: filePath,
                    fileType: type, //文件类型
                    success: function (res) {
                        that.setData({
                            loadPreviewFileFlag: false,
                        });
                        console.log('打开文档成功')
                    },
                    fail: function (res) {
                        console.log(res);
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
    },

    /**
     * 图片预览
     * @param {} event 
     */
    previewImages: function (event) {
        var imageUrl = event.currentTarget.dataset.imageurl;
        var previewImgList = [this.data.imageHost + imageUrl];
        wx.previewImage({
            current: this.data.imageHost + imageUrl, // 当前显示图片的http链接
            urls: previewImgList // 需要预览的图片http链接列表
        })
    },

    /**
     * 滑动图片文件列表变化
     * @param {*} e 
     */
    changeImg: function (e) {
        const currentImageIndex = e.detail.current; //当前图片是第几张？0/1/2
        var list = this.data.priceObjList;

        var fileList = list[currentImageIndex].productPriceFileViewList; //接受当前图片对应的数据;
        //改变资料的值：
        this.setData({
            showFileList: fileList, //修改文件的list;
            selectedTitleTag: list[currentImageIndex], //修改标签的选中情况;
        });
    },

    /**
     * 点击标签，图片和文件变化
     * @param {*} event 
     */
    clickTitleTags: function (event) {
        var showFileList = event.currentTarget.dataset.item; //点击标签传进来标签所对应的值；
        var index = event.currentTarget.dataset.index; //选中第几个标签;
        this.setData({
            showFileList: showFileList.productPriceFileViewList, //文件列表的变化;
            currentImgIndex: index, //图片跳转到标签对应的图片;
            selectedTitleTag: showFileList, //改变选中的title
        });
    },

    //动态修改swiper的高度;
    imageLoad: function (e) {
        var imgwidth = e.detail.width; //获取图片实际宽度
        var imgheight = e.detail.height; //获取图片实际高度;
        imgheight = 750 / imgwidth * imgheight;
        this.setData({
            imgheights: imgheight,
        })
    },

    /**
     * 底部导航栏界面跳转
     * @param {*} event 
     */
    navBottomJump: function (event) {
        event.currentTarget.dataset.pagetype = 'noreturn';
        template.navBottomJump(event);
    },
    /**
     * 滚动响应事件 没啥用避免报错的;
     */
    preventD: function () {},

})