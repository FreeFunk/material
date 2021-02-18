package com.edgedo.material.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.MaterialSysMessage;
import com.edgedo.material.mapper.MaterialSysMessageMapper;
import com.edgedo.material.queryvo.MaterialSysMessageQuery;
import com.edgedo.material.queryvo.MaterialSysMessageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialSysMessageService {
	
	
	@Autowired
	private MaterialSysMessageMapper materialSysMessageMapper;

	public List<MaterialSysMessageView> listPage(MaterialSysMessageQuery materialSysMessageQuery){
		List list = materialSysMessageMapper.listPage(materialSysMessageQuery);
		materialSysMessageQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialSysMessage materialSysMessage) {
		materialSysMessage.setId(Guid.guid());
		materialSysMessage.setCreateTime(new Date());
		materialSysMessage.setDataState("1");
		materialSysMessageMapper.insert(materialSysMessage);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialSysMessage materialSysMessage) {
		materialSysMessageMapper.updateById(materialSysMessage);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialSysMessage materialSysMessage) {
		materialSysMessageMapper.updateAllColumnById(materialSysMessage);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		return materialSysMessageMapper.logicDeleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByIds(List<String> ids) {
		for(int i=0;i<ids.size();i++){
			materialSysMessageMapper.logicDeleteById(ids.get(i));
		}
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialSysMessage loadById(String id) {
		return materialSysMessageMapper.selectById(id);
	}
	

}
