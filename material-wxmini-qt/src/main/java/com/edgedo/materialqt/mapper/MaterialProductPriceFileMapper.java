package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialProductPriceFile;
import com.edgedo.materialqt.queryvo.MaterialProductPriceFileQuery;
import com.edgedo.materialqt.queryvo.MaterialProductPriceFileView;
import org.apache.ibatis.annotations.Mapper;



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

	/*根据产品报价ID查询产品报价附件*/
    List<MaterialProductPriceFileView> listByProPriceId(String productPriceId);
}