package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialCaseImage;
import com.edgedo.materialqt.mapper.MaterialCaseImageMapper;
import com.edgedo.materialqt.queryvo.MaterialCaseImageQuery;
import com.edgedo.materialqt.queryvo.MaterialCaseImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialCaseImageService {
	
	
	@Autowired
	private MaterialCaseImageMapper materialCaseImageMapper;

	public List<MaterialCaseImageView> listPage(MaterialCaseImageQuery materialCaseImageQuery){
		List list = materialCaseImageMapper.listPage(materialCaseImageQuery);
		materialCaseImageQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialCaseImage materialCaseImage) {
		materialCaseImage.setId(Guid.guid());
		materialCaseImageMapper.insert(materialCaseImage);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialCaseImage materialCaseImage) {
		materialCaseImageMapper.updateById(materialCaseImage);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialCaseImage materialCaseImage) {
		materialCaseImageMapper.updateAllColumnById(materialCaseImage);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialCaseImageMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialCaseImageMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialCaseImage loadById(String id) {
		return materialCaseImageMapper.selectById(id);
	}

	/*查询案例详情的图片*/
	public List<MaterialCaseImage> listByCaseId(String caseId) {
		return materialCaseImageMapper.listByCaseId(caseId);
	}
}
