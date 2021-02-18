package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialProductStyle;
import com.edgedo.materialqt.mapper.MaterialProductStyleMapper;
import com.edgedo.materialqt.queryvo.MaterialProductStyleQuery;
import com.edgedo.materialqt.queryvo.MaterialProductStyleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProductStyleService {
	
	
	@Autowired
	private MaterialProductStyleMapper materialProductStyleMapper;

	public List<MaterialProductStyleView> listPage(MaterialProductStyleQuery materialProductStyleQuery){
		List list = materialProductStyleMapper.listPage(materialProductStyleQuery);
		materialProductStyleQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProductStyle materialProductStyle) {
		materialProductStyle.setId(Guid.guid());
		materialProductStyleMapper.insert(materialProductStyle);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProductStyle materialProductStyle) {
		materialProductStyleMapper.updateById(materialProductStyle);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProductStyle materialProductStyle) {
		materialProductStyleMapper.updateAllColumnById(materialProductStyle);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProductStyleMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialProductStyleMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProductStyle loadById(String id) {
		return materialProductStyleMapper.selectById(id);
	}
	

}
