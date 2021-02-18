package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialBanner;
import com.edgedo.material.queryvo.MaterialBannerQuery;
import com.edgedo.material.queryvo.MaterialBannerView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialBannerMapper  extends BaseMapper<MaterialBanner>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialBannerView> listPage(MaterialBannerQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialBannerView> listByObj(MaterialBannerQuery query);


    Integer selectByMaterId(@Param("materialId") String materialId);

    int updateBatchIds(List<String> ids);

    void updateEnableState(@Param("id")String id, @Param("isEnable")String isEnable);
}