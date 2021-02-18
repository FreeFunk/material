package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialUserCollection;
import com.edgedo.materialqt.mapper.MaterialCaseMapper;
import com.edgedo.materialqt.mapper.MaterialUserCollectionMapper;
import com.edgedo.materialqt.queryvo.MaterialCaseView;
import com.edgedo.materialqt.queryvo.MaterialUserCollectionQuery;
import com.edgedo.materialqt.queryvo.MaterialUserCollectionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialUserCollectionService {
	
	
	@Autowired
	private MaterialUserCollectionMapper materialUserCollectionMapper;
	@Autowired
	private MaterialCaseMapper caseMapper;

	public List<MaterialUserCollectionView> listPage(MaterialUserCollectionQuery materialUserCollectionQuery){
		List<MaterialUserCollectionView> list = materialUserCollectionMapper.listPage(materialUserCollectionQuery);
		materialUserCollectionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialUserCollection materialUserCollection) {
		materialUserCollection.setId(Guid.guid());
		materialUserCollectionMapper.insert(materialUserCollection);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialUserCollection materialUserCollection) {
		materialUserCollectionMapper.updateById(materialUserCollection);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialUserCollection materialUserCollection) {
		materialUserCollectionMapper.updateAllColumnById(materialUserCollection);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialUserCollectionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialUserCollectionMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialUserCollection loadById(String id) {
		return materialUserCollectionMapper.selectById(id);
	}

	/*添加一条用户收藏记录*/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public MaterialUserCollection insertOrUpdate(MaterialUserCollectionView userCollection) {
		String isAdd = userCollection.getIsAdd();
		if ("1".equals(isAdd)){
			MaterialUserCollection userCollection1 = materialUserCollectionMapper.loadByVo(userCollection);
			if(userCollection1==null){
				MaterialCaseView caseView = caseMapper.loadById(userCollection.getRelationId());
				if (caseView !=null){
					userCollection.setImageHeight(caseView.getImageHeight());
					userCollection.setImageWidth(caseView.getImageWidth());
				}else {
					userCollection.setImageHeight(0);
					userCollection.setImageWidth(0);
				}
				userCollection.setId(Guid.guid());
				materialUserCollectionMapper.insert(userCollection);
			}else {
				userCollection.setId(userCollection1.getId());
			}
		}
		if ("0".equals(isAdd)){
			String userId = userCollection.getCreateUserId();
			String relationId = userCollection.getRelationId();
			materialUserCollectionMapper.deleteByUserIdAndRelationId(userId,relationId);
		}
		return userCollection;
	}

	public MaterialUserCollection loadByCaseIdAndUserId(String caseId, String userId) {
		return materialUserCollectionMapper.loadByCaseIdAndUserId(caseId,userId);
	}
}
