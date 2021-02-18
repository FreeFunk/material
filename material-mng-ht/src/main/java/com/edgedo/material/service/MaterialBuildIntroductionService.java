package com.edgedo.material.service;
		
import java.util.List;

import com.edgedo.check.checkService.CheckOperatorService;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.Material;
import com.edgedo.material.entity.MaterialBuildIntroduction;
import com.edgedo.material.mapper.MaterialBuildIntroductionMapper;
import com.edgedo.material.mapper.MaterialMapper;
import com.edgedo.material.queryvo.MaterialBuildIntroductionQuery;
import com.edgedo.material.queryvo.MaterialBuildIntroductionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialBuildIntroductionService implements CheckOperatorService {
	
	
	@Autowired
	private MaterialBuildIntroductionMapper materialBuildIntroductionMapper;
	@Autowired
	private MaterialMapper materialMapper;

	public List<MaterialBuildIntroductionView> listPage(MaterialBuildIntroductionQuery materialBuildIntroductionQuery){
		List list = materialBuildIntroductionMapper.listPage(materialBuildIntroductionQuery);
		materialBuildIntroductionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialBuildIntroduction materialBuildIntroduction) {
		Material material = materialMapper.selectById(materialBuildIntroduction.getMaterialId());
		materialBuildIntroduction.setMaterialName(material.getMaterialName());
		materialBuildIntroduction.setId(Guid.guid());
		materialBuildIntroductionMapper.insert(materialBuildIntroduction);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialBuildIntroduction materialBuildIntroduction) {
		Material material = materialMapper.selectById(materialBuildIntroduction.getMaterialId());
		materialBuildIntroduction.setMaterialName(material.getMaterialName());
//		materialBuildIntroduction.setId(Guid.guid());
		materialBuildIntroductionMapper.updateById(materialBuildIntroduction);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialBuildIntroduction materialBuildIntroduction) {
		materialBuildIntroductionMapper.updateAllColumnById(materialBuildIntroduction);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialBuildIntroductionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialBuildIntroductionMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialBuildIntroduction loadById(String id) {
		return materialBuildIntroductionMapper.selectById(id);
	}


	@Override
	public String updateVoVerifyInfo(SysCheck checkVo) {
		String errMsg = "";
		String serviceId = checkVo.getServiceId();
		MaterialBuildIntroduction introduction = loadById(serviceId);
		if(introduction == null){
			errMsg = "未查询到审核记录";
		}else{
			introduction.setShState(checkVo.getShState());
			introduction.setShTime(checkVo.getShTime());
			introduction.setShUserId(checkVo.getShUserId());
			introduction.setShUserName(checkVo.getShUserName());
			String shState = checkVo.getShState();
			if(shState != null && !shState.equals("1")){
				introduction.setNotPassText(checkVo.getNotPassText());
			}
			update(introduction);
		}
		return errMsg;
	}

	public MaterialBuildIntroductionView selectByMaterId(String materId) {
		return materialBuildIntroductionMapper.selectByMaterId(materId);
	}
}
