package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialBuildIntroduction;
import com.edgedo.materialqt.queryvo.MaterialBuildIntroductionQuery;
import com.edgedo.materialqt.queryvo.MaterialBuildIntroductionView;
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

	/*根据材料id查询施工说明*/
    MaterialBuildIntroduction loadByMaterialId(@Param("materialId") String materialId);
}