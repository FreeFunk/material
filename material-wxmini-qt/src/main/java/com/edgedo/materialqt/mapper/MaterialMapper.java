package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.Material;
import com.edgedo.materialqt.queryvo.MaterialQuery;
import com.edgedo.materialqt.queryvo.MaterialView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialMapper  extends BaseMapper<Material>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialView> listPage(MaterialQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialView> listByObj(MaterialQuery query);
	
	

}