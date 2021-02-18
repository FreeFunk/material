package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialAboutUs;
import com.edgedo.materialqt.queryvo.MaterialAboutUsQuery;
import com.edgedo.materialqt.queryvo.MaterialAboutUsView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialAboutUsMapper  extends BaseMapper<MaterialAboutUs>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialAboutUsView> listPage(MaterialAboutUsQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialAboutUsView> listByObj(MaterialAboutUsQuery query);
	
	

}