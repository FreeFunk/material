package com.edgedo.material.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_user_collection")
public class MaterialUserCollection implements Serializable{
	
		
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
	 * 属性描述:收藏分类(案例：CASE，色卡：COLOR_MAP)
	 */
	@TableField(value="COLLECTION_CLS",exist=true)
	java.lang.String collectionCls;
	
	/**
	 * 属性描述:关联分类ID
	 */
	@TableField(value="RELATION_ID",exist=true)
	java.lang.String relationId;
	
	/**
	 * 属性描述:收藏标题
	 */
	@TableField(value="COLLECTION_TITLE",exist=true)
	java.lang.String collectionTitle;
	
	/**
	 * 属性描述:封面图
	 */
	@TableField(value="IMAGE_URL",exist=true)
	java.lang.String imageUrl;
	
	
	
	
	
	
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
	
	
	public java.lang.String getCollectionCls(){
		return this.collectionCls;
	}
	
	public void setCollectionCls(java.lang.String collectionCls){
		this.collectionCls=collectionCls;
	}
	
	
	public java.lang.String getRelationId(){
		return this.relationId;
	}
	
	public void setRelationId(java.lang.String relationId){
		this.relationId=relationId;
	}
	
	
	public java.lang.String getCollectionTitle(){
		return this.collectionTitle;
	}
	
	public void setCollectionTitle(java.lang.String collectionTitle){
		this.collectionTitle=collectionTitle;
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
			sb.append(", collectionCls=").append(collectionCls);			
			sb.append(", relationId=").append(relationId);			
			sb.append(", collectionTitle=").append(collectionTitle);			
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
        MaterialUserCollection other = (MaterialUserCollection) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getCollectionCls() == null ? other.getId() == null : this.getCollectionCls().equals(other.getCollectionCls()))		
				        		&&(this.getRelationId() == null ? other.getId() == null : this.getRelationId().equals(other.getRelationId()))		
				        		&&(this.getCollectionTitle() == null ? other.getId() == null : this.getCollectionTitle().equals(other.getCollectionTitle()))		
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
		        result = prime * result + ((getCollectionCls() == null) ? 0 : getCollectionCls().hashCode());		
		        result = prime * result + ((getRelationId() == null) ? 0 : getRelationId().hashCode());		
		        result = prime * result + ((getCollectionTitle() == null) ? 0 : getCollectionTitle().hashCode());		
		        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());		
		;
        return result;
    }

}
