package com.edgedo.materialqt.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

@TableName("material_product_price_file")
public class MaterialProductPriceFile implements Serializable{
	
		
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
	 * 属性描述:所属产品报价ID
	 */
	@TableField(value="PRODUCT_PRICE_ID",exist=true)
	java.lang.String productPriceId;
	
	/**
	 * 属性描述:文件名
	 */
	@TableField(value="FILE_NAME",exist=true)
	java.lang.String fileName;
	
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
	 * 属性描述:排序号
	 */
	@TableField(value="ORDER_NUM",exist=true)
	java.math.BigDecimal orderNum;
	
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
	
	
	public java.lang.String getProductPriceId(){
		return this.productPriceId;
	}
	
	public void setProductPriceId(java.lang.String productPriceId){
		this.productPriceId=productPriceId;
	}
	
	
	public java.lang.String getFileName(){
		return this.fileName;
	}
	
	public void setFileName(java.lang.String fileName){
		this.fileName=fileName;
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
	
	
	public java.math.BigDecimal getOrderNum(){
		return this.orderNum;
	}
	
	public void setOrderNum(java.math.BigDecimal orderNum){
		this.orderNum=orderNum;
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
			sb.append(", productPriceId=").append(productPriceId);			
			sb.append(", fileName=").append(fileName);			
			sb.append(", fileType=").append(fileType);			
			sb.append(", fileUrl=").append(fileUrl);			
			sb.append(", orderNum=").append(orderNum);			
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
        MaterialProductPriceFile other = (MaterialProductPriceFile) that;
        boolean flag = true;
        return  flag
        		&&(this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))		
				        		&&(this.getCreateTime() == null ? other.getId() == null : this.getCreateTime().equals(other.getCreateTime()))		
				        		&&(this.getCreateUserId() == null ? other.getId() == null : this.getCreateUserId().equals(other.getCreateUserId()))		
				        		&&(this.getProductPriceId() == null ? other.getId() == null : this.getProductPriceId().equals(other.getProductPriceId()))		
				        		&&(this.getFileName() == null ? other.getId() == null : this.getFileName().equals(other.getFileName()))		
				        		&&(this.getFileType() == null ? other.getId() == null : this.getFileType().equals(other.getFileType()))		
				        		&&(this.getFileUrl() == null ? other.getId() == null : this.getFileUrl().equals(other.getFileUrl()))		
				        		&&(this.getOrderNum() == null ? other.getId() == null : this.getOrderNum().equals(other.getOrderNum()))		
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
		        result = prime * result + ((getProductPriceId() == null) ? 0 : getProductPriceId().hashCode());		
		        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());		
		        result = prime * result + ((getFileType() == null) ? 0 : getFileType().hashCode());		
		        result = prime * result + ((getFileUrl() == null) ? 0 : getFileUrl().hashCode());		
		        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());		
		        result = prime * result + ((getDataState() == null) ? 0 : getDataState().hashCode());		
		;
        return result;
    }

}
