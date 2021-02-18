package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialAboutUs;
import com.edgedo.materialqt.mapper.MaterialAboutUsMapper;
import com.edgedo.materialqt.queryvo.MaterialAboutUsQuery;
import com.edgedo.materialqt.queryvo.MaterialAboutUsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialAboutUsService {
	
	
	@Autowired
	private MaterialAboutUsMapper materialAboutUsMapper;

	public List<MaterialAboutUsView> listPage(MaterialAboutUsQuery materialAboutUsQuery){
		List list = materialAboutUsMapper.listPage(materialAboutUsQuery);
		materialAboutUsQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialAboutUs materialAboutUs) {
		materialAboutUs.setId(Guid.guid());
		materialAboutUsMapper.insert(materialAboutUs);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialAboutUs materialAboutUs) {
		materialAboutUsMapper.updateById(materialAboutUs);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialAboutUs materialAboutUs) {
		materialAboutUsMapper.updateAllColumnById(materialAboutUs);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialAboutUsMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialAboutUsMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialAboutUs loadById(String id) {
		return materialAboutUsMapper.selectById(id);
	}
	

}
