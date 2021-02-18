package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialProductPriceFile;
import com.edgedo.material.queryvo.MaterialProductPriceFileQuery;
import com.edgedo.material.queryvo.MaterialProductPriceFileView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialProductPriceFileMapper  extends BaseMapper<MaterialProductPriceFile>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProductPriceFileView> listPage(MaterialProductPriceFileQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialProductPriceFileView> listByObj(MaterialProductPriceFileQuery query);


    Integer selectCountNum(@Param("pirceId") String pirceId);

	int updateByDataState(List<String> ids);

	/**
	 * 根据产品报价查询
	 * @author: ZhangCC
	 * @time: 2020/9/1 18:42
	 */
	List<MaterialProductPriceFileView> listByPriceId(String priceId);
}