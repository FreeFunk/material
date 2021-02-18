package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialUserCollection;
import com.edgedo.material.queryvo.MaterialUserCollectionQuery;
import com.edgedo.material.queryvo.MaterialUserCollectionView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialUserCollectionMapper  extends BaseMapper<MaterialUserCollection>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserCollectionView> listPage(MaterialUserCollectionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserCollectionView> listByObj(MaterialUserCollectionQuery query);
	
	

}