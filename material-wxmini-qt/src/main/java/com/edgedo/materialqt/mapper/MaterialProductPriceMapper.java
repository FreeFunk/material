package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialProductPrice;
import com.edgedo.materialqt.queryvo.MaterialProductPriceQuery;
import com.edgedo.materialqt.queryvo.MaterialProductPriceView;
import org.apache.ibatis.annotations.Mapper;



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
	
	

}