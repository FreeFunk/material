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
import com.edgedo.material.entity.MaterialProductStyle;
import com.edgedo.material.mapper.MaterialMapper;
import com.edgedo.material.mapper.MaterialProductStyleMapper;
import com.edgedo.material.queryvo.MaterialProductStyleQuery;
import com.edgedo.material.queryvo.MaterialProductStyleView;
import com.edgedo.material.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProductStyleService implements CheckOperatorService {
	
	
	@Autowired
	private MaterialProductStyleMapper materialProductStyleMapper;
	@Value("${fileForder}")
	private String fileForder;
	@Autowired
	private SysCheckService sysCheckService;
	@Autowired
	private MaterialMapper materialMapper;
	@Value("${app.faceai.imgcompresswidth}")
	private Integer app_faceai_imgcompresswidth;

	@Value("${app.faceaicls.imgcompresswidth}")
	private Integer app_faceaicls_imgcompresswidth;

	public List<MaterialProductStyleView> listPage(MaterialProductStyleQuery materialProductStyleQuery){
		List list = materialProductStyleMapper.listPage(materialProductStyleQuery);
		materialProductStyleQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProductStyle materialProductStyle) {
		materialProductStyle.setId(Guid.guid());
		materialProductStyleMapper.insert(materialProductStyle);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProductStyle materialProductStyle) {
		materialProductStyleMapper.updateById(materialProductStyle);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProductStyle materialProductStyle) {
		materialProductStyleMapper.updateAllColumnById(materialProductStyle);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProductStyleMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialProductStyleMapper.updateBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProductStyle loadById(String id) {
		return materialProductStyleMapper.selectById(id);
	}


	/**
	 * 材料管理员 新增
	 * @param materialProductStyle
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertProductStyle(MaterialProductStyle materialProductStyle) {

		Material material = materialMapper.selectById(materialProductStyle.getMaterialId());
		materialProductStyle.setMaterialName(material.getMaterialName());
		if (materialProductStyle.getProductType().equals("1")){
			String[] imgArr = materialProductStyle.getOrgImageUrl().split(",");
			for(String imgStr : imgArr){
				if(imgStr !=null && !imgStr.equals("")){
					String filePath = "";
					String filePathSecond = "";
					File file = new File(imgStr);
					try {
						//原图
						filePath = FileUtil.saveFile(file,fileForder,true);
						materialProductStyle.setOrgImageUrl(filePath);
						//压缩 ==> 展示图
						String faceOld = FileUtil.getImageBase64Str(file);
						faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
						filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
						materialProductStyle.setShowImageUrl(filePath);
						//二次 ==> 缩略图
						String faceOldSencond = FileUtil.getImageBase64Str(file);
						faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
						filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
						materialProductStyle.setMiniImg(filePathSecond);
						//宽高
						materialProductStyle.setImageHeight(FileUtil.getPhotoHeight(file));
						materialProductStyle.setImageWidth(FileUtil.getPhotoWidth(file));
						//删除临时文件
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
					materialProductStyle.setOrderNum(new BigDecimal(materialProductStyleMapper.count(material.getId())+1));
					materialProductStyle.setId(Guid.guid());
					materialProductStyleMapper.insert(materialProductStyle);
				}
			}
		}else {
			String[] fileUrlStr = materialProductStyle.getFileUrl().split(",");
			String[] fileNameStr = materialProductStyle.getFileName().split(",");
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
					materialProductStyle.setFileUrl(filePath);

					materialProductStyle.setFileName(fileNameStr[i]);
					materialProductStyle.setFileType(fileNameStr[i].split("\\.")[1]);
					materialProductStyle.setId(Guid.guid());
					materialProductStyle.setOrderNum(new BigDecimal(materialProductStyleMapper.count(material.getId()) + 1));
					materialProductStyleMapper.insert(materialProductStyle);
				}
			}
		}
		return "";
	}


	/**
	 * 厂商管理员 新增
	 * @param materialProductStyle
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insertProductStyleProductAdmin(MaterialProductStyle materialProductStyle,User user) {

		Material material = materialMapper.selectById(materialProductStyle.getMaterialId());
		materialProductStyle.setMaterialName(material.getMaterialName());
		if (materialProductStyle.getProductType().equals("1")){
			String[] imgArr = materialProductStyle.getOrgImageUrl().split(",");
			for(String imgStr : imgArr){
				if(imgStr !=null && !imgStr.equals("")){
					String filePath = "";
					String filePathSecond = "";
					File file = new File(imgStr);
					try {
						//原图
						filePath = FileUtil.saveFile(file,fileForder,true);
						materialProductStyle.setOrgImageUrl(filePath);
						//压缩 ==> 展示图
						String faceOld = FileUtil.getImageBase64Str(file);
						faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
						filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
						materialProductStyle.setShowImageUrl(filePath);
						//二次 ==> 缩略图
						String faceOldSencond = FileUtil.getImageBase64Str(file);
						faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
						filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
						materialProductStyle.setMiniImg(filePathSecond);
						//宽高
						materialProductStyle.setImageHeight(FileUtil.getPhotoHeight(file));
						materialProductStyle.setImageWidth(FileUtil.getPhotoWidth(file));
						//删除临时文件
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
					materialProductStyle.setOrderNum(new BigDecimal(materialProductStyleMapper.count(material.getId())+1));
					materialProductStyle.setId(Guid.guid());
					materialProductStyleMapper.insert(materialProductStyle);
					insertSysCheck(user,materialProductStyle.getId());
				}
			}
		}else {
			String[] fileUrlStr = materialProductStyle.getFileUrl().split(",");
			String[] fileNameStr = materialProductStyle.getFileName().split(",");
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
					materialProductStyle.setFileUrl(filePath);

					materialProductStyle.setFileName(fileNameStr[i]);
					materialProductStyle.setFileType(fileNameStr[i].split("\\.")[1]);
					materialProductStyle.setId(Guid.guid());
					materialProductStyle.setOrderNum(new BigDecimal(materialProductStyleMapper.count(material.getId()) + 1));
					materialProductStyleMapper.insert(materialProductStyle);
					insertSysCheck(user,materialProductStyle.getId());
				}
			}
		}
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
		sysCheck.setServiceDes("材料产品样式表");
		//verifyOperateServiceConstant
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_PRODUCT_STYLE_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_PRODUCT_STYLE_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
	}


	@Override
	public String updateVoVerifyInfo(SysCheck checkVo) {
		String errMsg = "";
		String serviceId = checkVo.getServiceId();
		MaterialProductStyle productStyle = loadById(serviceId);
		if(productStyle == null){
			errMsg = "未查询到审核记录";
		}else{
			productStyle.setShState(checkVo.getShState());
			productStyle.setShTime(checkVo.getShTime());
			productStyle.setShUserId(checkVo.getShUserId());
			productStyle.setShUserName(checkVo.getShUserName());
			String shState = checkVo.getShState();
			if(shState != null && !shState.equals("1")){
				productStyle.setNotPassText(checkVo.getNotPassText());
			}
			update(productStyle);
		}
		return errMsg;
	}
}
