package com.edgedo.material.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.MaterialConfig;
import com.edgedo.material.mapper.MaterialConfigMapper;
import com.edgedo.material.queryvo.MaterialConfigQuery;
import com.edgedo.material.queryvo.MaterialConfigView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialConfigService {
	
	
	@Autowired
	private MaterialConfigMapper materialConfigMapper;

	public List<MaterialConfigView> listPage(MaterialConfigQuery materialConfigQuery){
		List list = materialConfigMapper.listPage(materialConfigQuery);
		materialConfigQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialConfig materialConfig) {
		materialConfig.setId(Guid.guid());
		materialConfigMapper.insert(materialConfig);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialConfig materialConfig) {
		materialConfigMapper.updateById(materialConfig);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialConfig materialConfig) {
		materialConfigMapper.updateAllColumnById(materialConfig);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialConfigMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialConfigMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialConfig loadById(String id) {
		return materialConfigMapper.selectById(id);
	}
	

}
