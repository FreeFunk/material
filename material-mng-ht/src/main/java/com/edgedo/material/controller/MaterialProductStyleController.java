package com.edgedo.material.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.service.SysCheckService;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.material.constant.VerifyOperateServiceConstant;
import com.edgedo.material.entity.MaterialProductIntroduction;
import com.edgedo.material.entity.MaterialProductStyle;
import com.edgedo.material.queryvo.MaterialProductIntroductionQuery;
import com.edgedo.material.queryvo.MaterialProductStyleQuery;
import com.edgedo.material.service.MaterialProducerRelationService;
import com.edgedo.material.service.MaterialProducerSysUserRelationService;
import com.edgedo.material.service.MaterialProductStyleService;
import com.edgedo.material.util.ImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "MaterialProductStyle")
@Controller
@RequestMapping("/materialProductStyle")
public class MaterialProductStyleController extends BaseController{
	
	@Autowired
	private MaterialProductStyleService service;

	@Value("${app.faceai.imgcompresswidth}")
	private Integer app_faceai_imgcompresswidth;

	@Value("${app.faceaicls.imgcompresswidth}")
	private Integer app_faceaicls_imgcompresswidth;
	@Value("${fileForder}")
	private String fileForder;
	@Autowired
	private SysCheckService sysCheckService;
	@Autowired
	private MaterialProducerSysUserRelationService materialProducerSysUserRelationService;

	@Autowired
	private MaterialProducerRelationService materialProducerRelationService;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialProductStyle", notes = "分页查询MaterialProductStyle")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialProductStyleQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if (query.getOrderBy()==null){
			query.setOrderBy("ORDER_NUM asc");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 厂商只能看自己的材料
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageProduct",method = RequestMethod.POST)
	public ModelAndView listpageProduct(@ModelAttribute MaterialProductStyleQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//查出当前登陆人的id
		User user = getLoginUser();
		//查出厂商id
		String producerId = materialProducerSysUserRelationService.selectBySysUserId(user.getUserId());
		//查出所有的材料id
		List<String> materialIdList = materialProducerRelationService.selectByProducerId(producerId);
		if (materialIdList.size()==0){
			buildResponse(modelAndView,query);
			return modelAndView;
		}else {
			query.setMaterIdList(materialIdList);
			service.listPage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialProductStyle", notes = "新增修改MaterialProductStyle")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialProductStyle voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			errMsg = service.insert(voObj);
		}else{
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除MaterialProductStyle", notes = "根据ID批量删除MaterialProductStyle")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/deleteByIds",method = RequestMethod.POST)
	public ModelAndView delete(String ids){
		ModelAndView modelAndView = new ModelAndView();
		String[] arr = ids.split(",");
		List<String> list = new ArrayList<String>();
		for(String str : arr){
			list.add(str);
		}
		service.deleteByIds(list);		
		return buildResponse(modelAndView);
	}
	
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载MaterialProductStyle", notes = "根据ID加载MaterialProductStyle")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * 材料管理员 新增
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/insertProductStyle",method = RequestMethod.POST)
	public ModelAndView insertProductStyle(MaterialProductStyle voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		User user = getLoginUser();
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setShState("1");
		voObj.setDataState("1");
		voObj.setIsEnable("1");
		errMsg = service.insertProductStyle(voObj);

		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}


	/**
	 * 厂商管理员 新增
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/insertProductStyleProductAdmin",method = RequestMethod.POST)
	public ModelAndView insertProductStyleProductAdmin(MaterialProductStyle voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		User user = getLoginUser();
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setShState("0");
		voObj.setDataState("1");
		voObj.setIsEnable("0");
		errMsg = service.insertProductStyleProductAdmin(voObj,user);

		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	/**
	 * 材料管理员 修改
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateNew",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateNew(MaterialProductStyle voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";

		if(voObj.getProductType().equals("1")){
			String fileUrl =voObj.getOrgImageUrl();
			if(fileUrl !=null && !fileUrl.equals("")){
				String filePath = "";
				String filePathSecond = "";
				File file = new File(fileUrl);
				try {
					//原图
					filePath = FileUtil.saveFile(file,fileForder,true);
					voObj.setOrgImageUrl(filePath);
					//压缩 ==> 展示图
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					voObj.setShowImageUrl(filePath);
					//二次 ==> 缩略图
					String faceOldSencond = FileUtil.getImageBase64Str(file);
					faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
					filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
					voObj.setMiniImg(filePathSecond);
					//宽高
					voObj.setImageHeight(FileUtil.getPhotoHeight(file));
					voObj.setImageWidth(FileUtil.getPhotoWidth(file));
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				voObj.setOrgImageUrl(null);
			}
		}else {
			String fileUrl =voObj.getFileUrl();
			if(fileUrl !=null && !fileUrl.equals("")){
				String filePath = "";
				File file = new File(fileUrl);
				try {
					filePath = FileUtil.saveFile(file,fileForder,true);
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				voObj.setFileType(voObj.getFileName().split("\\.")[1]);
				voObj.setFileUrl(filePath);
			}else {
				voObj.setFileUrl(null);
			}
		}
		errMsg = service.update(voObj);

		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	/**
	 * 厂商管理员 修改
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateNewProduct",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateNewProduct(MaterialProductStyle voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		User user = getLoginUser();
		if(voObj.getProductType().equals("1")){
			String fileUrl =voObj.getOrgImageUrl();
			if(fileUrl !=null && !fileUrl.equals("")){
				String filePath = "";
				String filePathSecond = "";
				File file = new File(fileUrl);
				try {
					//原图
					filePath = FileUtil.saveFile(file,fileForder,true);
					voObj.setOrgImageUrl(filePath);
					//压缩 ==> 展示图
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					voObj.setShowImageUrl(filePath);
					//二次 ==> 缩略图
					String faceOldSencond = FileUtil.getImageBase64Str(file);
					faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
					filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
					voObj.setMiniImg(filePathSecond);
					//宽高
					voObj.setImageHeight(FileUtil.getPhotoHeight(file));
					voObj.setImageWidth(FileUtil.getPhotoWidth(file));
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				voObj.setOrgImageUrl(null);
			}
		}else {
			String fileUrl =voObj.getFileUrl();
			if(fileUrl !=null && !fileUrl.equals("")){
				String filePath = "";
				File file = new File(fileUrl);
				try {
					filePath = FileUtil.saveFile(file,fileForder,true);
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				voObj.setFileType(voObj.getFileName().split("\\.")[1]);
				voObj.setFileUrl(filePath);
			}else {
				voObj.setFileUrl(null);
			}
		}
		voObj.setShState("0");
		errMsg = service.update(voObj);
		//审核表 重新审核
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceDes("材料产品样式表");
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_PRODUCT_STYLE_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_PRODUCT_STYLE_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

}
