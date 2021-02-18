package com.edgedo.materialqt.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material")
public class Material implements Serializable{
	
		
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
	 * 属性描述:所属材料分类ID
	 */
	@TableField(value="MATERIAL_CLS_ID",exist=true)
	java.lang.String materialClsId;
	
	/**
	 * 属性描述:所属材料分类名
	 */
	@TableField(value="MATERIAL_CLS_NAME",exist=true)
	java.lang.String materialClsName;
	
	/**
	 * 属性描述:材料名称
	 */
	@TableField(value="MATERIAL_NAME",exist=true)
	java.lang.String materialName;
	
	/**
	 * 属性描述:材料标签
	 */
	@TableField(value="MATERIAL_LABEL",exist=true)
	java.lang.String materialLabel;
	
	/**
	 * 属性描述:产品报价图
	 */
	@TableField(value="PRODUCT_PRICE_IMAGE",exist=true)
	java.lang.String productPriceImage;
	
	/**
	 * 属性描述:产品介绍图
	 */
	@TableField(value="PRODUCT_INTRODUCTION_IMAGE",exist=true)
	java.lang.String productIntroductionImage;
	
	/**
	 * 属性描述:施工说明图
	 */
	@TableField(value="BUILD_INTRODUCTION_IMAGE",exist=true)
	java.lang.String buildIntroductionImage;
	
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
	 * 属性描述:是否展示产品样式
	 */
	@TableField(value="IS_SHOW_PRODUCT_STYLE",exist=true)
	java.lang.String isShowProductStyle;


	public String getIsShowProductStyle() {
		return isShowProductStyle;
	}

	public void setIsShowProductStyle(String isShowProductStyle) {
		this.isShowProductStyle = isShowProductStyle;
	}

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
	
	
	public java.lang.String getMaterialClsId(){
		return this.materialClsId;
	}
	
	public void setMaterialClsId(java.lang.String materialClsId){
		this.materialClsId=materialClsId;
	}
	
	
	public java.lang.String getMaterialClsName(){
		return this.materialClsName;
	}
	
	public void setMaterialClsName(java.lang.String materialClsName){
		this.materialClsName=materialClsName;
	}
	
	
	public java.lang.String getMaterialName(){
		return this.materialName;
	}
	
	public void setMaterialName(java.lang.String materialName){
		this.materialName=materialName;
	}
	
	
	public java.lang.String getMaterialLabel(){
		return this.materialLabel;
	}
	
	public void setMaterialLabel(java.lang.String materialLabel){
		this.materialLabel=materialLabel;
	}
	
	
	public java.lang.String getProductPriceImage(){
		return this.productPriceImage;
	}
	
	public void setProductPriceImage(java.lang.String productPriceImage){
		this.productPriceImage=productPriceImage;
	}
	
	
	public java.lang.String getProductIntroductionImage(){
		return this.productIntroductionImage;
	}
	
	public void setProductIntroductionImage(java.lang.String productIntroductionImage){
		this.productIntroductionImage=productIntroductionImage;
	}
	
	
	public java.lang.String getBuildIntroductionImage(){
		return this.buildIntroductionImage;
	}
	
	public void setBuildIntroductionImage(java.lang.String buildIntroductionImage){
		this.buildIntroductionImage=buildIntroductionImage;
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
			sb.append(", materialClsId=").append(materialClsId);			
			sb.append(", materialClsName=").append(materialClsName);			
			sb.append(", materialName=").append(materialName);			
			sb.append(", materialLabel=").append(materialLabel);			
			sb.append(", productPriceImage=").append(productPriceImage);			
			sb.append(", productIntroductionImage=").append(productIntroductionImage);			
			sb.append(", buildIntroductionImage=").append(buildIntroductionImage);			
			sb.append(", orderNum=").append(orderNum);			
			sb.append(", isEnable=").append(isEnable);			
			sb.append(", shState=").append(shState);			
			sb.append(", notPassText=").append(notPassText);			
			sb.append(", shUserId=").append(shUserId);			
			sb.append(", shUserName=").append(shUserName);			
			sb.append(", shTime=").append(shTime);			
			sb.append(", dataState=").append(dataState);			
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
        Material other = (Material) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getMaterialClsId() == null ? other.getId() == null : this.getMaterialClsId().equals(other.getMaterialClsId()))		
				        		&&(this.getMaterialClsName() == null ? other.getId() == null : this.getMaterialClsName().equals(other.getMaterialClsName()))		
				        		&&(this.getMaterialName() == null ? other.getId() == null : this.getMaterialName().equals(other.getMaterialName()))		
				        		&&(this.getMaterialLabel() == null ? other.getId() == null : this.getMaterialLabel().equals(other.getMaterialLabel()))		
				        		&&(this.getProductPriceImage() == null ? other.getId() == null : this.getProductPriceImage().equals(other.getProductPriceImage()))		
				        		&&(this.getProductIntroductionImage() == null ? other.getId() == null : this.getProductIntroductionImage().equals(other.getProductIntroductionImage()))		
				        		&&(this.getBuildIntroductionImage() == null ? other.getId() == null : this.getBuildIntroductionImage().equals(other.getBuildIntroductionImage()))		
				        		&&(this.getOrderNum() == null ? other.getId() == null : this.getOrderNum().equals(other.getOrderNum()))		
				        		&&(this.getIsEnable() == null ? other.getId() == null : this.getIsEnable().equals(other.getIsEnable()))		
				        		&&(this.getShState() == null ? other.getId() == null : this.getShState().equals(other.getShState()))		
				        		&&(this.getNotPassText() == null ? other.getId() == null : this.getNotPassText().equals(other.getNotPassText()))		
				        		&&(this.getShUserId() == null ? other.getId() == null : this.getShUserId().equals(other.getShUserId()))		
				        		&&(this.getShUserName() == null ? other.getId() == null : this.getShUserName().equals(other.getShUserName()))		
				        		&&(this.getShTime() == null ? other.getId() == null : this.getShTime().equals(other.getShTime()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
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
		        result = prime * result + ((getMaterialClsId() == null) ? 0 : getMaterialClsId().hashCode());		
		        result = prime * result + ((getMaterialClsName() == null) ? 0 : getMaterialClsName().hashCode());		
		        result = prime * result + ((getMaterialName() == null) ? 0 : getMaterialName().hashCode());		
		        result = prime * result + ((getMaterialLabel() == null) ? 0 : getMaterialLabel().hashCode());		
		        result = prime * result + ((getProductPriceImage() == null) ? 0 : getProductPriceImage().hashCode());		
		        result = prime * result + ((getProductIntroductionImage() == null) ? 0 : getProductIntroductionImage().hashCode());		
		        result = prime * result + ((getBuildIntroductionImage() == null) ? 0 : getBuildIntroductionImage().hashCode());		
		        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());		
		        result = prime * result + ((getIsEnable() == null) ? 0 : getIsEnable().hashCode());		
		        result = prime * result + ((getShState() == null) ? 0 : getShState().hashCode());		
		        result = prime * result + ((getNotPassText() == null) ? 0 : getNotPassText().hashCode());		
		        result = prime * result + ((getShUserId() == null) ? 0 : getShUserId().hashCode());		
		        result = prime * result + ((getShUserName() == null) ? 0 : getShUserName().hashCode());		
		        result = prime * result + ((getShTime() == null) ? 0 : getShTime().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
