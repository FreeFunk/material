package com.edgedo.material.service;
		
import java.io.File;
import java.io.IOException;
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
import com.edgedo.material.entity.MaterialProductPriceFile;
import com.edgedo.material.mapper.MaterialProductPriceFileMapper;
import com.edgedo.material.queryvo.MaterialProductPriceFileQuery;
import com.edgedo.material.queryvo.MaterialProductPriceFileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialProductPriceFileService implements CheckOperatorService{
	
	
	@Autowired
	private MaterialProductPriceFileMapper materialProductPriceFileMapper;
	@Value("${fileForder}")
	private String fileForder;
	@Autowired
	private SysCheckService sysCheckService;

	public List<MaterialProductPriceFileView> listPage(MaterialProductPriceFileQuery materialProductPriceFileQuery){
		List list = materialProductPriceFileMapper.listPage(materialProductPriceFileQuery);
		materialProductPriceFileQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialProductPriceFile materialProductPriceFile) {
		materialProductPriceFile.setId(Guid.guid());
		materialProductPriceFileMapper.insert(materialProductPriceFile);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialProductPriceFile materialProductPriceFile) {
		materialProductPriceFileMapper.updateById(materialProductPriceFile);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialProductPriceFile materialProductPriceFile) {
		materialProductPriceFileMapper.updateAllColumnById(materialProductPriceFile);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialProductPriceFileMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialProductPriceFileMapper.updateByDataState(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialProductPriceFile loadById(String id) {
		return materialProductPriceFileMapper.selectById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertNew(MaterialProductPriceFile materialProductPriceFile) {
		String[] fileUrlStr = materialProductPriceFile.getFileUrl().split(",");
		String[] fileNameStr = materialProductPriceFile.getFileName().split(",");
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
				materialProductPriceFile.setFileUrl(filePath);

				materialProductPriceFile.setFileName(fileNameStr[i]);
				materialProductPriceFile.setFileType(fileNameStr[i].split("\\.")[1]);
				materialProductPriceFile.setId(Guid.guid());
				materialProductPriceFile.setShState("1");
				materialProductPriceFile.setOrderNum(new BigDecimal(materialProductPriceFileMapper.selectCountNum(materialProductPriceFile.getProductPriceId()) + 1));
				materialProductPriceFileMapper.insert(materialProductPriceFile);
			}
		}
	}


	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertProductNew(MaterialProductPriceFile materialProductPriceFile, User user) {
		String[] fileUrlStr = materialProductPriceFile.getFileUrl().split(",");
		String[] fileNameStr = materialProductPriceFile.getFileName().split(",");
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
				materialProductPriceFile.setFileUrl(filePath);

				materialProductPriceFile.setFileName(fileNameStr[i]);
				materialProductPriceFile.setFileType(fileNameStr[i].split("\\.")[1]);
				materialProductPriceFile.setId(Guid.guid());
				materialProductPriceFile.setShState("0");
				materialProductPriceFile.setOrderNum(new BigDecimal(materialProductPriceFileMapper.selectCountNum(materialProductPriceFile.getProductPriceId()) + 1));
				materialProductPriceFileMapper.insert(materialProductPriceFile);
				//审核表 重新审核
				SysCheck sysCheck = new SysCheck();
				sysCheck.setCreateTime(new Date());
				sysCheck.setCreateUserId(user.getUserId());
				sysCheck.setCreateUserName(user.getUserName());
				sysCheck.setDataState("1");
				sysCheck.setServiceDes("报价文件审核");
				sysCheck.setServiceId(materialProductPriceFile.getId());
				sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_INTRO_FILE_SERVICE);
				sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_INTRO_FILE_PAGE);
				sysCheck.setShState("0");
				sysCheckService.insert(sysCheck);
			}
		}
	}

	/**
	 * 根据产品报价查询
	 * @author: ZhangCC
	 * @time: 2020/9/1 18:42
	 */
	public List<MaterialProductPriceFileView> listByPriceId(String priceId){
		return materialProductPriceFileMapper.listByPriceId(priceId);
	}

	@Override
	public String updateVoVerifyInfo(SysCheck checkVo) {
		String errMsg = "";
		String serviceId = checkVo.getServiceId();
		MaterialProductPriceFile productPriceFile = loadById(serviceId);
		if(productPriceFile == null){
			errMsg = "未查询到审核记录";
		}else{
			productPriceFile.setShState(checkVo.getShState());
			productPriceFile.setShTime(checkVo.getShTime());
			productPriceFile.setShUserId(checkVo.getShUserId());
			productPriceFile.setShUserName(checkVo.getShUserName());
			String shState = checkVo.getShState();
			if(shState != null && !shState.equals("1")){
				productPriceFile.setNotPassText(checkVo.getNotPassText());
			}
			update(productPriceFile);
		}
		return errMsg;
	}
}
