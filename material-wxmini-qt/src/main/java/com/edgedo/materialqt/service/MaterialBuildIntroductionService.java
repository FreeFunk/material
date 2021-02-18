package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialBuildIntroduction;
import com.edgedo.materialqt.mapper.MaterialBuildIntroductionMapper;
import com.edgedo.materialqt.queryvo.MaterialBuildIntroductionQuery;
import com.edgedo.materialqt.queryvo.MaterialBuildIntroductionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialBuildIntroductionService {
	
	
	@Autowired
	private MaterialBuildIntroductionMapper materialBuildIntroductionMapper;

	public List<MaterialBuildIntroductionView> listPage(MaterialBuildIntroductionQuery materialBuildIntroductionQuery){
		List list = materialBuildIntroductionMapper.listPage(materialBuildIntroductionQuery);
		materialBuildIntroductionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialBuildIntroduction materialBuildIntroduction) {
		materialBuildIntroduction.setId(Guid.guid());
		materialBuildIntroductionMapper.insert(materialBuildIntroduction);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialBuildIntroduction materialBuildIntroduction) {
		materialBuildIntroductionMapper.updateById(materialBuildIntroduction);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialBuildIntroduction materialBuildIntroduction) {
		materialBuildIntroductionMapper.updateAllColumnById(materialBuildIntroduction);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialBuildIntroductionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialBuildIntroductionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialBuildIntroduction loadById(String id) {
		return materialBuildIntroductionMapper.selectById(id);
	}

	/**
	 * 根据材料id查询施工说明
	 * @param materialId
	 */
	public MaterialBuildIntroduction loadByMaterialId(String materialId) {
		return materialBuildIntroductionMapper.loadByMaterialId(materialId);
	}

}
