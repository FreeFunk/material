package com.edgedo.material.service;
		
import java.math.BigDecimal;
import java.util.List;

import com.edgedo.check.checkService.CheckOperatorService;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.service.SysCheckService;
import com.edgedo.common.util.Guid;
import com.edgedo.material.entity.Material;
import com.edgedo.material.entity.MaterialBanner;
import com.edgedo.material.mapper.MaterialBannerMapper;
import com.edgedo.material.mapper.MaterialMapper;
import com.edgedo.material.queryvo.MaterialBannerQuery;
import com.edgedo.material.queryvo.MaterialBannerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialBannerService implements CheckOperatorService {
	
	
	@Autowired
	private MaterialBannerMapper materialBannerMapper;
	@Autowired
	private MaterialMapper materialMapper;

	public List<MaterialBannerView> listPage(MaterialBannerQuery materialBannerQuery){
		List list = materialBannerMapper.listPage(materialBannerQuery);
		materialBannerQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialBanner materialBanner) {
		//排序号
		if (materialBanner.getOrderNum()==null){
			Integer num = materialBannerMapper.selectByMaterId(materialBanner.getMaterialId());
			materialBanner.setOrderNum(new BigDecimal(num+1));
		}
		//所属材料名称
		Material material = materialMapper.selectById(materialBanner.getMaterialId());
		materialBanner.setMaterialName(material.getMaterialName());
		materialBanner.setId(Guid.guid());
		materialBannerMapper.insert(materialBanner);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialBanner materialBanner) {
		//所属材料名称
		Material material = materialMapper.selectById(materialBanner.getMaterialId());
		materialBanner.setMaterialName(material.getMaterialName());
		materialBannerMapper.updateById(materialBanner);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialBanner materialBanner) {
		materialBannerMapper.updateAllColumnById(materialBanner);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialBannerMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialBannerMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialBanner loadById(String id) {
		return materialBannerMapper.selectById(id);
	}


	public void updateEnableState(String id, String isEnable) {
		materialBannerMapper.updateEnableState(id,isEnable);
	}

	/**
	 * 审核成功
	 * @author: ZhangCC
	 * @time: 2020/8/20 19:37
	 */
	@Override
	public String updateVoVerifyInfo(SysCheck checkVo) {
		String errMsg = "";
		String serviceId = checkVo.getServiceId();
		MaterialBanner banner = loadById(serviceId);
		if(banner == null){
			errMsg = "未查询到审核记录";
		}else{
			banner.setShState(checkVo.getShState());
			banner.setShTime(checkVo.getShTime());
			banner.setShUserId(checkVo.getShUserId());
			banner.setShUserName(checkVo.getShUserName());
			String shState = checkVo.getShState();
			if(shState != null && !shState.equals("1")){
				banner.setNotPassText(checkVo.getNotPassText());
			}
			update(banner);
		}
		return errMsg;
	}

}
