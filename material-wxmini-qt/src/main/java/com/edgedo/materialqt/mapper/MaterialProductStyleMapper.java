package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialProductStyle;
import com.edgedo.materialqt.queryvo.MaterialProductStyleQuery;
import com.edgedo.materialqt.queryvo.MaterialProductStyleView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialProductStyleMapper  extends BaseMapper<MaterialProductStyle>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProductStyleView> listPage(MaterialProductStyleQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProductStyleView> listByObj(MaterialProductStyleQuery query);
	
	

}