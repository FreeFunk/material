package com.edgedo.material.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_product_style")
public class MaterialProductStyle implements Serializable{
	
		
	/**
	 * 属性描述:主键
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:创建时间
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;
	
	/**
	 * 属性描述:创建人ID
	 */
	@TableField(value="CREATE_USER_ID",exist=true)
	java.lang.String createUserId;
	
	/**
	 * 属性描述:创建人名
	 */
	@TableField(value="CREATE_USER_NAME",exist=true)
	java.lang.String createUserName;
	
	/**
	 * 属性描述:所属材料ID
	 */
	@TableField(value="MATERIAL_ID",exist=true)
	java.lang.String materialId;
	
	/**
	 * 属性描述:所属材料名
	 */
	@TableField(value="MATERIAL_NAME",exist=true)
	java.lang.String materialName;
	
	/**
	 * 属性描述:产品介绍类型(1：产品图片:2：产品资料)
	 */
	@TableField(value="PRODUCT_TYPE",exist=true)
	java.lang.String productType;
	
	/**
	 * 属性描述:分类名
	 */
	@TableField(value="STYLE_NAME",exist=true)
	java.lang.String styleName;
	
	/**
	 * 属性描述:缩略图
	 */
	@TableField(value="MINI_IMG",exist=true)
	java.lang.String miniImg;
	
	/**
	 * 属性描述:展示图
	 */
	@TableField(value="SHOW_IMAGE_URL",exist=true)
	java.lang.String showImageUrl;
	
	/**
	 * 属性描述:原图
	 */
	@TableField(value="ORG_IMAGE_URL",exist=true)
	java.lang.String orgImageUrl;
	
	/**
	 * 属性描述:文件类型
	 */
	@TableField(value="FILE_TYPE",exist=true)
	java.lang.String fileType;
	
	/**
	 * 属性描述:文件名
	 */
	@TableField(value="FILE_NAME",exist=true)
	java.lang.String fileName;
	
	/**
	 * 属性描述:文件地址
	 */
	@TableField(value="FILE_URL",exist=true)
	java.lang.String fileUrl;
	
	/**
	 * 属性描述:是否在首页展示
	 */
	@TableField(value="IS_SHOW_HOME",exist=true)
	java.lang.String isShowHome;
	
	/**
	 * 属性描述:排序号
	 */
	@TableField(value="ORDER_NUM",exist=true)
	java.math.BigDecimal orderNum;
	
	/**
	 * 属性描述:是否启用
	 */
	@TableField(value="IS_ENABLE",exist=true)
	java.lang.String isEnable;
	
	/**
	 * 属性描述:审核状态(审核中：0，通过：1，未通过：-1)
	 */
	@TableField(value="SH_STATE",exist=true)
	java.lang.String shState;
	
	/**
	 * 属性描述:未通过原因
	 */
	@TableField(value="NOT_PASS_TEXT",exist=true)
	java.lang.String notPassText;
	
	/**
	 * 属性描述:审核人ID
	 */
	@TableField(value="SH_USER_ID",exist=true)
	java.lang.String shUserId;
	
	/**
	 * 属性描述:审核人名
	 */
	@TableField(value="SH_USER_NAME",exist=true)
	java.lang.String shUserName;
	
	/**
	 * 属性描述:审核时间
	 */
	@TableField(value="SH_TIME",exist=true)
	java.util.Date shTime;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;
	
	/**
	 * 属性描述:图片高度
	 */
	@TableField(value="IMAGE_HEIGHT",exist=true)
	java.lang.Integer imageHeight;
	
	/**
	 * 属性描述:图片宽度
	 */
	@TableField(value="IMAGE_WIDTH",exist=true)
	java.lang.Integer imageWidth;
	
	
	
	
	
	
	public java.lang.String getId(){
		return this.id;
	}
	
	public void setId(java.lang.String id){
		this.id=id;
	}
	
	
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}
	
	
	public java.lang.String getCreateUserId(){
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.String createUserId){
		this.createUserId=createUserId;
	}
	
	
	public java.lang.String getCreateUserName(){
		return this.createUserName;
	}
	
	public void setCreateUserName(java.lang.String createUserName){
		this.createUserName=createUserName;
	}
	
	
	public java.lang.String getMaterialId(){
		return this.materialId;
	}
	
	public void setMaterialId(java.lang.String materialId){
		this.materialId=materialId;
	}
	
	
	public java.lang.String getMaterialName(){
		return this.materialName;
	}
	
	public void setMaterialName(java.lang.String materialName){
		this.materialName=materialName;
	}
	
	
	public java.lang.String getProductType(){
		return this.productType;
	}
	
	public void setProductType(java.lang.String productType){
		this.productType=productType;
	}
	
	
	public java.lang.String getStyleName(){
		return this.styleName;
	}
	
	public void setStyleName(java.lang.String styleName){
		this.styleName=styleName;
	}
	
	
	public java.lang.String getMiniImg(){
		return this.miniImg;
	}
	
	public void setMiniImg(java.lang.String miniImg){
		this.miniImg=miniImg;
	}
	
	
	public java.lang.String getShowImageUrl(){
		return this.showImageUrl;
	}
	
	public void setShowImageUrl(java.lang.String showImageUrl){
		this.showImageUrl=showImageUrl;
	}
	
	
	public java.lang.String getOrgImageUrl(){
		return this.orgImageUrl;
	}
	
	public void setOrgImageUrl(java.lang.String orgImageUrl){
		this.orgImageUrl=orgImageUrl;
	}
	
	
	public java.lang.String getFileType(){
		return this.fileType;
	}
	
	public void setFileType(java.lang.String fileType){
		this.fileType=fileType;
	}
	
	
	public java.lang.String getFileName(){
		return this.fileName;
	}
	
	public void setFileName(java.lang.String fileName){
		this.fileName=fileName;
	}
	
	
	public java.lang.String getFileUrl(){
		return this.fileUrl;
	}
	
	public void setFileUrl(java.lang.String fileUrl){
		this.fileUrl=fileUrl;
	}
	
	
	public java.lang.String getIsShowHome(){
		return this.isShowHome;
	}
	
	public void setIsShowHome(java.lang.String isShowHome){
		this.isShowHome=isShowHome;
	}
	
	
	public java.math.BigDecimal getOrderNum(){
		return this.orderNum;
	}
	
	public void setOrderNum(java.math.BigDecimal orderNum){
		this.orderNum=orderNum;
	}
	
	
	public java.lang.String getIsEnable(){
		return this.isEnable;
	}
	
	public void setIsEnable(java.lang.String isEnable){
		this.isEnable=isEnable;
	}
	
	
	public java.lang.String getShState(){
		return this.shState;
	}
	
	public void setShState(java.lang.String shState){
		this.shState=shState;
	}
	
	
	public java.lang.String getNotPassText(){
		return this.notPassText;
	}
	
	public void setNotPassText(java.lang.String notPassText){
		this.notPassText=notPassText;
	}
	
	
	public java.lang.String getShUserId(){
		return this.shUserId;
	}
	
	public void setShUserId(java.lang.String shUserId){
		this.shUserId=shUserId;
	}
	
	
	public java.lang.String getShUserName(){
		return this.shUserName;
	}
	
	public void setShUserName(java.lang.String shUserName){
		this.shUserName=shUserName;
	}
	
	
	public java.util.Date getShTime(){
		return this.shTime;
	}
	
	public void setShTime(java.util.Date shTime){
		this.shTime=shTime;
	}
	
	
	public java.lang.String getDataState(){
		return this.dataState;
	}
	
	public void setDataState(java.lang.String dataState){
		this.dataState=dataState;
	}
	
	
	public java.lang.Integer getImageHeight(){
		return this.imageHeight;
	}
	
	public void setImageHeight(java.lang.Integer imageHeight){
		this.imageHeight=imageHeight;
	}
	
	
	public java.lang.Integer getImageWidth(){
		return this.imageWidth;
	}
	
	public void setImageWidth(java.lang.Integer imageWidth){
		this.imageWidth=imageWidth;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", createTime=").append(createTime);			
			sb.append(", createUserId=").append(createUserId);			
			sb.append(", createUserName=").append(createUserName);			
			sb.append(", materialId=").append(materialId);			
			sb.append(", materialName=").append(materialName);			
			sb.append(", productType=").append(productType);			
			sb.append(", styleName=").append(styleName);			
			sb.append(", miniImg=").append(miniImg);			
			sb.append(", showImageUrl=").append(showImageUrl);			
			sb.append(", orgImageUrl=").append(orgImageUrl);			
			sb.append(", fileType=").append(fileType);			
			sb.append(", fileName=").append(fileName);			
			sb.append(", fileUrl=").append(fileUrl);			
			sb.append(", isShowHome=").append(isShowHome);			
			sb.append(", orderNum=").append(orderNum);			
			sb.append(", isEnable=").append(isEnable);			
			sb.append(", shState=").append(shState);			
			sb.append(", notPassText=").append(notPassText);			
			sb.append(", shUserId=").append(shUserId);			
			sb.append(", shUserName=").append(shUserName);			
			sb.append(", shTime=").append(shTime);			
			sb.append(", dataState=").append(dataState);			
			sb.append(", imageHeight=").append(imageHeight);			
			sb.append(", imageWidth=").append(imageWidth);			
        sb.append("]");
        return sb.toString();
    }

   
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MaterialProductStyle other = (MaterialProductStyle) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getMaterialId() == null ? other.getId() == null : this.getMaterialId().equals(other.getMaterialId()))		
				        		&&(this.getMaterialName() == null ? other.getId() == null : this.getMaterialName().equals(other.getMaterialName()))		
				        		&&(this.getProductType() == null ? other.getId() == null : this.getProductType().equals(other.getProductType()))		
				        		&&(this.getStyleName() == null ? other.getId() == null : this.getStyleName().equals(other.getStyleName()))		
				        		&&(this.getMiniImg() == null ? other.getId() == null : this.getMiniImg().equals(other.getMiniImg()))		
				        		&&(this.getShowImageUrl() == null ? other.getId() == null : this.getShowImageUrl().equals(other.getShowImageUrl()))		
				        		&&(this.getOrgImageUrl() == null ? other.getId() == null : this.getOrgImageUrl().equals(other.getOrgImageUrl()))		
				        		&&(this.getFileType() == null ? other.getId() == null : this.getFileType().equals(other.getFileType()))		
				        		&&(this.getFileName() == null ? other.getId() == null : this.getFileName().equals(other.getFileName()))		
				        		&&(this.getFileUrl() == null ? other.getId() == null : this.getFileUrl().equals(other.getFileUrl()))		
				        		&&(this.getIsShowHome() == null ? other.getId() == null : this.getIsShowHome().equals(other.getIsShowHome()))		
				        		&&(this.getOrderNum() == null ? other.getId() == null : this.getOrderNum().equals(other.getOrderNum()))		
				        		&&(this.getIsEnable() == null ? other.getId() == null : this.getIsEnable().equals(other.getIsEnable()))		
				        		&&(this.getShState() == null ? other.getId() == null : this.getShState().equals(other.getShState()))		
				        		&&(this.getNotPassText() == null ? other.getId() == null : this.getNotPassText().equals(other.getNotPassText()))		
				        		&&(this.getShUserId() == null ? other.getId() == null : this.getShUserId().equals(other.getShUserId()))		
				        		&&(this.getShUserName() == null ? other.getId() == null : this.getShUserName().equals(other.getShUserName()))		
				        		&&(this.getShTime() == null ? other.getId() == null : this.getShTime().equals(other.getShTime()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				        		&&(this.getImageHeight() == null ? other.getId() == null : this.getImageHeight().equals(other.getImageHeight()))		
				        		&&(this.getImageWidth() == null ? other.getId() == null : this.getImageWidth().equals(other.getImageWidth()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());		
		        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());		
		        result = prime * result + ((getMaterialId() == null) ? 0 : getMaterialId().hashCode());		
		        result = prime * result + ((getMaterialName() == null) ? 0 : getMaterialName().hashCode());		
		        result = prime * result + ((getProductType() == null) ? 0 : getProductType().hashCode());		
		        result = prime * result + ((getStyleName() == null) ? 0 : getStyleName().hashCode());		
		        result = prime * result + ((getMiniImg() == null) ? 0 : getMiniImg().hashCode());		
		        result = prime * result + ((getShowImageUrl() == null) ? 0 : getShowImageUrl().hashCode());		
		        result = prime * result + ((getOrgImageUrl() == null) ? 0 : getOrgImageUrl().hashCode());		
		        result = prime * result + ((getFileType() == null) ? 0 : getFileType().hashCode());		
		        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());		
		        result = prime * result + ((getFileUrl() == null) ? 0 : getFileUrl().hashCode());		
		        result = prime * result + ((getIsShowHome() == null) ? 0 : getIsShowHome().hashCode());		
		        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());		
		        result = prime * result + ((getIsEnable() == null) ? 0 : getIsEnable().hashCode());		
		        result = prime * result + ((getShState() == null) ? 0 : getShState().hashCode());		
		        result = prime * result + ((getNotPassText() == null) ? 0 : getNotPassText().hashCode());		
		        result = prime * result + ((getShUserId() == null) ? 0 : getShUserId().hashCode());		
		        result = prime * result + ((getShUserName() == null) ? 0 : getShUserName().hashCode());		
		        result = prime * result + ((getShTime() == null) ? 0 : getShTime().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		        result = prime * result + ((getImageHeight() == null) ? 0 : getImageHeight().hashCode());		
		        result = prime * result + ((getImageWidth() == null) ? 0 : getImageWidth().hashCode());		
		;
        return result;
    }

}
