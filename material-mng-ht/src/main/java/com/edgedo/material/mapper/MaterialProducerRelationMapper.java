package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialProducerRelation;
import com.edgedo.material.queryvo.MaterialProducerRelationQuery;
import com.edgedo.material.queryvo.MaterialProducerRelationView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialProducerRelationMapper  extends BaseMapper<MaterialProducerRelation>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProducerRelationView> listPage(MaterialProducerRelationQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProducerRelationView> listByObj(MaterialProducerRelationQuery query);


    List<String> selectByProducerId(String producerId);

    int countByMatAndPro(@Param("producerId") String producerId,
						 @Param("materialId") String materialId);

	int deleteByMatAndPro(@Param("producerId") String producerId,
						 @Param("materialId") String materialId);
}