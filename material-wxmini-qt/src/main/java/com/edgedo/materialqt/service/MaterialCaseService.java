package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialCase;
import com.edgedo.materialqt.entity.MaterialCaseImage;
import com.edgedo.materialqt.entity.MaterialUserCollection;
import com.edgedo.materialqt.mapper.MaterialCaseMapper;
import com.edgedo.materialqt.queryvo.MaterialCaseQuery;
import com.edgedo.materialqt.queryvo.MaterialCaseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialCaseService {
	
	
	@Autowired
	private MaterialCaseMapper materialCaseMapper;
	@Autowired
	private MaterialUserCollectionService materialUserCollectionService;
	@Autowired
	private MaterialCaseImageService materialCaseImageService;

	public List<MaterialCaseView> listPage(MaterialCaseQuery materialCaseQuery,String userId){
		List<MaterialCaseView> list = materialCaseMapper.listPage(materialCaseQuery);
		for (MaterialCaseView caseView:list){
			String caseId = caseView.getId();
			//根据案例id和用户id查询
			MaterialUserCollection materialUserCollection= materialUserCollectionService.loadByCaseIdAndUserId(caseId,userId);
			if(materialUserCollection !=null ){
				caseView.setIsCollection("1");
			}else {
				caseView.setIsCollection("0");
			}
		}
		materialCaseQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialCase materialCase) {
		materialCase.setId(Guid.guid());
		materialCaseMapper.insert(materialCase);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialCase materialCase) {
		materialCaseMapper.updateById(materialCase);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialCase materialCase) {
		materialCaseMapper.updateAllColumnById(materialCase);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialCaseMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialCaseMapper.deleteBatchIds(ids);
	}
	
	
	
/*	*//**
	 * 加载单个
	 * @param id
	 *//*
	public MaterialCase loadById(String id) {
		return materialCaseMapper.selectById(id);
	}*/

	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialCaseView loadById(String id,String userId) {
		MaterialCaseView materialCaseView = materialCaseMapper.loadById(id);
		//根据案例id和用户id查询
		MaterialUserCollection materialUserCollection= materialUserCollectionService.loadByCaseIdAndUserId(id,userId);
		if(materialUserCollection !=null ){
			materialCaseView.setIsCollection("1");
		}else {
			materialCaseView.setIsCollection("0");
		}
		//查询所有的图片集合
		List<MaterialCaseImage> materialCaseImageList = materialCaseImageService.listByCaseId(id);
		materialCaseView.setMaterialCaseImageList(materialCaseImageList);
		return materialCaseView;
	}

	public List<String> listLable(String caseLable) {
		return materialCaseMapper.listLable(caseLable);
	}
}
