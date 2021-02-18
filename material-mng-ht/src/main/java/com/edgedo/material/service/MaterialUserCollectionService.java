package com.edgedo.material.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.MaterialUserCollection;
import com.edgedo.material.mapper.MaterialUserCollectionMapper;
import com.edgedo.material.queryvo.MaterialUserCollectionQuery;
import com.edgedo.material.queryvo.MaterialUserCollectionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialUserCollectionService {
	
	
	@Autowired
	private MaterialUserCollectionMapper materialUserCollectionMapper;

	public List<MaterialUserCollectionView> listPage(MaterialUserCollectionQuery materialUserCollectionQuery){
		List list = materialUserCollectionMapper.listPage(materialUserCollectionQuery);
		materialUserCollectionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialUserCollection materialUserCollection) {
		materialUserCollection.setId(Guid.guid());
		materialUserCollectionMapper.insert(materialUserCollection);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialUserCollection materialUserCollection) {
		materialUserCollectionMapper.updateById(materialUserCollection);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialUserCollection materialUserCollection) {
		materialUserCollectionMapper.updateAllColumnById(materialUserCollection);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialUserCollectionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialUserCollectionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialUserCollection loadById(String id) {
		return materialUserCollectionMapper.selectById(id);
	}
	

}
