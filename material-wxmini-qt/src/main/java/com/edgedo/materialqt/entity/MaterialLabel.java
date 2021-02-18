package com.edgedo.materialqt.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_label")
public class MaterialLabel implements Serializable{
	
		
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
	 * 属性描述:标签类型(1:材料标签2：热门推荐)
	 */
	@TableField(value="LABEL_TYPE",exist=true)
	java.lang.String labelType;
	
	/**
	 * 属性描述:标签名
	 */
	@TableField(value="LABEL_NAME",exist=true)
	java.lang.String labelName;
	
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
	
	
	public java.lang.String getLabelType(){
		return this.labelType;
	}
	
	public void setLabelType(java.lang.String labelType){
		this.labelType=labelType;
	}
	
	
	public java.lang.String getLabelName(){
		return this.labelName;
	}
	
	public void setLabelName(java.lang.String labelName){
		this.labelName=labelName;
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
			sb.append(", labelType=").append(labelType);			
			sb.append(", labelName=").append(labelName);			
			sb.append(", orderNum=").append(orderNum);			
			sb.append(", isEnable=").append(isEnable);			
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
        MaterialLabel other = (MaterialLabel) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getCreateUserName() == null ? other.getId() == null : this.getCreateUserName().equals(other.getCreateUserName()))		
				        		&&(this.getLabelType() == null ? other.getId() == null : this.getLabelType().equals(other.getLabelType()))		
				        		&&(this.getLabelName() == null ? other.getId() == null : this.getLabelName().equals(other.getLabelName()))		
				        		&&(this.getOrderNum() == null ? other.getId() == null : this.getOrderNum().equals(other.getOrderNum()))		
				        		&&(this.getIsEnable() == null ? other.getId() == null : this.getIsEnable().equals(other.getIsEnable()))		
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
		        result = prime * result + ((getLabelType() == null) ? 0 : getLabelType().hashCode());		
		        result = prime * result + ((getLabelName() == null) ? 0 : getLabelName().hashCode());		
		        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());		
		        result = prime * result + ((getIsEnable() == null) ? 0 : getIsEnable().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
