package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialCaseImage;
import com.edgedo.materialqt.queryvo.MaterialCaseImageQuery;
import com.edgedo.materialqt.queryvo.MaterialCaseImageView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialCaseImageMapper  extends BaseMapper<MaterialCaseImage>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialCaseImageView> listPage(MaterialCaseImageQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialCaseImageView> listByObj(MaterialCaseImageQuery query);

	/*查询案例详情的图片*/
    List<MaterialCaseImage> listByCaseId(String caseId);
}