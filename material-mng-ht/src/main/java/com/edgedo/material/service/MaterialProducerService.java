package com.edgedo.material.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.MaterialProducer;
import com.edgedo.material.entity.MaterialProducerSysUserRelation;
import com.edgedo.material.mapper.MaterialProducerMapper;
import com.edgedo.material.queryvo.MaterialProducerQuery;
import com.edgedo.material.queryvo.MaterialProducerView;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProducerService {
	
	
	@Autowired
	private MaterialProducerMapper materialProducerMapper;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private MaterialProducerSysUserRelationService producerUserRelationService;

	public List<MaterialProducerView> listPage(MaterialProducerQuery materialProducerQuery){
		List list = materialProducerMapper.listPage(materialProducerQuery);
		materialProducerQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProducer materialProducer) {
		materialProducer.setId(Guid.guid());
		materialProducer.setCreateTime(new Date());
		materialProducer.setDataState("1");
		materialProducer.setIsNeedSh("1");
		/*String isNeedSh = materialProducer.getIsNeedSh();
		if(isNeedSh == null || isNeedSh.equals("")){
			materialProducer.setIsNeedSh("1");
		}*/
		materialProducerMapper.insert(materialProducer);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProducer materialProducer) {
		String isNeedSh = materialProducer.getIsNeedSh();
		if(isNeedSh == null || isNeedSh.equals("")){
			materialProducer.setIsNeedSh("0");
		}else{
			materialProducer.setIsNeedSh("1");
		}
		materialProducerMapper.updateById(materialProducer);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProducer materialProducer) {
		materialProducerMapper.updateAllColumnById(materialProducer);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProducerMapper.logicDeleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByIds(List<String> ids) {
		for(int i=0;i<ids.size();i++){
			materialProducerMapper.logicDeleteById(ids.get(i));
		}
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProducer loadById(String id) {
		return materialProducerMapper.selectById(id);
	}

	/**
	 * 添加厂商和账号
	 * @param voObj
	 * @param userCode
	 * @return
	 */
	public String insertVoAndUser(MaterialProducer voObj,String userCode){
		String errMsg = "";
		errMsg = insert(voObj);
		if(errMsg.equals("")){
			//为厂商添加账号
			SysUser sysUser = new SysUser();
			sysUser.setAge(24);
			sysUser.setUserSex("男");
			sysUser.setPassword("888888");
			sysUser.setUserCode(userCode);
			sysUser.setCompId(voObj.getId());
			sysUser.setUserName(voObj.getContactUserName());
			sysUser.setDefaultRoleId("PRODUCER");
			sysUser.setDefaultRoleName("厂商管理员");
			errMsg = sysUserService.insert(sysUser);
			//添加厂商账号
			MaterialProducerSysUserRelation userRelation = new MaterialProducerSysUserRelation();
			userRelation.setCreateUserId(voObj.getCreateUserId());
			userRelation.setProducerId(voObj.getId());
			userRelation.setSysUserId(sysUser.getId());
			userRelation.setUserCode(userCode);
			producerUserRelationService.insert(userRelation);
		}
		return errMsg;
	}
	

}
