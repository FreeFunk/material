package com.edgedo.materialqt.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_sys_message")
public class MaterialSysMessage implements Serializable{
	
		
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
	 * 属性描述:通知标题
	 */
	@TableField(value="MESSAGE_TITLE",exist=true)
	java.lang.String messageTitle;
	
	/**
	 * 属性描述:通知图片
	 */
	@TableField(value="MESSAGE_IMAGE",exist=true)
	java.lang.String messageImage;
	
	/**
	 * 属性描述:通知内容
	 */
	@TableField(value="MESSAGE_CONTENT",exist=true)
	java.lang.String messageContent;
	
	/**
	 * 属性描述:发送状态
	 */
	@TableField(value="SEND_STATE",exist=true)
	java.lang.String sendState;
	
	/**
	 * 属性描述:数据状态
	 */
	@TableField(value="DATA_STATE",exist=true)
	java.lang.String dataState;
	
	
	
	
	
	
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
	
	
	public java.lang.String getMessageTitle(){
		return this.messageTitle;
	}
	
	public void setMessageTitle(java.lang.String messageTitle){
		this.messageTitle=messageTitle;
	}
	
	
	public java.lang.String getMessageImage(){
		return this.messageImage;
	}
	
	public void setMessageImage(java.lang.String messageImage){
		this.messageImage=messageImage;
	}
	
	
	public java.lang.String getMessageContent(){
		return this.messageContent;
	}
	
	public void setMessageContent(java.lang.String messageContent){
		this.messageContent=messageContent;
	}
	
	
	public java.lang.String getSendState(){
		return this.sendState;
	}
	
	public void setSendState(java.lang.String sendState){
		this.sendState=sendState;
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
			sb.append(", messageTitle=").append(messageTitle);			
			sb.append(", messageImage=").append(messageImage);			
			sb.append(", messageContent=").append(messageContent);			
			sb.append(", sendState=").append(sendState);			
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
        MaterialSysMessage other = (MaterialSysMessage) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getMessageTitle() == null ? other.getId() == null : this.getMessageTitle().equals(other.getMessageTitle()))		
				        		&&(this.getMessageImage() == null ? other.getId() == null : this.getMessageImage().equals(other.getMessageImage()))		
				        		&&(this.getMessageContent() == null ? other.getId() == null : this.getMessageContent().equals(other.getMessageContent()))		
				        		&&(this.getSendState() == null ? other.getId() == null : this.getSendState().equals(other.getSendState()))		
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
		        result = prime * result + ((getMessageTitle() == null) ? 0 : getMessageTitle().hashCode());		
		        result = prime * result + ((getMessageImage() == null) ? 0 : getMessageImage().hashCode());		
		        result = prime * result + ((getMessageContent() == null) ? 0 : getMessageContent().hashCode());		
		        result = prime * result + ((getSendState() == null) ? 0 : getSendState().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
