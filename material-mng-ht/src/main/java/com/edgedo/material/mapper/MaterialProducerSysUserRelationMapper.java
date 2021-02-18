package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialProducerSysUserRelation;
import com.edgedo.material.queryvo.MaterialProducerSysUserRelationQuery;
import com.edgedo.material.queryvo.MaterialProducerSysUserRelationView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialProducerSysUserRelationMapper  extends BaseMapper<MaterialProducerSysUserRelation>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProducerSysUserRelationView> listPage(MaterialProducerSysUserRelationQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProducerSysUserRelationView> listByObj(MaterialProducerSysUserRelationQuery query);


    String selectBySysUserId(String userId);
}