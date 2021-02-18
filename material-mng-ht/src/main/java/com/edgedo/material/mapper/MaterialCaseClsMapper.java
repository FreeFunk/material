package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialCaseCls;
import com.edgedo.material.queryvo.MaterialCaseClsQuery;
import com.edgedo.material.queryvo.MaterialCaseClsView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialCaseClsMapper  extends BaseMapper<MaterialCaseCls>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialCaseClsView> listPage(MaterialCaseClsQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialCaseClsView> listByObj(MaterialCaseClsQuery query);


    Integer count(@Param("materialId")String materialId,@Param("type")String type);

	void updateEnableState(@Param("id")String id, @Param("isEnable")String isEnable);

	int updateBatchIds(List<String> ids);

	List<MaterialCaseClsView> materialClsNameAll();

	List<MaterialCaseClsView> materialClsNameAllColor();

	List<MaterialCaseClsView> materialNameAndClsId(String materialId);

    List<MaterialCaseClsView> materialClsNameAllMaterIdList(@Param("materialIdList")List<String> materialIdList);

	List<MaterialCaseClsView> materialClsNameAllMaterIdListColor(@Param("materialIdList")List<String> materialIdList);

}