const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}
const previewImage = event =>{
    var imageUrl = event.currentTarget.dataset.imageurl;
    var previewImgList = event.currentTarget.dataset.previewImgList;
    wx.previewImage({
        current: getApp().globalData.imageHost + imageUrl, // 当前显示图片的http链接
        urls: previewImgList // 需要预览的图片http链接列表
    })
}


module.exports = {
  formatTime: formatTime
}
