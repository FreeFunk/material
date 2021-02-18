package com.edgedo.check.service;
		
import java.util.List;

import com.edgedo.check.checkService.CheckOperatorService;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.mapper.SysCheckMapper;
import com.edgedo.check.queryvo.SysCheckQuery;
import com.edgedo.common.base.BusinessException;
import com.edgedo.common.util.Guid;
import com.edgedo.check.queryvo.SysCheckView;
import com.edgedo.common.util.IocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class SysCheckService {
	
	
	@Autowired
	private SysCheckMapper sysCheckMapper;

	public List<SysCheckView> listPage(SysCheckQuery sysCheckQuery){
		List list = sysCheckMapper.listPage(sysCheckQuery);
		sysCheckQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(SysCheck sysCheck) {
		sysCheck.setId(Guid.guid());
		sysCheckMapper.insert(sysCheck);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(SysCheck sysCheck) {
		sysCheckMapper.updateById(sysCheck);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(SysCheck sysCheck) {
		sysCheckMapper.updateAllColumnById(sysCheck);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return sysCheckMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return sysCheckMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public SysCheck loadById(String id) {
		return sysCheckMapper.selectById(id);
	}

	/**
	 * 记录审核处理
	 * @author: ZhangCC
	 * @time: 2020/8/20 18:45
	 */
	public String updateVoVerifyInfo(SysCheck checkVo){
		String serviceType = checkVo.getServiceType();
		try{
			Object operator = IocUtil.getBean(Class.forName(serviceType));
			if(operator==null || operator.equals("")){
				return "未查询到处理方式";
			}else{
				int count = sysCheckMapper.updateById(checkVo);
				if(count > 0){
					CheckOperatorService operatorService = (CheckOperatorService)operator;
					operatorService.updateVoVerifyInfo(checkVo);
				}
			}
		} catch (Exception e){
			return e.getMessage();
		}
		return "";
	}
	

}
