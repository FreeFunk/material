package com.edgedo.materialqt.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialUserCollection;
import com.edgedo.materialqt.entity.MaterialUserFootmark;
import com.edgedo.materialqt.mapper.MaterialCaseMapper;
import com.edgedo.materialqt.mapper.MaterialUserFootmarkMapper;
import com.edgedo.materialqt.queryvo.MaterialCaseView;
import com.edgedo.materialqt.queryvo.MaterialUserFootmarkQuery;
import com.edgedo.materialqt.queryvo.MaterialUserFootmarkView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialUserFootmarkService {
	
	
	@Autowired
	private MaterialUserFootmarkMapper materialUserFootmarkMapper;
	@Autowired
	private MaterialUserCollectionService materialUserCollectionService;
	@Autowired
	private MaterialCaseMapper caseMapper;

	public List<MaterialUserFootmarkView> listPage(MaterialUserFootmarkQuery materialUserFootmarkQuery,String userId){
		List<MaterialUserFootmarkView> list = materialUserFootmarkMapper.listPage(materialUserFootmarkQuery);
		for(MaterialUserFootmarkView footmarkView:list){
			String relationId = footmarkView.getRelationId();
			//根据案例id和用户id查询
			MaterialUserCollection materialUserCollection= materialUserCollectionService.loadByCaseIdAndUserId(relationId,userId);
			if(materialUserCollection !=null ){
				footmarkView.setIsCollection("1");
			}else {
				footmarkView.setIsCollection("0");
			}
		}
		materialUserFootmarkQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialUserFootmark materialUserFootmark) {
		materialUserFootmark.setId(Guid.guid());
		materialUserFootmarkMapper.insert(materialUserFootmark);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialUserFootmark materialUserFootmark) {
		materialUserFootmarkMapper.updateById(materialUserFootmark);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialUserFootmark materialUserFootmark) {
		materialUserFootmarkMapper.updateAllColumnById(materialUserFootmark);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialUserFootmarkMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialUserFootmarkMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialUserFootmark loadById(String id) {
		return materialUserFootmarkMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public MaterialUserFootmark insertOrUpdate(MaterialUserFootmark userFootmark) {
		//查询当日的用户足迹是否存在
		MaterialUserFootmark userFootmark1 = materialUserFootmarkMapper.loadByVo(userFootmark);
		if(userFootmark1==null){
			MaterialCaseView caseView = caseMapper.loadById(userFootmark.getRelationId());
			if (caseView !=null){
				userFootmark.setImageHeight(caseView.getImageHeight());
				userFootmark.setImageWidth(caseView.getImageWidth());
			}else {
				userFootmark.setImageHeight(0);
				userFootmark.setImageWidth(0);
			}
			userFootmark.setId(Guid.guid());
			materialUserFootmarkMapper.insert(userFootmark);
		}else {
			userFootmark.setId(userFootmark1.getId());
			userFootmark.setCreateTime(new Date());
		}
		return userFootmark;
	}
	/*清除用户足迹*/
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteVo(String userId, String type) {
		materialUserFootmarkMapper.deleteVo(userId,type);
	}
}
