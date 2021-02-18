package com.edgedo.material.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_user")
public class MaterialUser implements Serializable{
	
		
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
	 * 属性描述:手机号
	 */
	@TableField(value="PHONE_NUM",exist=true)
	java.lang.String phoneNum;
	
	/**
	 * 属性描述:密码
	 */
	@TableField(value="PASSWORD",exist=true)
	java.lang.String password;
	
	/**
	 * 属性描述:用户昵称
	 */
	@TableField(value="NICK_NAME",exist=true)
	java.lang.String nickName;
	
	/**
	 * 属性描述:用户头像
	 */
	@TableField(value="HEAD_PHOTO",exist=true)
	java.lang.String headPhoto;
	
	/**
	 * 属性描述:用户类型(员工，普通用户)
	 */
	@TableField(value="USER_TYPE",exist=true)
	java.lang.String userType;
	
	/**
	 * 属性描述:用户备注
	 */
	@TableField(value="USER_REMARK",exist=true)
	java.lang.String userRemark;
	
	/**
	 * 属性描述:小程序openid
	 */
	@TableField(value="MINI_OPEN_ID",exist=true)
	java.lang.String miniOpenId;
	
	/**
	 * 属性描述:是否授权
	 */
	@TableField(value="IS_POWER",exist=true)
	java.lang.String isPower;
	
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
	
	
	public java.lang.String getPhoneNum(){
		return this.phoneNum;
	}
	
	public void setPhoneNum(java.lang.String phoneNum){
		this.phoneNum=phoneNum;
	}
	
	
	public java.lang.String getPassword(){
		return this.password;
	}
	
	public void setPassword(java.lang.String password){
		this.password=password;
	}
	
	
	public java.lang.String getNickName(){
		return this.nickName;
	}
	
	public void setNickName(java.lang.String nickName){
		this.nickName=nickName;
	}
	
	
	public java.lang.String getHeadPhoto(){
		return this.headPhoto;
	}
	
	public void setHeadPhoto(java.lang.String headPhoto){
		this.headPhoto=headPhoto;
	}
	
	
	public java.lang.String getUserType(){
		return this.userType;
	}
	
	public void setUserType(java.lang.String userType){
		this.userType=userType;
	}
	
	
	public java.lang.String getUserRemark(){
		return this.userRemark;
	}
	
	public void setUserRemark(java.lang.String userRemark){
		this.userRemark=userRemark;
	}
	
	
	public java.lang.String getMiniOpenId(){
		return this.miniOpenId;
	}
	
	public void setMiniOpenId(java.lang.String miniOpenId){
		this.miniOpenId=miniOpenId;
	}
	
	
	public java.lang.String getIsPower(){
		return this.isPower;
	}
	
	public void setIsPower(java.lang.String isPower){
		this.isPower=isPower;
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
			sb.append(", phoneNum=").append(phoneNum);			
			sb.append(", password=").append(password);			
			sb.append(", nickName=").append(nickName);			
			sb.append(", headPhoto=").append(headPhoto);			
			sb.append(", userType=").append(userType);			
			sb.append(", userRemark=").append(userRemark);			
			sb.append(", miniOpenId=").append(miniOpenId);			
			sb.append(", isPower=").append(isPower);			
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
        MaterialUser other = (MaterialUser) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getPhoneNum() == null ? other.getId() == null : this.getPhoneNum().equals(other.getPhoneNum()))		
				        		&&(this.getPassword() == null ? other.getId() == null : this.getPassword().equals(other.getPassword()))		
				        		&&(this.getNickName() == null ? other.getId() == null : this.getNickName().equals(other.getNickName()))		
				        		&&(this.getHeadPhoto() == null ? other.getId() == null : this.getHeadPhoto().equals(other.getHeadPhoto()))		
				        		&&(this.getUserType() == null ? other.getId() == null : this.getUserType().equals(other.getUserType()))		
				        		&&(this.getUserRemark() == null ? other.getId() == null : this.getUserRemark().equals(other.getUserRemark()))		
				        		&&(this.getMiniOpenId() == null ? other.getId() == null : this.getMiniOpenId().equals(other.getMiniOpenId()))		
				        		&&(this.getIsPower() == null ? other.getId() == null : this.getIsPower().equals(other.getIsPower()))		
				        		&&(this.getDataState() == null ? other.getId() == null : this.getDataState().equals(other.getDataState()))		
				;
    }

  
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());		
		        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());		
		        result = prime * result + ((getPhoneNum() == null) ? 0 : getPhoneNum().hashCode());		
		        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());		
		        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());		
		        result = prime * result + ((getHeadPhoto() == null) ? 0 : getHeadPhoto().hashCode());		
		        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());		
		        result = prime * result + ((getUserRemark() == null) ? 0 : getUserRemark().hashCode());		
		        result = prime * result + ((getMiniOpenId() == null) ? 0 : getMiniOpenId().hashCode());		
		        result = prime * result + ((getIsPower() == null) ? 0 : getIsPower().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
