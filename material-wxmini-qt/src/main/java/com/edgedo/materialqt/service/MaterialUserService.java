package com.edgedo.materialqt.service;
		
import java.util.Date;
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialUser;
import com.edgedo.materialqt.mapper.MaterialUserMapper;
import com.edgedo.materialqt.queryvo.MaterialUserQuery;
import com.edgedo.materialqt.queryvo.MaterialUserView;
import com.edgedo.sys.entity.SysWxUser;
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
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialUser materialUser) {
		materialUser.setId(Guid.guid());
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
		
		return materialUserMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialUserMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialUser loadById(String id) {
		return materialUserMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public MaterialUser saveOrUpdate(SysWxUser sysWxUser) {
		//根据miniOpenId查询用户是否存在
		MaterialUser materialUser = materialUserMapper.loadByMiniOpenId(sysWxUser.getOpenId());
		if(materialUser == null){
			materialUser = new MaterialUser();
			materialUser.setId(Guid.guid());
			materialUser.setCreateTime(new Date());
			materialUser.setPhoneNum(sysWxUser.getPhoneNum());
			materialUser.setNickName(sysWxUser.getNickName());
			materialUser.setHeadPhoto(sysWxUser.getHeadPhoto());
			materialUser.setMiniOpenId(sysWxUser.getOpenId());
			// 1：员工 0：普通用户
			materialUser.setUserType("0");
			materialUser.setIsPower("0");
			materialUser.setDataState("1");
			materialUserMapper.insert(materialUser);
		}else {
			materialUser.setPhoneNum(sysWxUser.getPhoneNum());
			materialUser.setNickName(sysWxUser.getNickName());
			materialUser.setHeadPhoto(sysWxUser.getHeadPhoto());
			materialUserMapper.updateById(materialUser);
		}
		return  materialUser;
	}
	/*根据openId查询用户*/
	public MaterialUser loadByMiniOpenId(String miniOpenId) {
		return materialUserMapper.loadByMiniOpenId(miniOpenId);
	}
}
