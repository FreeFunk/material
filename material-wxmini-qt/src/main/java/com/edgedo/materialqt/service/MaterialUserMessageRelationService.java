package com.edgedo.materialqt.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialUserMessageRelation;
import com.edgedo.materialqt.mapper.MaterialUserMessageRelationMapper;
import com.edgedo.materialqt.queryvo.MaterialUserMessageRelationQuery;
import com.edgedo.materialqt.queryvo.MaterialUserMessageRelationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialUserMessageRelationService {
	
	
	@Autowired
	private MaterialUserMessageRelationMapper materialUserMessageRelationMapper;

	public List<MaterialUserMessageRelationView> listPage(MaterialUserMessageRelationQuery materialUserMessageRelationQuery){
		List list = materialUserMessageRelationMapper.listPage(materialUserMessageRelationQuery);
		materialUserMessageRelationQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialUserMessageRelation materialUserMessageRelation) {
		materialUserMessageRelation.setId(Guid.guid());
		materialUserMessageRelationMapper.insert(materialUserMessageRelation);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialUserMessageRelation materialUserMessageRelation) {
		materialUserMessageRelationMapper.updateById(materialUserMessageRelation);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialUserMessageRelation materialUserMessageRelation) {
		materialUserMessageRelationMapper.updateAllColumnById(materialUserMessageRelation);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialUserMessageRelationMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialUserMessageRelationMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialUserMessageRelation loadById(String id) {
		return materialUserMessageRelationMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void addOrUpdate(String userId, String sysMessageId) {
		MaterialUserMessageRelation materialUserMessageRelation = new MaterialUserMessageRelation();
		materialUserMessageRelation.setCreateTime(new Date());
		materialUserMessageRelation.setId(Guid.guid());
		materialUserMessageRelation.setIsRead("1");
		materialUserMessageRelation.setUserId(userId);
		materialUserMessageRelation.setSysMessageId(sysMessageId);
		materialUserMessageRelationMapper.insert(materialUserMessageRelation);
	}
	/*根据用户id和公告id查询*/
	public int countByUserIdAndMsgId(String userId, String msgId) {
		return materialUserMessageRelationMapper.countByUserIdAndMsgId(userId,msgId);
	}
}
