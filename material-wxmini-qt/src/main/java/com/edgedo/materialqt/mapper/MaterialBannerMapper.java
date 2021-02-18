package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialBanner;
import com.edgedo.materialqt.queryvo.MaterialBannerQuery;
import com.edgedo.materialqt.queryvo.MaterialBannerView;
import org.apache.ibatis.annotations.Mapper;



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
	
	

}