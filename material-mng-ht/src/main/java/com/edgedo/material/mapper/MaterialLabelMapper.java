package com.edgedo.material.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialLabel;
import com.edgedo.material.queryvo.MaterialLabelQuery;
import com.edgedo.material.queryvo.MaterialLabelView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


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

	/**
	 * 逻辑删除
	 * @return
	 */
	int logicDeleteById(String id);

	/**
	 * 根据标签类型查询最大的序号
	 * @return
	 */
	BigDecimal selectMaxOrderNumByType(String labelType);

	/**
	 * 更新启用状态
	 * @author: ZhangCC
	 * @time: 2020/9/3 18:59
	 */
	int updateEnableState(@Param("id") String id,
						  @Param("isEnable") String isEnable);

}