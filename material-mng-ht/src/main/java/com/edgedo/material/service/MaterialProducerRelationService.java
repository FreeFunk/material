package com.edgedo.material.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.shiro.User;
import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.MaterialProducerRelation;
import com.edgedo.material.mapper.MaterialProducerRelationMapper;
import com.edgedo.material.queryvo.MaterialProducerRelationQuery;
import com.edgedo.material.queryvo.MaterialProducerRelationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProducerRelationService {
	
	
	@Autowired
	private MaterialProducerRelationMapper materialProducerRelationMapper;

	public List<MaterialProducerRelationView> listPage(MaterialProducerRelationQuery materialProducerRelationQuery){
		List list = materialProducerRelationMapper.listPage(materialProducerRelationQuery);
		materialProducerRelationQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProducerRelation materialProducerRelation) {
		materialProducerRelation.setId(Guid.guid());
		materialProducerRelation.setCreateTime(new Date());
		materialProducerRelationMapper.insert(materialProducerRelation);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProducerRelation materialProducerRelation) {
		materialProducerRelationMapper.updateById(materialProducerRelation);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProducerRelation materialProducerRelation) {
		materialProducerRelationMapper.updateAllColumnById(materialProducerRelation);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProducerRelationMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialProducerRelationMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProducerRelation loadById(String id) {
		return materialProducerRelationMapper.selectById(id);
	}


	public List<String> selectByProducerId(String producerId) {
		return materialProducerRelationMapper.selectByProducerId(producerId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertProMaterialRel(User user, String materialIds, String producerId){
		String[] materialIdArr = materialIds.split(",");
		for(int i=0;i<materialIdArr.length;i++){
			String materialId = materialIdArr[i];
			int count = materialProducerRelationMapper.countByMatAndPro(producerId,materialId);
			if(count <= 0){
				MaterialProducerRelation relation = new MaterialProducerRelation();
				relation.setCreateUserId(user.getUserId());
				relation.setMaterialId(materialId);
				relation.setProducerId(producerId);
				insert(relation);
			}
		}
	}

	public int deleteByMatAndPro(String producerId,String materialId){
		return materialProducerRelationMapper.deleteByMatAndPro(producerId,materialId);
	}

}
