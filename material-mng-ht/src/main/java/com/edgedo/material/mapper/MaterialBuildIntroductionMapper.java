package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialBuildIntroduction;
import com.edgedo.material.queryvo.MaterialBuildIntroductionQuery;
import com.edgedo.material.queryvo.MaterialBuildIntroductionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialBuildIntroductionMapper  extends BaseMapper<MaterialBuildIntroduction>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialBuildIntroductionView> listPage(MaterialBuildIntroductionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialBuildIntroductionView> listByObj(MaterialBuildIntroductionQuery query);


	int updateBatchIds(List<String> ids);

    MaterialBuildIntroductionView selectByMaterId(@Param("materId") String materId);

}