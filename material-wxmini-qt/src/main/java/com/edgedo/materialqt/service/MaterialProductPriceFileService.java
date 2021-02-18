package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialProductPriceFile;
import com.edgedo.materialqt.mapper.MaterialProductPriceFileMapper;
import com.edgedo.materialqt.queryvo.MaterialProductPriceFileQuery;
import com.edgedo.materialqt.queryvo.MaterialProductPriceFileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProductPriceFileService {
	
	
	@Autowired
	private MaterialProductPriceFileMapper materialProductPriceFileMapper;

	public List<MaterialProductPriceFileView> listPage(MaterialProductPriceFileQuery materialProductPriceFileQuery){
		List list = materialProductPriceFileMapper.listPage(materialProductPriceFileQuery);
		materialProductPriceFileQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProductPriceFile materialProductPriceFile) {
		materialProductPriceFile.setId(Guid.guid());
		materialProductPriceFileMapper.insert(materialProductPriceFile);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProductPriceFile materialProductPriceFile) {
		materialProductPriceFileMapper.updateById(materialProductPriceFile);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProductPriceFile materialProductPriceFile) {
		materialProductPriceFileMapper.updateAllColumnById(materialProductPriceFile);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProductPriceFileMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialProductPriceFileMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProductPriceFile loadById(String id) {
		return materialProductPriceFileMapper.selectById(id);
	}

	/*根据产品报价ID查询产品报价附件*/
	public List<MaterialProductPriceFileView>  listByProPriceId(String productPriceId) {
		return materialProductPriceFileMapper.listByProPriceId(productPriceId);
	}
}
