package com.edgedo.material.service;
		
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.MaterialLabel;
import com.edgedo.material.mapper.MaterialLabelMapper;
import com.edgedo.material.queryvo.MaterialLabelQuery;
import com.edgedo.material.queryvo.MaterialLabelView;
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
		materialLabel.setCreateTime(new Date());
		materialLabel.setDataState("1");
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
		return materialLabelMapper.logicDeleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByIds(List<String> ids) {
		for(int i=0;i<ids.size();i++){
			materialLabelMapper.deleteById(ids.get(i));
		}
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialLabel loadById(String id) {
		return materialLabelMapper.selectById(id);
	}

	/**
	 * 根据标签类型查询最大的序号
	 * @return
	 */
	public BigDecimal selectMaxOrderNumByType(String labelType){
		return materialLabelMapper.selectMaxOrderNumByType(labelType);
	}

	/**
	 * 更新启用状态
	 * @author: ZhangCC
	 * @time: 2020/9/3 18:59
	 */
	public int updateEnableState(String id,String isEnable){
		return materialLabelMapper.updateEnableState(id,isEnable);
	}

}
