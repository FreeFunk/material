package com.edgedo.material.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_producer")
public class MaterialProducer implements Serializable{
	
		
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
	 * 属性描述:厂商名称
	 */
	@TableField(value="PRODUCER_NAME",exist=true)
	java.lang.String producerName;
	
	/**
	 * 属性描述:联系人
	 */
	@TableField(value="CONTACT_USER_NAME",exist=true)
	java.lang.String contactUserName;
	
	/**
	 * 属性描述:联系电话
	 */
	@TableField(value="CONTACT_PHONE_NUM",exist=true)
	java.lang.String contactPhoneNum;
	
	/**
	 * 属性描述:地址
	 */
	@TableField(value="ADDRESS",exist=true)
	java.lang.String address;
	
	/**
	 * 属性描述:是否需要审核
	 */
	@TableField(value="IS_NEED_SH",exist=true)
	java.lang.String isNeedSh;
	
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
	
	
	public java.lang.String getProducerName(){
		return this.producerName;
	}
	
	public void setProducerName(java.lang.String producerName){
		this.producerName=producerName;
	}
	
	
	public java.lang.String getContactUserName(){
		return this.contactUserName;
	}
	
	public void setContactUserName(java.lang.String contactUserName){
		this.contactUserName=contactUserName;
	}
	
	
	public java.lang.String getContactPhoneNum(){
		return this.contactPhoneNum;
	}
	
	public void setContactPhoneNum(java.lang.String contactPhoneNum){
		this.contactPhoneNum=contactPhoneNum;
	}
	
	
	public java.lang.String getAddress(){
		return this.address;
	}
	
	public void setAddress(java.lang.String address){
		this.address=address;
	}
	
	
	public java.lang.String getIsNeedSh(){
		return this.isNeedSh;
	}
	
	public void setIsNeedSh(java.lang.String isNeedSh){
		this.isNeedSh=isNeedSh;
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
			sb.append(", producerName=").append(producerName);			
			sb.append(", contactUserName=").append(contactUserName);			
			sb.append(", contactPhoneNum=").append(contactPhoneNum);			
			sb.append(", address=").append(address);			
			sb.append(", isNeedSh=").append(isNeedSh);			
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
        MaterialProducer other = (MaterialProducer) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getProducerName() == null ? other.getId() == null : this.getProducerName().equals(other.getProducerName()))		
				        		&&(this.getContactUserName() == null ? other.getId() == null : this.getContactUserName().equals(other.getContactUserName()))		
				        		&&(this.getContactPhoneNum() == null ? other.getId() == null : this.getContactPhoneNum().equals(other.getContactPhoneNum()))		
				        		&&(this.getAddress() == null ? other.getId() == null : this.getAddress().equals(other.getAddress()))		
				        		&&(this.getIsNeedSh() == null ? other.getId() == null : this.getIsNeedSh().equals(other.getIsNeedSh()))		
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
		        result = prime * result + ((getProducerName() == null) ? 0 : getProducerName().hashCode());		
		        result = prime * result + ((getContactUserName() == null) ? 0 : getContactUserName().hashCode());		
		        result = prime * result + ((getContactPhoneNum() == null) ? 0 : getContactPhoneNum().hashCode());		
		        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());		
		        result = prime * result + ((getIsNeedSh() == null) ? 0 : getIsNeedSh().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
