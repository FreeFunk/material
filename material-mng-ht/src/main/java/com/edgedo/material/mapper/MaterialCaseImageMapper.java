package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialCaseImage;
import com.edgedo.material.queryvo.MaterialCaseImageQuery;
import com.edgedo.material.queryvo.MaterialCaseImageView;
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


    Integer count(String caseId);

	int updateBatchIds(List<String> ids);

	/**
	 * 根据产品id查询关联图片
	 * @author: ZhangCC
	 * @time: 2020/9/2 12:58
	 */
	MaterialCaseImageView selectVoByCaseId(String caseId);

}