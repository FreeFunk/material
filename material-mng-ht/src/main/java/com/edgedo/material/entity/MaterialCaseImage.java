package com.edgedo.material.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_case_image")
public class MaterialCaseImage implements Serializable{
	
		
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
	 * 属性描述:所属精品案例ID
	 */
	@TableField(value="CASE_ID",exist=true)
	java.lang.String caseId;
	
	/**
	 * 属性描述:缩略图
	 */
	@TableField(value="CASE_MINI_IMG",exist=true)
	java.lang.String caseMiniImg;
	
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
	 * 属性描述:排序号
	 */
	@TableField(value="ORDER_NUM",exist=true)
	java.math.BigDecimal orderNum;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;
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
	 * 属性描述:审核人名
	 */
	@TableField(value="IMAGE_TITLE",exist=true)
	java.lang.String imageTitle;


	public String getShState() {
		return shState;
	}

	public void setShState(String shState) {
		this.shState = shState;
	}

	public String getNotPassText() {
		return notPassText;
	}

	public void setNotPassText(String notPassText) {
		this.notPassText = notPassText;
	}

	public String getShUserId() {
		return shUserId;
	}

	public void setShUserId(String shUserId) {
		this.shUserId = shUserId;
	}

	public String getShUserName() {
		return shUserName;
	}

	public void setShUserName(String shUserName) {
		this.shUserName = shUserName;
	}

	public Date getShTime() {
		return shTime;
	}

	public void setShTime(Date shTime) {
		this.shTime = shTime;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
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
	
	
	public java.lang.String getCaseId(){
		return this.caseId;
	}
	
	public void setCaseId(java.lang.String caseId){
		this.caseId=caseId;
	}
	
	
	public java.lang.String getCaseMiniImg(){
		return this.caseMiniImg;
	}
	
	public void setCaseMiniImg(java.lang.String caseMiniImg){
		this.caseMiniImg=caseMiniImg;
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
	
	
	public java.math.BigDecimal getOrderNum(){
		return this.orderNum;
	}
	
	public void setOrderNum(java.math.BigDecimal orderNum){
		this.orderNum=orderNum;
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
			sb.append(", caseId=").append(caseId);			
			sb.append(", caseMiniImg=").append(caseMiniImg);			
			sb.append(", showImageUrl=").append(showImageUrl);			
			sb.append(", orgImageUrl=").append(orgImageUrl);			
			sb.append(", orderNum=").append(orderNum);			
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
        MaterialCaseImage other = (MaterialCaseImage) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCaseId() == null ? other.getId() == null : this.getCaseId().equals(other.getCaseId()))		
				        		&&(this.getCaseMiniImg() == null ? other.getId() == null : this.getCaseMiniImg().equals(other.getCaseMiniImg()))		
				        		&&(this.getShowImageUrl() == null ? other.getId() == null : this.getShowImageUrl().equals(other.getShowImageUrl()))		
				        		&&(this.getOrgImageUrl() == null ? other.getId() == null : this.getOrgImageUrl().equals(other.getOrgImageUrl()))		
				        		&&(this.getOrderNum() == null ? other.getId() == null : this.getOrderNum().equals(other.getOrderNum()))		
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
		        result = prime * result + ((getCaseId() == null) ? 0 : getCaseId().hashCode());		
		        result = prime * result + ((getCaseMiniImg() == null) ? 0 : getCaseMiniImg().hashCode());		
		        result = prime * result + ((getShowImageUrl() == null) ? 0 : getShowImageUrl().hashCode());		
		        result = prime * result + ((getOrgImageUrl() == null) ? 0 : getOrgImageUrl().hashCode());		
		        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
