package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialAboutUs;
import com.edgedo.material.queryvo.MaterialAboutUsQuery;
import com.edgedo.material.queryvo.MaterialAboutUsView;
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

	/**
	 * 逻辑删除
	 * @return
	 */
	int logicDeleteById(String id);

	//根据当前人员加载
	MaterialAboutUsView loadInfoByUser(String userId);

}