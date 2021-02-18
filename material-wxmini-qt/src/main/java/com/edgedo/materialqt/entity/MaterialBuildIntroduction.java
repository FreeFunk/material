package com.edgedo.materialqt.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_build_introduction")
public class MaterialBuildIntroduction implements Serializable{
	
		
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
	 * 属性描述:施工说明标题
	 */
	@TableField(value="BUILD_TITLE",exist=true)
	java.lang.String buildTitle;
	
	/**
	 * 属性描述:施工说明摘要
	 */
	@TableField(value="BUILD_ABSTRACT",exist=true)
	java.lang.String buildAbstract;
	
	/**
	 * 属性描述:施工说明内容
	 */
	@TableField(value="BUILD_CONTENT",exist=true)
	java.lang.String buildContent;
	
	/**
	 * 属性描述:文件类型
	 */
	@TableField(value="FILE_TYPE",exist=true)
	java.lang.String fileType;
	
	/**
	 * 属性描述:文件地址
	 */
	@TableField(value="FILE_URL",exist=true)
	java.lang.String fileUrl;
	
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
	
	
	public java.lang.String getBuildTitle(){
		return this.buildTitle;
	}
	
	public void setBuildTitle(java.lang.String buildTitle){
		this.buildTitle=buildTitle;
	}
	
	
	public java.lang.String getBuildAbstract(){
		return this.buildAbstract;
	}
	
	public void setBuildAbstract(java.lang.String buildAbstract){
		this.buildAbstract=buildAbstract;
	}
	
	
	public java.lang.String getBuildContent(){
		return this.buildContent;
	}
	
	public void setBuildContent(java.lang.String buildContent){
		this.buildContent=buildContent;
	}
	
	
	public java.lang.String getFileType(){
		return this.fileType;
	}
	
	public void setFileType(java.lang.String fileType){
		this.fileType=fileType;
	}
	
	
	public java.lang.String getFileUrl(){
		return this.fileUrl;
	}
	
	public void setFileUrl(java.lang.String fileUrl){
		this.fileUrl=fileUrl;
	}
	
	
	public java.lang.String getDataState(){
		return this.dataState;
	}
	
	public void setDataState(java.lang.String dataState){
		this.dataState=dataState;
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
			sb.append(", buildTitle=").append(buildTitle);			
			sb.append(", buildAbstract=").append(buildAbstract);			
			sb.append(", buildContent=").append(buildContent);			
			sb.append(", fileType=").append(fileType);			
			sb.append(", fileUrl=").append(fileUrl);			
			sb.append(", dataState=").append(dataState);			
			sb.append(", shState=").append(shState);			
			sb.append(", notPassText=").append(notPassText);			
			sb.append(", shUserId=").append(shUserId);			
			sb.append(", shUserName=").append(shUserName);			
			sb.append(", shTime=").append(shTime);			
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
        MaterialBuildIntroduction other = (MaterialBuildIntroduction) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getMaterialId() == null ? other.getId() == null : this.getMaterialId().equals(other.getMaterialId()))		
				        		&&(this.getMaterialName() == null ? other.getId() == null : this.getMaterialName().equals(other.getMaterialName()))		
				        		&&(this.getBuildTitle() == null ? other.getId() == null : this.getBuildTitle().equals(other.getBuildTitle()))		
				        		&&(this.getBuildAbstract() == null ? other.getId() == null : this.getBuildAbstract().equals(other.getBuildAbstract()))		
				        		&&(this.getBuildContent() == null ? other.getId() == null : this.getBuildContent().equals(other.getBuildContent()))		
				        		&&(this.getFileType() == null ? other.getId() == null : this.getFileType().equals(other.getFileType()))		
				        		&&(this.getFileUrl() == null ? other.getId() == null : this.getFileUrl().equals(other.getFileUrl()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				        		&&(this.getShState() == null ? other.getId() == null : this.getShState().equals(other.getShState()))		
				        		&&(this.getNotPassText() == null ? other.getId() == null : this.getNotPassText().equals(other.getNotPassText()))		
				        		&&(this.getShUserId() == null ? other.getId() == null : this.getShUserId().equals(other.getShUserId()))		
				        		&&(this.getShUserName() == null ? other.getId() == null : this.getShUserName().equals(other.getShUserName()))		
				        		&&(this.getShTime() == null ? other.getId() == null : this.getShTime().equals(other.getShTime()))		
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
		        result = prime * result + ((getBuildTitle() == null) ? 0 : getBuildTitle().hashCode());		
		        result = prime * result + ((getBuildAbstract() == null) ? 0 : getBuildAbstract().hashCode());		
		        result = prime * result + ((getBuildContent() == null) ? 0 : getBuildContent().hashCode());		
		        result = prime * result + ((getFileType() == null) ? 0 : getFileType().hashCode());		
		        result = prime * result + ((getFileUrl() == null) ? 0 : getFileUrl().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		        result = prime * result + ((getShState() == null) ? 0 : getShState().hashCode());		
		        result = prime * result + ((getNotPassText() == null) ? 0 : getNotPassText().hashCode());		
		        result = prime * result + ((getShUserId() == null) ? 0 : getShUserId().hashCode());		
		        result = prime * result + ((getShUserName() == null) ? 0 : getShUserName().hashCode());		
		        result = prime * result + ((getShTime() == null) ? 0 : getShTime().hashCode());		
		;
        return result;
    }

}
