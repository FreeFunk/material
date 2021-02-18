package com.edgedo.material.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.MaterialProducer;
import com.edgedo.material.entity.MaterialProducerSysUserRelation;
import com.edgedo.material.mapper.MaterialProducerSysUserRelationMapper;
import com.edgedo.material.queryvo.MaterialProducerSysUserRelationQuery;
import com.edgedo.material.queryvo.MaterialProducerSysUserRelationView;
import com.edgedo.sys.entity.SysUser;
import com.edgedo.sys.queryvo.SysUserQuery;
import com.edgedo.sys.queryvo.SysUserView;
import com.edgedo.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProducerSysUserRelationService {
	
	
	@Autowired
	private MaterialProducerSysUserRelationMapper materialProducerSysUserRelationMapper;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private MaterialProducerService producerService;

	public List<MaterialProducerSysUserRelationView> listPage(MaterialProducerSysUserRelationQuery materialProducerSysUserRelationQuery){
		List list = materialProducerSysUserRelationMapper.listPage(materialProducerSysUserRelationQuery);
		materialProducerSysUserRelationQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProducerSysUserRelation materialProducerSysUserRelation) {
		materialProducerSysUserRelation.setId(Guid.guid());
		materialProducerSysUserRelation.setCreateTime(new Date());
		materialProducerSysUserRelationMapper.insert(materialProducerSysUserRelation);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProducerSysUserRelation materialProducerSysUserRelation) {
		materialProducerSysUserRelationMapper.updateById(materialProducerSysUserRelation);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProducerSysUserRelation materialProducerSysUserRelation) {
		materialProducerSysUserRelationMapper.updateAllColumnById(materialProducerSysUserRelation);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProducerSysUserRelationMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {

		return materialProducerSysUserRelationMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProducerSysUserRelation loadById(String id) {
		return materialProducerSysUserRelationMapper.selectById(id);
	}

	/**
	 * 添加厂商用户
	 * @return
	 */
	public String insertProducerUser(MaterialProducerSysUserRelationView userRelationView){
		String errMsg = "";
		String producerId = userRelationView.getProducerId();
		MaterialProducer producer = producerService.loadById(producerId);
		if(producer == null){
			return "未查询到厂商信息";
		}
		MaterialProducerSysUserRelation userRelation = new MaterialProducerSysUserRelation();
		userRelation.setProducerId(producerId);
		userRelation.setUserCode(userRelationView.getUserCode());
		//添加用户
		SysUser sysUser = new SysUser();
		sysUser.setAge(24);
		sysUser.setUserSex("男");
		sysUser.setPassword(userRelationView.getPassword());
		sysUser.setUserCode(userRelationView.getUserCode());
		sysUser.setCompId(producerId);
		sysUser.setUserName(producer.getContactUserName());
		sysUser.setDefaultRoleId("PRODUCER");
		sysUser.setDefaultRoleName("厂商管理员");
		errMsg = sysUserService.insert(sysUser);
		if(errMsg.equals("")){
			userRelation.setSysUserId(sysUser.getId());
			errMsg = insert(userRelation);
		}
		return errMsg;
	}

	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	public String resetPwdById(String id){
		String errMsg = "";
		MaterialProducerSysUserRelation userRelation = loadById(id);
		if(userRelation == null){
			return "未查询到关联账号";
		}
		String sysUserId = userRelation.getSysUserId();
		sysUserService.updateResetPwd(sysUserId);
		return errMsg;
	}

	/**
	 * 修改账号信息
	 * @return
	 */
	public String updateUserCode(String id,String userCode){
		String errMsg = "";
		MaterialProducerSysUserRelation userRelation = loadById(id);
		if(userRelation == null){
			return "未查询到关联账号";
		}
		String sysUserId = userRelation.getSysUserId();
		SysUserView checkUser = sysUserService.getAdminUserByCode(userCode);
		if(checkUser != null){
			return "账号已经存在";
		}
		userRelation.setUserCode(userCode);
		update(userRelation);
		//更新sysUser
		SysUserQuery updateUser = new SysUserQuery();
		updateUser.getQueryObj().setId(sysUserId);
		updateUser.getQueryObj().setUserCode(userCode);
		sysUserService.updateCodeAndPwdById(updateUser);
		return errMsg;
	}

	/**
	 * 删除账号
	 * @return
	 */
	public String deleteProducerUser(String id){
		MaterialProducerSysUserRelation userRelation = loadById(id);
		if(userRelation == null){
			return "未查询到关联账号";
		}
		int count = delete(id);
		if(count == 1){
			String sysUserId = userRelation.getSysUserId();
			sysUserService.delete(sysUserId);
		}else{
			return "删除失败";
		}
		return "";
	}


	public String selectBySysUserId(String userId) {
		return materialProducerSysUserRelationMapper.selectBySysUserId(userId);
	}
}
