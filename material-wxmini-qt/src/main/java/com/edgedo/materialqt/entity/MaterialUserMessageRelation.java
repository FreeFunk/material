package com.edgedo.materialqt.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_user_message_relation")
public class MaterialUserMessageRelation implements Serializable{
	
		
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
	 * 属性描述:用户ID
	 */
	@TableField(value="USER_ID",exist=true)
	java.lang.String userId;
	
	/**
	 * 属性描述:通告id
	 */
	@TableField(value="SYS_MESSAGE_ID",exist=true)
	java.lang.String sysMessageId;
	
	/**
	 * 属性描述:是否已读
	 */
	@TableField(value="IS_READ",exist=true)
	java.lang.String isRead;
	
	
	
	
	
	
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
	
	
	public java.lang.String getUserId(){
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId){
		this.userId=userId;
	}
	
	
	public java.lang.String getSysMessageId(){
		return this.sysMessageId;
	}
	
	public void setSysMessageId(java.lang.String sysMessageId){
		this.sysMessageId=sysMessageId;
	}
	
	
	public java.lang.String getIsRead(){
		return this.isRead;
	}
	
	public void setIsRead(java.lang.String isRead){
		this.isRead=isRead;
	}
	
	
	
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
			sb.append(", id=").append(id);			
			sb.append(", createTime=").append(createTime);			
			sb.append(", userId=").append(userId);			
			sb.append(", sysMessageId=").append(sysMessageId);			
			sb.append(", isRead=").append(isRead);			
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
        MaterialUserMessageRelation other = (MaterialUserMessageRelation) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getUserId() == null ? other.getId() == null : this.getUserId().equals(other.getUserId()))		
				        		&&(this.getSysMessageId() == null ? other.getId() == null : this.getSysMessageId().equals(other.getSysMessageId()))		
				        		&&(this.getIsRead() == null ? other.getId() == null : this.getIsRead().equals(other.getIsRead()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());		
		        result = prime * result + ((getSysMessageId() == null) ? 0 : getSysMessageId().hashCode());		
		        result = prime * result + ((getIsRead() == null) ? 0 : getIsRead().hashCode());		
		;
        return result;
    }

}
