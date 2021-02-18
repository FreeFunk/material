package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialCaseCls;
import com.edgedo.materialqt.mapper.MaterialCaseClsMapper;
import com.edgedo.materialqt.queryvo.MaterialCaseClsQuery;
import com.edgedo.materialqt.queryvo.MaterialCaseClsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialCaseClsService {
	
	
	@Autowired
	private MaterialCaseClsMapper materialCaseClsMapper;

	public List<MaterialCaseClsView> listPage(MaterialCaseClsQuery materialCaseClsQuery){
		List list = materialCaseClsMapper.listPage(materialCaseClsQuery);
		materialCaseClsQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialCaseCls materialCaseCls) {
		materialCaseCls.setId(Guid.guid());
		materialCaseClsMapper.insert(materialCaseCls);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialCaseCls materialCaseCls) {
		materialCaseClsMapper.updateById(materialCaseCls);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialCaseCls materialCaseCls) {
		materialCaseClsMapper.updateAllColumnById(materialCaseCls);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialCaseClsMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialCaseClsMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialCaseCls loadById(String id) {
		return materialCaseClsMapper.selectById(id);
	}
	

}
