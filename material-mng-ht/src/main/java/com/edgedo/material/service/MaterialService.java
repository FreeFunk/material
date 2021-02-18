package com.edgedo.material.service;
		
import java.math.BigDecimal;
import java.util.List;

import com.edgedo.check.checkService.CheckOperatorService;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.Material;
import com.edgedo.material.mapper.MaterialMapper;
import com.edgedo.material.queryvo.MaterialQuery;
import com.edgedo.material.queryvo.MaterialView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialService implements CheckOperatorService {
	
	
	@Autowired
	private MaterialMapper materialMapper;

	public List<MaterialView> listPage(MaterialQuery materialQuery){
		List list = materialMapper.listPage(materialQuery);
		materialQuery.setList(list);
		return list;
	}

	/**
	 * 厂商未关联的材料列表
	 * @author: ZhangCC
	 * @time: 2020/9/10 9:26
	 */
	public List<MaterialView> proNotRelListPage(MaterialQuery query){
		List list = materialMapper.proNotRelListPage(query);
		query.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(Material material) {
		material.setId(Guid.guid());
		materialMapper.insert(material);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(Material material) {
		materialMapper.updateById(material);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(Material material) {
		materialMapper.updateAllColumnById(material);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public Material loadById(String id) {
		return materialMapper.selectById(id);
	}


	//统计所有的材料数量
	public BigDecimal countAllMaterNum() {
		return new BigDecimal(materialMapper.countAllMaterNum()+1);
	}


	/**
	 * 切换启用状态
	 * @param id
	 * @param isEnable
	 */
	public void updateEnableState(String id, String isEnable) {
		materialMapper.updateEnableState( id,  isEnable);
	}

	/**
	 * 切换产品样式的启用状态
	 * @param id
	 * @param isShowProductStyle
	 */
	public void updateIsShowProductStyleState(String id, String isShowProductStyle) {
		materialMapper.updateIsShowProductStyleState( id,  isShowProductStyle);
	}

	public List<MaterialView> materialNameAll() {
		return materialMapper.materialNameAll();
	}

	@Override
	public String updateVoVerifyInfo(SysCheck checkVo) {
		String errMsg = "";
		String serviceId = checkVo.getServiceId();
		Material material = loadById(serviceId);
		if(material == null){
			errMsg = "未查询到审核记录";
		}else{
			material.setShState(checkVo.getShState());
			material.setShTime(checkVo.getShTime());
			material.setShUserId(checkVo.getShUserId());
			material.setShUserName(checkVo.getShUserName());
			String shState = checkVo.getShState();
			if(shState != null && !shState.equals("1")){
				material.setNotPassText(checkVo.getNotPassText());
			}
			update(material);
		}
		return errMsg;
	}

	public List<MaterialView> materialNameProdouceId(List<String> materialIdList) {
		return materialMapper.materialNameProdouceId(materialIdList);
	}

	public List<MaterialView> notRelListPage(MaterialQuery query){
		List list = materialMapper.notRelListPage(query);
		query.setList(list);
		return list;
	}

}
