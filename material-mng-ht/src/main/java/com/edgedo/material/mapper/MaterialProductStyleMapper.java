package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialProductStyle;
import com.edgedo.material.queryvo.MaterialProductStyleQuery;
import com.edgedo.material.queryvo.MaterialProductStyleView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialProductStyleMapper  extends BaseMapper<MaterialProductStyle>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProductStyleView> listPage(MaterialProductStyleQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProductStyleView> listByObj(MaterialProductStyleQuery query);


	Integer count(String materId);

	int updateBatchIds(List<String> ids);
}