package com.edgedo.material.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.Material;
import com.edgedo.material.queryvo.MaterialQuery;
import com.edgedo.material.queryvo.MaterialView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialMapper  extends BaseMapper<Material>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialView> listPage(MaterialQuery query);

	/**
	 * 厂商未关联的材料列表
	 * @author: ZhangCC
	 * @time: 2020/9/10 9:26
	 */
	List<MaterialView> proNotRelListPage(MaterialQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialView> listByObj(MaterialQuery query);


    Integer countAllMaterNum();

	int updateBatchIds(List<String> ids);

//	切换启用状态
    void updateEnableState(@Param("id") String id,@Param("isEnable") String isEnable);

	List<MaterialView> materialNameAll();

	List<MaterialView> materialNameProdouceId(@Param("materialIdList")List<String> materialIdList);

	List<MaterialView> notRelListPage(MaterialQuery query);

	//切换产品样式启用状态
	void updateIsShowProductStyleState(@Param("id") String id,@Param("isShowProductStyle") String isShowProductStyle);
}