package com.edgedo.material.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.MaterialUserFootmark;
import com.edgedo.material.mapper.MaterialUserFootmarkMapper;
import com.edgedo.material.queryvo.MaterialUserFootmarkQuery;
import com.edgedo.material.queryvo.MaterialUserFootmarkView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialUserFootmarkService {
	
	
	@Autowired
	private MaterialUserFootmarkMapper materialUserFootmarkMapper;

	public List<MaterialUserFootmarkView> listPage(MaterialUserFootmarkQuery materialUserFootmarkQuery){
		List list = materialUserFootmarkMapper.listPage(materialUserFootmarkQuery);
		materialUserFootmarkQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialUserFootmark materialUserFootmark) {
		materialUserFootmark.setId(Guid.guid());
		materialUserFootmarkMapper.insert(materialUserFootmark);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialUserFootmark materialUserFootmark) {
		materialUserFootmarkMapper.updateById(materialUserFootmark);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialUserFootmark materialUserFootmark) {
		materialUserFootmarkMapper.updateAllColumnById(materialUserFootmark);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialUserFootmarkMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialUserFootmarkMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialUserFootmark loadById(String id) {
		return materialUserFootmarkMapper.selectById(id);
	}
	

}
