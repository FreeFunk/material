package com.edgedo.material.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_producer_sys_user_relation")
public class MaterialProducerSysUserRelation implements Serializable{
	
		
	/**
	 * 属性描述:id
	 */
	@TableField(value="ID",exist=true)
	java.lang.String id;
	
	/**
	 * 属性描述:createTime
	 */
	@TableField(value="CREATE_TIME",exist=true)
	java.util.Date createTime;
	
	/**
	 * 属性描述:createUserId
	 */
	@TableField(value="CREATE_USER_ID",exist=true)
	java.lang.String createUserId;
	
	/**
	 * 属性描述:sysUserId
	 */
	@TableField(value="SYS_USER_ID",exist=true)
	java.lang.String sysUserId;
	
	/**
	 * 属性描述:producerId
	 */
	@TableField(value="PRODUCER_ID",exist=true)
	java.lang.String producerId;
	
	/**
	 * 属性描述:userCode
	 */
	@TableField(value="USER_CODE",exist=true)
	java.lang.String userCode;
	
	
	
	
	
	
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
	
	
	public java.lang.String getSysUserId(){
		return this.sysUserId;
	}
	
	public void setSysUserId(java.lang.String sysUserId){
		this.sysUserId=sysUserId;
	}
	
	
	public java.lang.String getProducerId(){
		return this.producerId;
	}
	
	public void setProducerId(java.lang.String producerId){
		this.producerId=producerId;
	}
	
	
	public java.lang.String getUserCode(){
		return this.userCode;
	}
	
	public void setUserCode(java.lang.String userCode){
		this.userCode=userCode;
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
			sb.append(", sysUserId=").append(sysUserId);			
			sb.append(", producerId=").append(producerId);			
			sb.append(", userCode=").append(userCode);			
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
        MaterialProducerSysUserRelation other = (MaterialProducerSysUserRelation) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getSysUserId() == null ? other.getId() == null : this.getSysUserId().equals(other.getSysUserId()))		
				        		&&(this.getProducerId() == null ? other.getId() == null : this.getProducerId().equals(other.getProducerId()))		
				        		&&(this.getUserCode() == null ? other.getId() == null : this.getUserCode().equals(other.getUserCode()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());		
		        result = prime * result + ((getSysUserId() == null) ? 0 : getSysUserId().hashCode());		
		        result = prime * result + ((getProducerId() == null) ? 0 : getProducerId().hashCode());		
		        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());		
		;
        return result;
    }

}
