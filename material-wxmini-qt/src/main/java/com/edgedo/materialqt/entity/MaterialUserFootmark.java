package com.edgedo.materialqt.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_user_footmark")
public class MaterialUserFootmark implements Serializable{
	
		
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
	 * 属性描述:足迹分类(案例：CASE，色卡：COLOR_MAP)
	 */
	@TableField(value="FOOTMARK_CLS",exist=true)
	java.lang.String footmarkCls;
	
	/**
	 * 属性描述:关联分类ID
	 */
	@TableField(value="RELATION_ID",exist=true)
	java.lang.String relationId;
	
	/**
	 * 属性描述:足迹标题
	 */
	@TableField(value="FOOTMARK_TITLE",exist=true)
	java.lang.String footmarkTitle;
	
	/**
	 * 属性描述:封面图
	 */
	@TableField(value="IMAGE_URL",exist=true)
	java.lang.String imageUrl;



	/**
	 * 属性描述:图片高度
	 */
	@TableField(value="IMAGE_HEIGHT",exist=true)
	Integer imageHeight;

	/**
	 * 属性描述:图片宽度
	 */
	@TableField(value="IMAGE_WIDTH",exist=true)
	Integer imageWidth;

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
	
	
	public java.lang.String getFootmarkCls(){
		return this.footmarkCls;
	}
	
	public void setFootmarkCls(java.lang.String footmarkCls){
		this.footmarkCls=footmarkCls;
	}
	
	
	public java.lang.String getRelationId(){
		return this.relationId;
	}
	
	public void setRelationId(java.lang.String relationId){
		this.relationId=relationId;
	}
	
	
	public java.lang.String getFootmarkTitle(){
		return this.footmarkTitle;
	}
	
	public void setFootmarkTitle(java.lang.String footmarkTitle){
		this.footmarkTitle=footmarkTitle;
	}
	
	
	public java.lang.String getImageUrl(){
		return this.imageUrl;
	}
	
	public void setImageUrl(java.lang.String imageUrl){
		this.imageUrl=imageUrl;
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
			sb.append(", footmarkCls=").append(footmarkCls);			
			sb.append(", relationId=").append(relationId);			
			sb.append(", footmarkTitle=").append(footmarkTitle);			
			sb.append(", imageUrl=").append(imageUrl);			
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
        MaterialUserFootmark other = (MaterialUserFootmark) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getFootmarkCls() == null ? other.getId() == null : this.getFootmarkCls().equals(other.getFootmarkCls()))		
				        		&&(this.getRelationId() == null ? other.getId() == null : this.getRelationId().equals(other.getRelationId()))		
				        		&&(this.getFootmarkTitle() == null ? other.getId() == null : this.getFootmarkTitle().equals(other.getFootmarkTitle()))		
				        		&&(this.getImageUrl() == null ? other.getId() == null : this.getImageUrl().equals(other.getImageUrl()))		
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
		        result = prime * result + ((getFootmarkCls() == null) ? 0 : getFootmarkCls().hashCode());		
		        result = prime * result + ((getRelationId() == null) ? 0 : getRelationId().hashCode());		
		        result = prime * result + ((getFootmarkTitle() == null) ? 0 : getFootmarkTitle().hashCode());		
		        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());		
		;
        return result;
    }

}
