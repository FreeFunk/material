package com.edgedo.material.service;
		
import java.math.BigDecimal;
import java.util.List;

import com.edgedo.check.checkService.CheckOperatorService;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.Material;
import com.edgedo.material.entity.MaterialCaseCls;
import com.edgedo.material.mapper.MaterialCaseClsMapper;
import com.edgedo.material.mapper.MaterialMapper;
import com.edgedo.material.queryvo.MaterialCaseClsQuery;
import com.edgedo.material.queryvo.MaterialCaseClsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialCaseClsService implements CheckOperatorService {
	
	
	@Autowired
	private MaterialCaseClsMapper materialCaseClsMapper;
	@Autowired
	private MaterialMapper materialMapper;

	public List<MaterialCaseClsView> listPage(MaterialCaseClsQuery materialCaseClsQuery){
		List list = materialCaseClsMapper.listPage(materialCaseClsQuery);
		materialCaseClsQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialCaseCls materialCaseCls) {
		Material material  = materialMapper.selectById(materialCaseCls.getMaterialId());
		materialCaseCls.setMaterialName(material.getMaterialName());
		if (materialCaseCls.getOrderNum()==null){
			materialCaseCls.setOrderNum(new BigDecimal(materialCaseClsMapper.count(materialCaseCls.getMaterialId(),materialCaseCls.getType())+1));
		}
		materialCaseCls.setId(Guid.guid());
		materialCaseClsMapper.insert(materialCaseCls);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialCaseCls materialCaseCls) {
		Material material  = materialMapper.selectById(materialCaseCls.getMaterialId());
		materialCaseCls.setMaterialName(material.getMaterialName());
		materialCaseClsMapper.updateById(materialCaseCls);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialCaseCls materialCaseCls) {
		materialCaseClsMapper.updateAllColumnById(materialCaseCls);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialCaseClsMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialCaseClsMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialCaseCls loadById(String id) {
		return materialCaseClsMapper.selectById(id);
	}

	public void updateEnableState(String id, String isEnable) {
		materialCaseClsMapper.updateEnableState(id,isEnable);
	}

	public List<MaterialCaseClsView> materialClsNameAll() {
		return materialCaseClsMapper.materialClsNameAll();
	}

	public List<MaterialCaseClsView> materialClsNameAllColor() {
		return materialCaseClsMapper.materialClsNameAllColor();
	}

	public List<MaterialCaseClsView> materialNameAndClsId(String materialId) {
		return materialCaseClsMapper.materialNameAndClsId(materialId);
	}

	@Override
	public String updateVoVerifyInfo(SysCheck checkVo) {
		String errMsg = "";
		String serviceId = checkVo.getServiceId();
		MaterialCaseCls caseCls = loadById(serviceId);
		if(caseCls == null){
			errMsg = "未查询到审核记录";
		}else{
			caseCls.setShState(checkVo.getShState());
			caseCls.setShTime(checkVo.getShTime());
			caseCls.setShUserId(checkVo.getShUserId());
			caseCls.setShUserName(checkVo.getShUserName());
			String shState = checkVo.getShState();
			if(shState != null && !shState.equals("1")){
				caseCls.setNotPassText(checkVo.getNotPassText());
			}
			update(caseCls);
		}
		return errMsg;
	}

	public List<MaterialCaseClsView> materialClsNameAllMaterIdList(List<String> materialIdList) {
		return materialCaseClsMapper.materialClsNameAllMaterIdList(materialIdList);
	}

	public List<MaterialCaseClsView> materialClsNameAllMaterIdListColor(List<String> materialIdList) {
		return materialCaseClsMapper.materialClsNameAllMaterIdListColor(materialIdList);
	}
}
