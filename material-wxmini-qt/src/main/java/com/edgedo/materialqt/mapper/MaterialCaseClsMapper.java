package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialCaseCls;
import com.edgedo.materialqt.queryvo.MaterialCaseClsQuery;
import com.edgedo.materialqt.queryvo.MaterialCaseClsView;
import org.apache.ibatis.annotations.Mapper;



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
	
	

}