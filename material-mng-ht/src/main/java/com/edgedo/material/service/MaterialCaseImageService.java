package com.edgedo.material.service;
		
import java.math.BigDecimal;
import java.util.List;

import com.edgedo.check.checkService.CheckOperatorService;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.MaterialCase;
import com.edgedo.material.entity.MaterialCaseImage;
import com.edgedo.material.mapper.MaterialCaseImageMapper;
import com.edgedo.material.queryvo.MaterialCaseImageQuery;
import com.edgedo.material.queryvo.MaterialCaseImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialCaseImageService implements CheckOperatorService {
	
	
	@Autowired
	private MaterialCaseImageMapper materialCaseImageMapper;
	@Autowired
	private MaterialCaseService materialCaseService;

	public List<MaterialCaseImageView> listPage(MaterialCaseImageQuery materialCaseImageQuery){
		List list = materialCaseImageMapper.listPage(materialCaseImageQuery);
		materialCaseImageQuery.setList(list);
		return list;
	}

	public List<MaterialCaseImageView> listByObj(MaterialCaseImageQuery materialCaseImageQuery){
		List list = materialCaseImageMapper.listByObj(materialCaseImageQuery);
		materialCaseImageQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialCaseImage materialCaseImage) {
		Integer num = materialCaseImageMapper.count(materialCaseImage.getCaseId())+1;
		materialCaseImage.setOrderNum(new BigDecimal(num));
		materialCaseImage.setId(Guid.guid());
		//查询 案例色卡分类名 名称
		String caseId = materialCaseImage.getCaseId();
		MaterialCase materialCase = materialCaseService.loadById(caseId);
		String title = "";
		String caseClsName = materialCase.getCaseClsName();
		String caseName = materialCase.getCaseName();
		String materName = materialCase.getMaterialName();
		if (materName!=null && !materName.equals("")){
			title = materName+"-";
		}
		if (caseClsName!=null && !caseClsName.equals("")){
			title = title+caseClsName+"-";
		}
		if (caseName!=null && !caseName.equals("")){
			title = title + caseName+"-";
		}
		if (num<10){
			title = title + "0"+num;
		}else {
			title = title + num;
		}
		materialCaseImage.setImageTitle(title);
		materialCaseImageMapper.insert(materialCaseImage);
		return "";
	}


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertColor(MaterialCaseImage materialCaseImage) {
		Integer num = materialCaseImageMapper.count(materialCaseImage.getCaseId())+1;
		materialCaseImage.setOrderNum(new BigDecimal(num));
		materialCaseImage.setId(Guid.guid());
		//查询 案例色卡分类名 名称
		String caseId = materialCaseImage.getCaseId();
		MaterialCase materialCase = materialCaseService.loadById(caseId);
		String title = "";
		String caseClsName = materialCase.getCaseClsName();
		String caseName = materialCase.getCaseName();
		String materName = materialCase.getMaterialName();
		if (materName!=null && !materName.equals("")){
			title = materName+"-";
		}
		if (caseClsName!=null && !caseClsName.equals("")){
			title = title+caseClsName;
		}
		materialCaseImage.setImageTitle(title);
		materialCaseImageMapper.insert(materialCaseImage);
		return "";
	}


	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialCaseImage materialCaseImage) {
		materialCaseImageMapper.updateById(materialCaseImage);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialCaseImage materialCaseImage) {
		materialCaseImageMapper.updateAllColumnById(materialCaseImage);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialCaseImageMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialCaseImageMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialCaseImage loadById(String id) {
		return materialCaseImageMapper.selectById(id);
	}

	/**
	 * 根据产品id查询关联图片
	 * @author: ZhangCC
	 * @time: 2020/9/2 12:58
	 */
	public MaterialCaseImageView selectVoByCaseId(String caseId){
		return materialCaseImageMapper.selectVoByCaseId(caseId);
	}


	@Override
	public String updateVoVerifyInfo(SysCheck checkVo) {
		String errMsg = "";
		String serviceId = checkVo.getServiceId();
		MaterialCaseImage caseImage = loadById(serviceId);
		if(caseImage == null){
			errMsg = "未查询到审核记录";
		}else{
			caseImage.setShState(checkVo.getShState());
			caseImage.setShTime(checkVo.getShTime());
			caseImage.setShUserId(checkVo.getShUserId());
			caseImage.setShUserName(checkVo.getShUserName());
			String shState = checkVo.getShState();
			if(shState != null && !shState.equals("1")){
				caseImage.setNotPassText(checkVo.getNotPassText());
			}
			update(caseImage);
		}
		return errMsg;
	}
}
