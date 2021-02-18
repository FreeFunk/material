package com.edgedo.material.service;
		
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.edgedo.check.checkService.CheckOperatorService;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.service.SysCheckService;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.material.constant.VerifyOperateServiceConstant;
import com.edgedo.material.entity.Material;
import com.edgedo.material.entity.MaterialProductIntroduction;
import com.edgedo.material.mapper.MaterialMapper;
import com.edgedo.material.mapper.MaterialProductIntroductionMapper;
import com.edgedo.material.queryvo.MaterialProductIntroductionQuery;
import com.edgedo.material.queryvo.MaterialProductIntroductionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProductIntroductionService implements CheckOperatorService {
	
	
	@Autowired
	private MaterialProductIntroductionMapper materialProductIntroductionMapper;
	@Value("${fileForder}")
	private String fileForder;
	@Autowired
	private SysCheckService sysCheckService;
	@Autowired
	private MaterialMapper materialMapper;
	@Value("${app.faceai.imgcompresswidth}")
	private Integer app_faceai_imgcompresswidth;
	public List<MaterialProductIntroductionView> listPage(MaterialProductIntroductionQuery materialProductIntroductionQuery){
		List list = materialProductIntroductionMapper.listPage(materialProductIntroductionQuery);
		materialProductIntroductionQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProductIntroduction materialProductIntroduction) {
		materialProductIntroduction.setId(Guid.guid());
		materialProductIntroductionMapper.insert(materialProductIntroduction);
		return "";
	}


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertProductIntroduction(MaterialProductIntroduction materialProductIntroduction) {

		Material material = materialMapper.selectById(materialProductIntroduction.getMaterialId());
		materialProductIntroduction.setMaterialName(material.getMaterialName());
		if (materialProductIntroduction.getProductType().equals("1")){
			String[] imgArr = materialProductIntroduction.getFileUrl().split(",");
			for(String imgStr : imgArr){
				if(imgStr !=null && !imgStr.equals("")){
					String filePath = "";
					File file = new File(imgStr);
					try {
						//压缩
//						String faceOld = FileUtil.getImageBase64Str(file);
//						faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
//						filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
						filePath = FileUtil.saveFile(file,fileForder,true);
						//删除临时文件
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
					materialProductIntroduction.setFileUrl(filePath);
					String[] fileNameArr = filePath.split("\\.");
					materialProductIntroduction.setFileType(fileNameArr[fileNameArr.length-1]);
					materialProductIntroduction.setOrderNum(new BigDecimal(materialProductIntroductionMapper.count(material.getId())+1));
					materialProductIntroduction.setId(Guid.guid());
					materialProductIntroductionMapper.insert(materialProductIntroduction);
				}
			}
		}else {
			String[] fileUrlStr = materialProductIntroduction.getFileUrl().split(",");
			String[] fileNameStr = materialProductIntroduction.getFileName().split(",");
			for(int i = 0;i<fileUrlStr.length;i++){
				String fileUrl = fileUrlStr[i];
				if (fileUrl != null && !fileUrl.equals("")) {
					String filePath = "";
					File file = new File(fileUrl);
					try {
						filePath = FileUtil.saveFile(file, fileForder, true);
						//删除临时文件
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
					materialProductIntroduction.setFileUrl(filePath);

					materialProductIntroduction.setFileName(fileNameStr[i]);
					materialProductIntroduction.setFileType(fileNameStr[i].split("\\.")[1]);
					materialProductIntroduction.setId(Guid.guid());
					materialProductIntroduction.setOrderNum(new BigDecimal(materialProductIntroductionMapper.count(material.getId()) + 1));
					materialProductIntroductionMapper.insert(materialProductIntroduction);
				}
			}
		}
		return "";
	}


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertProductIntroductionProductAdmin(MaterialProductIntroduction materialProductIntroduction,User user) {

		Material material = materialMapper.selectById(materialProductIntroduction.getMaterialId());
		materialProductIntroduction.setMaterialName(material.getMaterialName());
		if (materialProductIntroduction.getProductType().equals("1")){
			String[] imgArr = materialProductIntroduction.getFileUrl().split(",");
			for(String imgStr : imgArr){
				if(imgStr !=null && !imgStr.equals("")){
					String filePath = "";
					File file = new File(imgStr);
					try {
						//压缩
//						String faceOld = FileUtil.getImageBase64Str(file);
//						faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
//						filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
						filePath = FileUtil.saveFile(file,fileForder,true);
						//删除临时文件
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
					materialProductIntroduction.setFileUrl(filePath);
					String[] fileNameArr = filePath.split("\\.");
					materialProductIntroduction.setFileType(fileNameArr[fileNameArr.length-1]);
					materialProductIntroduction.setOrderNum(new BigDecimal(materialProductIntroductionMapper.count(material.getId())+1));
					materialProductIntroduction.setId(Guid.guid());
					materialProductIntroductionMapper.insert(materialProductIntroduction);
					insertSysCheck(user,materialProductIntroduction.getId());
				}
			}
		}else {
			String[] fileUrlStr = materialProductIntroduction.getFileUrl().split(",");
			String[] fileNameStr = materialProductIntroduction.getFileName().split(",");
			for(int i = 0;i<fileUrlStr.length;i++){
				String fileUrl = fileUrlStr[i];
				if (fileUrl != null && !fileUrl.equals("")) {
					String filePath = "";
					File file = new File(fileUrl);
					try {
						filePath = FileUtil.saveFile(file, fileForder, true);
						//删除临时文件
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
					materialProductIntroduction.setFileUrl(filePath);

					materialProductIntroduction.setFileName(fileNameStr[i]);
					materialProductIntroduction.setFileType(fileNameStr[i].split("\\.")[1]);
					materialProductIntroduction.setId(Guid.guid());
					materialProductIntroduction.setOrderNum(new BigDecimal(materialProductIntroductionMapper.count(material.getId()) + 1));
					materialProductIntroductionMapper.insert(materialProductIntroduction);
					insertSysCheck(user,materialProductIntroduction.getId());
				}
			}
		}
		return "";
	}


	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProductIntroduction materialProductIntroduction) {
		materialProductIntroductionMapper.updateById(materialProductIntroduction);
		return "";
	}

	public void insertSysCheck(User user, String introductId){
		//审核表 重新审核
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceId(introductId);
		sysCheck.setServiceDes("产品介绍审核");
		//verifyOperateServiceConstant
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_INTRO_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_INTRO_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProductIntroduction materialProductIntroduction) {
		materialProductIntroductionMapper.updateAllColumnById(materialProductIntroduction);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProductIntroductionMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialProductIntroductionMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProductIntroduction loadById(String id) {
		return materialProductIntroductionMapper.selectById(id);
	}


	public void updateEnableState(String id, String isEnable) {
		materialProductIntroductionMapper.updateEnableState( id,  isEnable);
	}

	@Override
	public String updateVoVerifyInfo(SysCheck checkVo) {
		String errMsg = "";
		String serviceId = checkVo.getServiceId();
		MaterialProductIntroduction introduction = loadById(serviceId);
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
}
