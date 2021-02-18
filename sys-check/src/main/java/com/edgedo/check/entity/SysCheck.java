package com.edgedo.check.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("sys_check")
public class SysCheck implements Serializable{
	
		
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
	 * 属性描述:业务类型
	 */
	@TableField(value="SERVICE_TYPE",exist=true)
	java.lang.String serviceType;
	
	/**
	 * 属性描述:业务ID
	 */
	@TableField(value="SERVICE_ID",exist=true)
	java.lang.String serviceId;

	/**
	 * 属性描述:业务描述
	 */
	@TableField(value="SERVICE_DES",exist=true)
	java.lang.String serviceDes;

	/**
	 * 属性描述:业务页面
	 */
	@TableField(value="SERVICE_HTML",exist=true)
	java.lang.String serviceHtml;
	
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


	public String getServiceDes() {
		return serviceDes;
	}

	public void setServiceDes(String serviceDes) {
		this.serviceDes = serviceDes;
	}

	public String getServiceHtml() {
		return serviceHtml;
	}

	public void setServiceHtml(String serviceHtml) {
		this.serviceHtml = serviceHtml;
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
	
	
	public java.lang.String getServiceType(){
		return this.serviceType;
	}
	
	public void setServiceType(java.lang.String serviceType){
		this.serviceType=serviceType;
	}
	
	
	public java.lang.String getServiceId(){
		return this.serviceId;
	}
	
	public void setServiceId(java.lang.String serviceId){
		this.serviceId=serviceId;
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
			sb.append(", serviceType=").append(serviceType);			
			sb.append(", serviceId=").append(serviceId);			
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
        SysCheck other = (SysCheck) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getServiceType() == null ? other.getId() == null : this.getServiceType().equals(other.getServiceType()))		
				        		&&(this.getServiceId() == null ? other.getId() == null : this.getServiceId().equals(other.getServiceId()))		
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
		        result = prime * result + ((getServiceType() == null) ? 0 : getServiceType().hashCode());		
		        result = prime * result + ((getServiceId() == null) ? 0 : getServiceId().hashCode());		
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
