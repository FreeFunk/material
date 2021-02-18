package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialProductIntroduction;
import com.edgedo.material.queryvo.MaterialProductIntroductionQuery;
import com.edgedo.material.queryvo.MaterialProductIntroductionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


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


    void updateEnableState(@Param("id") String id, @Param("isEnable")String isEnable);

    Integer count(String materId);

    int updateBatchIds(List<String> ids);
}