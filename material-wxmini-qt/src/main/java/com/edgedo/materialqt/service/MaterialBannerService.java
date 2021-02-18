package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialBanner;
import com.edgedo.materialqt.mapper.MaterialBannerMapper;
import com.edgedo.materialqt.queryvo.MaterialBannerQuery;
import com.edgedo.materialqt.queryvo.MaterialBannerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialBannerService {
	
	
	@Autowired
	private MaterialBannerMapper materialBannerMapper;

	public List<MaterialBannerView> listPage(MaterialBannerQuery materialBannerQuery){
		List list = materialBannerMapper.listPage(materialBannerQuery);
		materialBannerQuery.setList(list);
		return list;
	}

	public List<MaterialBannerView> listByObj(MaterialBannerQuery materialBannerQuery){
		List list = materialBannerMapper.listByObj(materialBannerQuery);
		materialBannerQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialBanner materialBanner) {
		materialBanner.setId(Guid.guid());
		materialBannerMapper.insert(materialBanner);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialBanner materialBanner) {
		materialBannerMapper.updateById(materialBanner);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialBanner materialBanner) {
		materialBannerMapper.updateAllColumnById(materialBanner);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialBannerMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialBannerMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialBanner loadById(String id) {
		return materialBannerMapper.selectById(id);
	}
	

}
