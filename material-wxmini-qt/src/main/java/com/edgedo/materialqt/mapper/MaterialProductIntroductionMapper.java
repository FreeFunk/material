package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialProductIntroduction;
import com.edgedo.materialqt.queryvo.MaterialProductIntroductionQuery;
import com.edgedo.materialqt.queryvo.MaterialProductIntroductionView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialProductIntroductionMapper  extends BaseMapper<MaterialProductIntroduction>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProductIntroductionView> listPage(MaterialProductIntroductionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProductIntroductionView> listByObj(MaterialProductIntroductionQuery query);
	
	

}