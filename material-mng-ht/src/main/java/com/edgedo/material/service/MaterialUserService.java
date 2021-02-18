package com.edgedo.material.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.common.util.MD5;
import com.edgedo.material.entity.MaterialUser;
import com.edgedo.material.mapper.MaterialUserMapper;
import com.edgedo.material.queryvo.MaterialUserQuery;
import com.edgedo.material.queryvo.MaterialUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialUserService {
	
	
	@Autowired
	private MaterialUserMapper materialUserMapper;

	public List<MaterialUserView> listPage(MaterialUserQuery materialUserQuery){
		List list = materialUserMapper.listPage(materialUserQuery);
		materialUserQuery.setList(list);
		return list;
	}

	public List<MaterialUserView> listByObj(MaterialUserQuery materialUserQuery){
		List list = materialUserMapper.listByObj(materialUserQuery);
		materialUserQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialUser materialUser) {
		if(materialUser.getId() == null){
			materialUser.setId(Guid.guid());
		}
		materialUser.setCreateTime(new Date());
		materialUser.setDataState("1");
		materialUserMapper.insert(materialUser);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialUser materialUser) {
		materialUserMapper.updateById(materialUser);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialUser materialUser) {
		materialUserMapper.updateAllColumnById(materialUser);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		return materialUserMapper.logicDeleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteByIds(List<String> ids) {
		for(int i=0;i<ids.size();i++){
			materialUserMapper.logicDeleteById(ids.get(i));
		}
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialUser loadById(String id) {
		return materialUserMapper.selectById(id);
	}

	/**
	 * 根据手机号查询
	 * @param phoneNum
	 * @return
	 */
	public MaterialUser selectVoByPhoneNum(String phoneNum){
		return materialUserMapper.selectVoByPhoneNum(phoneNum);
	}

	/**
	 * 插入用户
	 * @return
	 */
	public String insertMaterialUser(MaterialUser voObj){
		String errMsg = "";
		voObj.setId(Guid.guid());
		String phoneNum = voObj.getPhoneNum();
		//看看手机号是不是已经有了
		MaterialUser checkUser = materialUserMapper.selectVoByPhoneNum(phoneNum);
		if(checkUser != null){
			return "手机号已经存在";
		}
		voObj.setPassword(MD5.encode(MD5.encode("888888") + voObj.getId()));
		String userType = voObj.getUserType();
		if("1".equals(userType)){
			voObj.setIsPower("1");
		}else{
			voObj.setIsPower("0");
		}
		errMsg = insert(voObj);
		return errMsg;
	}

	/**
	 * 修改用户
	 * @return
	 */
	public String updateMaterialUser(MaterialUser voObj){
		String errMsg = "";
		MaterialUser oraUser = loadById(voObj.getId());
		String oraPhoneNum = oraUser.getPhoneNum();
		String phoneNum = voObj.getPhoneNum();
		if(!oraPhoneNum.equals(phoneNum)){
			//看看手机号是不是已经有了
			MaterialUser checkUser = materialUserMapper.selectVoByPhoneNum(phoneNum);
			if(checkUser != null){
				return "手机号已经存在";
			}
		}
		String userType = voObj.getUserType();
		if("1".equals(userType)){
			voObj.setIsPower("1");
		}else{
			voObj.setIsPower("0");
		}
		errMsg = update(voObj);
		return errMsg;
	}

	/**
	 * 重置用户密码
	 * @param id
	 * @return
	 */
	public String resetUserPwd(String id){
		String errMsg = "";
		MaterialUser checkUser = loadById(id);
		if(checkUser == null){
			return "未查询用户信息";
		}
		String pwd = MD5.encode(MD5.encode("888888")+checkUser.getId());
		checkUser.setPassword(pwd);
		errMsg = update(checkUser);
		return errMsg;
	}
	

}
