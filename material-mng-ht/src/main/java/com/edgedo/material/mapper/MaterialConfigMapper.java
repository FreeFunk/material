package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialConfig;
import com.edgedo.material.queryvo.MaterialConfigQuery;
import com.edgedo.material.queryvo.MaterialConfigView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialConfigMapper  extends BaseMapper<MaterialConfig>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialConfigView> listPage(MaterialConfigQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialConfigView> listByObj(MaterialConfigQuery query);
	
	

}