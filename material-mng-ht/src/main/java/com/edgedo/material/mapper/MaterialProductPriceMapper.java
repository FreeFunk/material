package com.edgedo.material.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialProductPrice;
import com.edgedo.material.queryvo.MaterialProductPriceQuery;
import com.edgedo.material.queryvo.MaterialProductPriceView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialProductPriceMapper  extends BaseMapper<MaterialProductPrice>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProductPriceView> listPage(MaterialProductPriceQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProductPriceView> listByObj(MaterialProductPriceQuery query);


    Integer selectOrderPriceNum(@Param("materialId") String materialId);

    void updateEnableState(@Param("id")String id, @Param("isEnable")String isEnable);

}