package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialProductIntroduction;
import com.edgedo.materialqt.mapper.MaterialProductIntroductionMapper;
import com.edgedo.materialqt.queryvo.MaterialProductIntroductionQuery;
import com.edgedo.materialqt.queryvo.MaterialProductIntroductionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProductIntroductionService {
	
	
	@Autowired
	private MaterialProductIntroductionMapper materialProductIntroductionMapper;

	public List<MaterialProductIntroductionView> listPage(MaterialProductIntroductionQuery materialProductIntroductionQuery){
		List list = materialProductIntroductionMapper.listPage(materialProductIntroductionQuery);
		materialProductIntroductionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProductIntroduction materialProductIntroduction) {
		materialProductIntroduction.setId(Guid.guid());
		materialProductIntroductionMapper.insert(materialProductIntroduction);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProductIntroduction materialProductIntroduction) {
		materialProductIntroductionMapper.updateById(materialProductIntroduction);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProductIntroduction materialProductIntroduction) {
		materialProductIntroductionMapper.updateAllColumnById(materialProductIntroduction);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProductIntroductionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialProductIntroductionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProductIntroduction loadById(String id) {
		return materialProductIntroductionMapper.selectById(id);
	}
	

}
