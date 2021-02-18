package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialProducer;
import com.edgedo.material.queryvo.MaterialProducerQuery;
import com.edgedo.material.queryvo.MaterialProducerView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialProducerMapper  extends BaseMapper<MaterialProducer>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProducerView> listPage(MaterialProducerQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProducerView> listByObj(MaterialProducerQuery query);

	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	int logicDeleteById(String id);

}