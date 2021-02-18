package com.edgedo.material.service;
		
import java.math.BigDecimal;
import java.util.List;

import com.edgedo.check.checkService.CheckOperatorService;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.Material;
import com.edgedo.material.entity.MaterialCase;
import com.edgedo.material.entity.MaterialCaseCls;
import com.edgedo.material.mapper.MaterialCaseClsMapper;
import com.edgedo.material.mapper.MaterialCaseMapper;
import com.edgedo.material.mapper.MaterialMapper;
import com.edgedo.material.queryvo.MaterialCaseQuery;
import com.edgedo.material.queryvo.MaterialCaseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialCaseService implements CheckOperatorService {
	
	
	@Autowired
	private MaterialCaseMapper materialCaseMapper;
	@Autowired
	private MaterialMapper materialMapper;
	@Autowired
	private MaterialCaseClsMapper materialCaseClsMapper;

	public List<MaterialCaseView> listPage(MaterialCaseQuery materialCaseQuery){
		List list = materialCaseMapper.listPage(materialCaseQuery);
		materialCaseQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialCase materialCase) {
		Material material = materialMapper.selectById(materialCase.getMaterialId());
		materialCase.setMaterialName(material.getMaterialName());
		MaterialCaseCls materialCaseCls = materialCaseClsMapper.selectById(materialCase.getCaseClsId());
		materialCase.setCaseClsName(materialCaseCls.getCaseClsName());
		if (materialCase.getOrderNum()==null){
			materialCase.setOrderNum(new BigDecimal(materialCaseMapper.selectMaIdAndClsId(materialCase.getMaterialId(),materialCase.getCaseClsId(),materialCase.getType())+1));
		}
		materialCase.setId(Guid.guid());
		materialCaseMapper.insert(materialCase);
		return "";
	}


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertAll(MaterialCase materialCase) {
		Material material = materialMapper.selectById(materialCase.getMaterialId());
		materialCase.setMaterialName(material.getMaterialName());
		MaterialCaseCls materialCaseCls = materialCaseClsMapper.selectById(materialCase.getCaseClsId());
		materialCase.setCaseClsName(materialCaseCls.getCaseClsName());
		materialCase.setOrderNum(new BigDecimal(materialCaseMapper.selectMaIdAndClsId(materialCase.getMaterialId(),materialCase.getCaseClsId(),materialCase.getType())+1));

		if(materialCase.getCaseName()==null){
			String casseName = material.getMaterialName()+"-"+materialCaseCls.getCaseClsName()+"-";
			if(materialCase.getOrderNum().intValue()<10){
				casseName = casseName+"0"+materialCase.getOrderNum();
			}else {
				casseName = casseName+materialCase.getOrderNum();
			}
			materialCase.setCaseName(casseName);
		}
		materialCase.setId(Guid.guid());
		materialCaseMapper.insert(materialCase);
		return "";
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertAllColor(MaterialCase materialCase) {
		Material material = materialMapper.selectById(materialCase.getMaterialId());
		materialCase.setMaterialName(material.getMaterialName());
		MaterialCaseCls materialCaseCls = materialCaseClsMapper.selectById(materialCase.getCaseClsId());
		materialCase.setCaseClsName(materialCaseCls.getCaseClsName());
		materialCase.setOrderNum(new BigDecimal(materialCaseMapper.selectMaIdAndClsId(materialCase.getMaterialId(),materialCase.getCaseClsId(),materialCase.getType())+1));

		if(materialCase.getCaseName()==null){
			String casseName = material.getMaterialName()+"-"+materialCaseCls.getCaseClsName();
			materialCase.setCaseName(casseName);
		}
		materialCase.setId(Guid.guid());
		materialCaseMapper.insert(materialCase);
		return "";
	}


	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialCase materialCase) {
		Material material = materialMapper.selectById(materialCase.getMaterialId());
		materialCase.setMaterialName(material.getMaterialName());
		MaterialCaseCls materialCaseCls = materialCaseClsMapper.selectById(materialCase.getCaseClsId());
		materialCase.setCaseClsName(materialCaseCls.getCaseClsName());
		materialCaseMapper.updateById(materialCase);
		return "";
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateById(MaterialCase materialCase) {
		materialCaseMapper.updateById(materialCase);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialCase materialCase) {
		materialCaseMapper.updateAllColumnById(materialCase);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialCaseMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialCaseMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialCase loadById(String id) {
		return materialCaseMapper.selectById(id);
	}



	public void updateEnableState(String id, String isEnable) {
		materialCaseMapper.updateEnableState(id,isEnable);
	}

	@Override
	public String updateVoVerifyInfo(SysCheck checkVo) {
		String errMsg = "";
		String serviceId = checkVo.getServiceId();
		MaterialCase materialCase = loadById(serviceId);
		if(materialCase == null){
			errMsg = "未查询到审核记录";
		}else{
			materialCase.setShState(checkVo.getShState());
			materialCase.setShTime(checkVo.getShTime());
			materialCase.setShUserId(checkVo.getShUserId());
			materialCase.setShUserName(checkVo.getShUserName());
			String shState = checkVo.getShState();
			if(shState != null && !shState.equals("1")){
				materialCase.setNotPassText(checkVo.getNotPassText());
			}
			update(materialCase);
		}
		return errMsg;
	}

	public Integer selectByCaseNum(String materialId, String caseClsId,String type) {
		return materialCaseMapper.selectMaIdAndClsId(materialId,caseClsId,type);
	}

	/*查询所有的案例*/
	public List<MaterialCaseView> listAll() {
		MaterialCaseQuery query = new MaterialCaseQuery();
		query.getQueryObj().setType("CASE");
		return  materialCaseMapper.listByObj(query);
	}

	public List<MaterialCase> listByWidth(String id) {
		return materialCaseMapper.listByWidth(id);
	}
}
