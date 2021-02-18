package com.edgedo.material.controller;


import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.service.SysCheckService;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import com.edgedo.material.constant.VerifyOperateServiceConstant;
import com.edgedo.material.entity.MaterialProductIntroduction;
import com.edgedo.material.queryvo.MaterialProductIntroductionQuery;
import com.edgedo.material.service.MaterialProducerRelationService;
import com.edgedo.material.service.MaterialProducerSysUserRelationService;
import com.edgedo.material.service.MaterialProductIntroductionService;
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


@Api(tags = "MaterialProductIntroduction")
@Controller
@RequestMapping("/material/materialProductIntroduction")
public class MaterialProductIntroductionController extends BaseController{
	
	@Autowired
	private MaterialProductIntroductionService service;
	@Value("${fileForder}")
	private String fileForder;

	@Autowired
	private MaterialProducerSysUserRelationService materialProducerSysUserRelationService;

	@Autowired
	private MaterialProducerRelationService materialProducerRelationService;

	@Autowired
	private SysCheckService sysCheckService;
	@Value("${app.faceai.imgcompresswidth}")
	private Integer app_faceai_imgcompresswidth;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialProductIntroduction", notes = "分页查询MaterialProductIntroduction")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialProductIntroductionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if (query.getOrderBy() == null){
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
	public ModelAndView listpageProduct(@ModelAttribute MaterialProductIntroductionQuery query){
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
	 * 切换启用状态
	 * @param id isEnable
	 * @return
	 */
	@RequestMapping(value = "/switchEnableState",method = RequestMethod.POST)
	public ModelAndView  switchEnableState(String id, String isEnable){
		ModelAndView modelAndView = new ModelAndView();
		service.updateEnableState(id,isEnable);
		return buildResponse(modelAndView);
	}
	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialProductIntroduction", notes = "新增修改MaterialProductIntroduction")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialProductIntroduction voObj){
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


	@RequestMapping(value = "/insertProductIntroduction",method = RequestMethod.POST)
	public ModelAndView insertProductIntroduction(MaterialProductIntroduction voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		User user = getLoginUser();
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setShState("1");
		voObj.setDataState("1");
		voObj.setIsEnable("1");
		errMsg = service.insertProductIntroduction(voObj);

		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/insertProductIntroductionProductAdmin",method = RequestMethod.POST)
	public ModelAndView insertProductIntroductionProductAdmin(MaterialProductIntroduction voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		User user = getLoginUser();
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setShState("0");
		voObj.setDataState("1");
		voObj.setIsEnable("0");
		errMsg = service.insertProductIntroductionProductAdmin(voObj,user);
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/saveOrUpdateNew",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateNew(MaterialProductIntroduction voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String fileUrl =voObj.getFileUrl();
		if(voObj.getProductType().equals("1")){
			if(fileUrl !=null && !fileUrl.equals("")){
				String filePath = "";
				File file = new File(fileUrl);
				try {
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				String[] fileNameArr = filePath.split("\\.");
				voObj.setFileType(fileNameArr[fileNameArr.length-1]);
				voObj.setFileUrl(filePath);
			}else {
				voObj.setFileUrl(null);
			}
		}else {
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


	@RequestMapping(value = "/saveOrUpdateNewProduct",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateNewProduct(MaterialProductIntroduction voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		User user = getLoginUser();
		String fileUrl =voObj.getFileUrl();
		if(voObj.getProductType().equals("1")){
			if(fileUrl !=null && !fileUrl.equals("")){
				String filePath = "";
				File file = new File(fileUrl);
				try {
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				String[] fileNameArr = filePath.split("\\.");
				voObj.setFileType(fileNameArr[fileNameArr.length-1]);
				voObj.setFileUrl(filePath);
			}else {
				voObj.setFileUrl(null);
			}
		}else {
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
		//审核表
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceDes("产品介绍审核");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_INTRO_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_INTRO_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
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
	@ApiOperation(value = "根据ID批量删除MaterialProductIntroduction", notes = "根据ID批量删除MaterialProductIntroduction")
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
	@ApiOperation(value = "根据ID加载MaterialProductIntroduction", notes = "根据ID加载MaterialProductIntroduction")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	@RequestMapping(value = "/verifyListPage",method = RequestMethod.POST)
	public ModelAndView verifyListPage(MaterialProductIntroductionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy() == null){
			query.setOrderBy("CREATE_TIME DESC");
		}
		query.getQueryObj().setShState("0");
		service.listPage(query);
		return buildResponse(modelAndView,query);
	}

	/**
	 * 审核通过
	 * @return
	 */
	@RequestMapping(value = "/productVerifyPass",method = RequestMethod.POST)
	public ModelAndView productVerifyPass(String id){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialProductIntroduction product = service.loadById(id);
		if(product == null){
			return buildErrorResponse(modelAndView,"未查询到记录");
		}
		product.setShTime(new Date());
		product.setShUserId(user.getUserId());
		product.setShUserName(user.getUserName());
		product.setShState("1");
		service.update(product);
		return buildResponse(modelAndView);
	}

	/**
	 * 审核失败
	 * @return
	 */
	@RequestMapping(value = "/productVerifyFail",method = RequestMethod.POST)
	public ModelAndView productVerifyFail(String id,String failReason){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialProductIntroduction product = service.loadById(id);
		if(product == null){
			return buildErrorResponse(modelAndView,"未查询到记录");
		}
		product.setShTime(new Date());
		product.setShUserId(user.getUserId());
		product.setShUserName(user.getUserName());
		product.setShState("-1");
		product.setNotPassText(failReason);
		service.update(product);
		return buildResponse(modelAndView);
	}
	
	
}
