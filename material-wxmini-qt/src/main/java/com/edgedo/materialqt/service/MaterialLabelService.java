package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialLabel;
import com.edgedo.materialqt.mapper.MaterialLabelMapper;
import com.edgedo.materialqt.queryvo.MaterialLabelQuery;
import com.edgedo.materialqt.queryvo.MaterialLabelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialLabelService {
	
	
	@Autowired
	private MaterialLabelMapper materialLabelMapper;

	public List<MaterialLabelView> listPage(MaterialLabelQuery materialLabelQuery){
		List list = materialLabelMapper.listPage(materialLabelQuery);
		materialLabelQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialLabel materialLabel) {
		materialLabel.setId(Guid.guid());
		materialLabelMapper.insert(materialLabel);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialLabel materialLabel) {
		materialLabelMapper.updateById(materialLabel);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialLabel materialLabel) {
		materialLabelMapper.updateAllColumnById(materialLabel);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialLabelMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialLabelMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialLabel loadById(String id) {
		return materialLabelMapper.selectById(id);
	}
	

}
