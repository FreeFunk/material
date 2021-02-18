package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialLabel;
import com.edgedo.materialqt.queryvo.MaterialLabelQuery;
import com.edgedo.materialqt.queryvo.MaterialLabelView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialLabelMapper  extends BaseMapper<MaterialLabel>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialLabelView> listPage(MaterialLabelQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialLabelView> listByObj(MaterialLabelQuery query);
	
	

}