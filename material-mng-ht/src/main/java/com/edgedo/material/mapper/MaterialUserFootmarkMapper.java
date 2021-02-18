package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialUserFootmark;
import com.edgedo.material.queryvo.MaterialUserFootmarkQuery;
import com.edgedo.material.queryvo.MaterialUserFootmarkView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialUserFootmarkMapper  extends BaseMapper<MaterialUserFootmark>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserFootmarkView> listPage(MaterialUserFootmarkQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserFootmarkView> listByObj(MaterialUserFootmarkQuery query);
	
	

}