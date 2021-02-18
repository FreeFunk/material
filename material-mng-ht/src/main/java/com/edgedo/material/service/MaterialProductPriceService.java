package com.edgedo.material.service;
		
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import com.edgedo.check.checkService.CheckOperatorService;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.service.SysCheckService;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.Material;
import com.edgedo.material.entity.MaterialProductPrice;
import com.edgedo.material.entity.MaterialProductPriceFile;
import com.edgedo.material.mapper.MaterialMapper;
import com.edgedo.material.mapper.MaterialProductPriceFileMapper;
import com.edgedo.material.mapper.MaterialProductPriceMapper;
import com.edgedo.material.queryvo.MaterialProductPriceQuery;
import com.edgedo.material.queryvo.MaterialProductPriceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProductPriceService implements CheckOperatorService {
	
	
	@Autowired
	private MaterialProductPriceMapper materialProductPriceMapper;
	@Autowired
	private MaterialProductPriceFileMapper materialProductPriceFileMapper;
	@Autowired
	private MaterialMapper materialMapper;
	@Value("${fileForder}")
	private String fileForder;
	@Autowired
	private SysCheckService sysCheckService;

	public List<MaterialProductPriceView> listPage(MaterialProductPriceQuery materialProductPriceQuery){
		List list = materialProductPriceMapper.listPage(materialProductPriceQuery);
		materialProductPriceQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProductPrice materialProductPrice) {
		materialProductPrice.setId(Guid.guid());
		materialProductPriceMapper.insert(materialProductPrice);
		return "";
	}


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertView(MaterialProductPriceView materialProductPrice) {
		materialProductPrice.setId(Guid.guid());
		//查询材料名称
		Material material = materialMapper.selectById(materialProductPrice.getMaterialId());
		materialProductPrice.setMaterialName(material.getMaterialName());
		//排序号
		if (materialProductPrice.getOrderNum()==null){
			materialProductPrice.setOrderNum(new BigDecimal(materialProductPriceMapper.selectOrderPriceNum(materialProductPrice.getMaterialId())+1));
		}
		materialProductPriceMapper.insert(materialProductPrice);
		return "";
	}

	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProductPrice materialProductPrice) {
		//查询材料名称
		Material material = materialMapper.selectById(materialProductPrice.getMaterialId());
		materialProductPrice.setMaterialName(material.getMaterialName());
		materialProductPriceMapper.updateById(materialProductPrice);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProductPrice materialProductPrice) {
		materialProductPriceMapper.updateAllColumnById(materialProductPrice);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProductPriceMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialProductPriceMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProductPrice loadById(String id) {
		return materialProductPriceMapper.selectById(id);
	}


	public void updateEnableState(String id, String isEnable) {
		materialProductPriceMapper.updateEnableState( id,  isEnable);
	}

	@Override
	public String updateVoVerifyInfo(SysCheck checkVo) {
		String errMsg = "";
		String serviceId = checkVo.getServiceId();
		MaterialProductPrice productPrice = loadById(serviceId);
		if(productPrice == null){
			errMsg = "未查询到审核记录";
		}else{
			productPrice.setShState(checkVo.getShState());
			productPrice.setShTime(checkVo.getShTime());
			productPrice.setShUserId(checkVo.getShUserId());
			productPrice.setShUserName(checkVo.getShUserName());
			String shState = checkVo.getShState();
			if(shState != null && !shState.equals("1")){
				productPrice.setNotPassText(checkVo.getNotPassText());
			}
			update(productPrice);
		}
		return errMsg;
	}
}
