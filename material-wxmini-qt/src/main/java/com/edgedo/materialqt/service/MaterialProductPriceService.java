package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialProductPrice;
import com.edgedo.materialqt.mapper.MaterialProductPriceMapper;
import com.edgedo.materialqt.queryvo.MaterialProductPriceFileView;
import com.edgedo.materialqt.queryvo.MaterialProductPriceQuery;
import com.edgedo.materialqt.queryvo.MaterialProductPriceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProductPriceService {
	
	
	@Autowired
	private MaterialProductPriceMapper materialProductPriceMapper;
	@Autowired
	private MaterialProductPriceFileService materialProductPriceFileService;

	public List<MaterialProductPriceView> listPage(MaterialProductPriceQuery materialProductPriceQuery){
		List<MaterialProductPriceView> list = materialProductPriceMapper.listPage(materialProductPriceQuery);
		for (MaterialProductPriceView price:list){
			List<MaterialProductPriceFileView> productPriceFileViewList = materialProductPriceFileService.listByProPriceId(price.getId());
			price.setProductPriceFileViewList(productPriceFileViewList);
		}
		materialProductPriceQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProductPrice materialProductPrice) {
		materialProductPrice.setId(Guid.guid());
		materialProductPriceMapper.insert(materialProductPrice);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProductPrice materialProductPrice) {
		materialProductPriceMapper.updateById(materialProductPrice);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProductPrice materialProductPrice) {
		materialProductPriceMapper.updateAllColumnById(materialProductPrice);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProductPriceMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialProductPriceMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProductPrice loadById(String id) {
		return materialProductPriceMapper.selectById(id);
	}
	

}
