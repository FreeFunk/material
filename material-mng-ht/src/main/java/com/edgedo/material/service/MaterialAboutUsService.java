package com.edgedo.material.service;
		
import java.io.File;
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.MaterialAboutUs;
import com.edgedo.material.mapper.MaterialAboutUsMapper;
import com.edgedo.material.queryvo.MaterialAboutUsQuery;
import com.edgedo.material.queryvo.MaterialAboutUsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
		materialAboutUs.setCreateTime(new Date());
		materialAboutUs.setDataState("1");
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
		return materialAboutUsMapper.logicDeleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByIds(List<String> ids) {
		for(int i=0;i<ids.size();i++){
			materialAboutUsMapper.logicDeleteById(ids.get(i));
		}
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialAboutUs loadById(String id) {
		return materialAboutUsMapper.selectById(id);
	}

	//根据当前人员加载
	public MaterialAboutUsView loadInfoByUser(String userId){
		return materialAboutUsMapper.loadInfoByUser(userId);
	}
	

}
