package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialCase;
import com.edgedo.material.queryvo.MaterialCaseQuery;
import com.edgedo.material.queryvo.MaterialCaseView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialCaseMapper  extends BaseMapper<MaterialCase>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialCaseView> listPage(MaterialCaseQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialCaseView> listByObj(MaterialCaseQuery query);


	void updateEnableState(@Param("id")String id, @Param("isEnable")String isEnable);

    Integer selectMaIdAndClsId(@Param("materialId")String materialId, @Param("caseClsId")String caseClsId,
							   @Param("type")String type);

	int updateBatchIds(List<String> ids);

    List<MaterialCase> listByWidth(String id);
}