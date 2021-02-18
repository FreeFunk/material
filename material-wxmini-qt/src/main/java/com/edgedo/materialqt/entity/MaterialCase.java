package com.edgedo.materialqt.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_case")
public class MaterialCase implements Serializable{
	
		
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
	 * 属性描述:类型(案例：CASE，色卡：COLOR_MAP)
	 */
	@TableField(value="TYPE",exist=true)
	java.lang.String type;
	
	/**
	 * 属性描述:案例分类ID
	 */
	@TableField(value="CASE_CLS_ID",exist=true)
	java.lang.String caseClsId;
	
	/**
	 * 属性描述:案例分类名
	 */
	@TableField(value="CASE_CLS_NAME",exist=true)
	java.lang.String caseClsName;
	
	/**
	 * 属性描述:案例名称
	 */
	@TableField(value="CASE_NAME",exist=true)
	java.lang.String caseName;
	
	/**
	 * 属性描述:案例图编码
	 */
	@TableField(value="CASE_CODE",exist=true)
	java.lang.String caseCode;
	
	/**
	 * 属性描述:案例标签
	 */
	@TableField(value="CASE_LABEL",exist=true)
	java.lang.String caseLabel;
	
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
	 * 属性描述:是否置顶
	 */
	@TableField(value="IS_TOP",exist=true)
	java.lang.String isTop;
	
	/**
	 * 属性描述:是否在发现页隐藏
	 */
	@TableField(value="IS_HIDE",exist=true)
	java.lang.String isHide;
	
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
	 * 属性描述:展示图
	 */
	@TableField(value="IMAGE_HEIGHT",exist=true)
	Integer imageHeight;

	/**
	 * 属性描述:原图
	 */
	@TableField(value="IMAGE_WIDTH",exist=true)
	Integer imageWidth;

	/**
	 * 属性描述:描述
	 */
	@TableField(value="CASE_DESC",exist=true)
	java.lang.String caseDesc;

	public String getCaseDesc() {
		return caseDesc;
	}

	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}

	public Integer getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}

	public Integer getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}

	public String getShowImageUrl() {
		return showImageUrl;
	}

	public void setShowImageUrl(String showImageUrl) {
		this.showImageUrl = showImageUrl;
	}

	public String getOrgImageUrl() {
		return orgImageUrl;
	}

	public void setOrgImageUrl(String orgImageUrl) {
		this.orgImageUrl = orgImageUrl;
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
	
	
	public java.lang.String getType(){
		return this.type;
	}
	
	public void setType(java.lang.String type){
		this.type=type;
	}
	
	
	public java.lang.String getCaseClsId(){
		return this.caseClsId;
	}
	
	public void setCaseClsId(java.lang.String caseClsId){
		this.caseClsId=caseClsId;
	}
	
	
	public java.lang.String getCaseClsName(){
		return this.caseClsName;
	}
	
	public void setCaseClsName(java.lang.String caseClsName){
		this.caseClsName=caseClsName;
	}
	
	
	public java.lang.String getCaseName(){
		return this.caseName;
	}
	
	public void setCaseName(java.lang.String caseName){
		this.caseName=caseName;
	}
	
	
	public java.lang.String getCaseCode(){
		return this.caseCode;
	}
	
	public void setCaseCode(java.lang.String caseCode){
		this.caseCode=caseCode;
	}
	
	
	public java.lang.String getCaseLabel(){
		return this.caseLabel;
	}
	
	public void setCaseLabel(java.lang.String caseLabel){
		this.caseLabel=caseLabel;
	}
	
	
	public java.lang.String getCaseMiniImg(){
		return this.caseMiniImg;
	}
	
	public void setCaseMiniImg(java.lang.String caseMiniImg){
		this.caseMiniImg=caseMiniImg;
	}
	
	
	public java.lang.String getIsTop(){
		return this.isTop;
	}
	
	public void setIsTop(java.lang.String isTop){
		this.isTop=isTop;
	}
	
	
	public java.lang.String getIsHide(){
		return this.isHide;
	}
	
	public void setIsHide(java.lang.String isHide){
		this.isHide=isHide;
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
			sb.append(", materialId=").append(materialId);			
			sb.append(", materialName=").append(materialName);			
			sb.append(", type=").append(type);			
			sb.append(", caseClsId=").append(caseClsId);			
			sb.append(", caseClsName=").append(caseClsName);			
			sb.append(", caseName=").append(caseName);			
			sb.append(", caseCode=").append(caseCode);			
			sb.append(", caseLabel=").append(caseLabel);			
			sb.append(", caseMiniImg=").append(caseMiniImg);			
			sb.append(", isTop=").append(isTop);			
			sb.append(", isHide=").append(isHide);			
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
        MaterialCase other = (MaterialCase) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getMaterialId() == null ? other.getId() == null : this.getMaterialId().equals(other.getMaterialId()))		
				        		&&(this.getMaterialName() == null ? other.getId() == null : this.getMaterialName().equals(other.getMaterialName()))		
				        		&&(this.getType() == null ? other.getId() == null : this.getType().equals(other.getType()))		
				        		&&(this.getCaseClsId() == null ? other.getId() == null : this.getCaseClsId().equals(other.getCaseClsId()))		
				        		&&(this.getCaseClsName() == null ? other.getId() == null : this.getCaseClsName().equals(other.getCaseClsName()))		
				        		&&(this.getCaseName() == null ? other.getId() == null : this.getCaseName().equals(other.getCaseName()))		
				        		&&(this.getCaseCode() == null ? other.getId() == null : this.getCaseCode().equals(other.getCaseCode()))		
				        		&&(this.getCaseLabel() == null ? other.getId() == null : this.getCaseLabel().equals(other.getCaseLabel()))		
				        		&&(this.getCaseMiniImg() == null ? other.getId() == null : this.getCaseMiniImg().equals(other.getCaseMiniImg()))		
				        		&&(this.getIsTop() == null ? other.getId() == null : this.getIsTop().equals(other.getIsTop()))		
				        		&&(this.getIsHide() == null ? other.getId() == null : this.getIsHide().equals(other.getIsHide()))		
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
		        result = prime * result + ((getMaterialId() == null) ? 0 : getMaterialId().hashCode());		
		        result = prime * result + ((getMaterialName() == null) ? 0 : getMaterialName().hashCode());		
		        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());		
		        result = prime * result + ((getCaseClsId() == null) ? 0 : getCaseClsId().hashCode());		
		        result = prime * result + ((getCaseClsName() == null) ? 0 : getCaseClsName().hashCode());		
		        result = prime * result + ((getCaseName() == null) ? 0 : getCaseName().hashCode());		
		        result = prime * result + ((getCaseCode() == null) ? 0 : getCaseCode().hashCode());		
		        result = prime * result + ((getCaseLabel() == null) ? 0 : getCaseLabel().hashCode());		
		        result = prime * result + ((getCaseMiniImg() == null) ? 0 : getCaseMiniImg().hashCode());		
		        result = prime * result + ((getIsTop() == null) ? 0 : getIsTop().hashCode());		
		        result = prime * result + ((getIsHide() == null) ? 0 : getIsHide().hashCode());		
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
