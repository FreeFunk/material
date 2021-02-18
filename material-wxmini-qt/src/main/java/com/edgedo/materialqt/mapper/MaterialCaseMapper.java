package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialCase;
import com.edgedo.materialqt.queryvo.MaterialCaseQuery;
import com.edgedo.materialqt.queryvo.MaterialCaseView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialCaseMapper  extends BaseMapper<MaterialCase>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialCaseView> listPage(MaterialCaseQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialCaseView> listByObj(MaterialCaseQuery query);


    MaterialCaseView loadById(String id);
    /*发现页的推荐*/
    List<String> listLable(String caseLable);
}