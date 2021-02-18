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
import com.edgedo.material.entity.MaterialProductPrice;
import com.edgedo.material.queryvo.MaterialProductPriceQuery;
import com.edgedo.material.queryvo.MaterialProductPriceView;
import com.edgedo.material.service.MaterialProducerRelationService;
import com.edgedo.material.service.MaterialProducerSysUserRelationService;
import com.edgedo.material.service.MaterialProductPriceService;
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


@Api(tags = "MaterialProductPrice")
@Controller
@RequestMapping("/material/materialProductPrice")
public class MaterialProductPriceController extends BaseController{
	
	@Autowired
	private MaterialProductPriceService service;
	@Value("${fileForder}")
	private String fileForder;
	@Autowired
	private MaterialProducerSysUserRelationService materialProducerSysUserRelationService;

	@Autowired
	private MaterialProducerRelationService materialProducerRelationService;
	@Autowired
	private SysCheckService sysCheckService;

	@Value("${app.faceai.maxfacelength}")
	private Integer app_faceai_maxfacelength;
	@Value("${app.faceai.imgcompresswidth}")
	private Integer app_faceai_imgcompresswidth;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialProductPrice", notes = "分页查询MaterialProductPrice")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialProductPriceQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if (query.getOrderBy() == null){
			query.setOrderBy("ORDER_NUM asc");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 查询厂商的产品报价
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageProdocue",method = RequestMethod.POST)
	public ModelAndView listpageProdocue(@ModelAttribute MaterialProductPriceQuery query){
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
	@ApiOperation(value = "新增修改MaterialProductPrice", notes = "新增修改MaterialProductPrice")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialProductPriceView voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				filePath = FileUtil.saveFile(file,fileForder,true);
				voObj.setImageUrl(filePath);
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				voObj.setCaseMiniImg(filePath);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else {
			voObj.setImageUrl(null);
			voObj.setCaseMiniImg(null);
		}

		User user = getLoginUser();
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setIsEnable("1");
			voObj.setShState("1");
			voObj.setDataState("1");
			errMsg = service.insertView(voObj);
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



	@RequestMapping(value = "/saveOrUpdateNew",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateNew(MaterialProductPriceView voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				filePath = FileUtil.saveFile(file,fileForder,true);
				voObj.setImageUrl(filePath);
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				voObj.setCaseMiniImg(filePath);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else {
			voObj.setImageUrl(null);
			voObj.setCaseMiniImg(null);
		}

		User user = getLoginUser();
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setIsEnable("1");
			voObj.setShState("1");
			voObj.setDataState("1");
			errMsg = service.insertView(voObj);
		}else{
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView,voObj.getId());
		}
		return modelAndView;
	}

//	insertProductPrice
	@RequestMapping(value = "/insertProductPrice",method = RequestMethod.POST)
	public ModelAndView insertProductPrice(MaterialProductPriceView voObj){
		ModelAndView modelAndView = new ModelAndView();
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				filePath = FileUtil.saveFile(file,fileForder,true);
				voObj.setImageUrl(filePath);
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				voObj.setCaseMiniImg(filePath);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else {
			voObj.setImageUrl(null);
			voObj.setCaseMiniImg(null);
		}

		User user = getLoginUser();
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setIsEnable("0");
		voObj.setShState("0");
		voObj.setDataState("1");
		service.insertView(voObj);
		//审核表
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceDes("产品报价审核");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_PRICE_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_PRICE_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/updateProductPrice",method = RequestMethod.POST)
	public ModelAndView updateProductPrice(MaterialProductPriceView voObj){
		ModelAndView modelAndView = new ModelAndView();
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				filePath = FileUtil.saveFile(file,fileForder,true);
				voObj.setImageUrl(filePath);
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				voObj.setCaseMiniImg(filePath);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else {
			voObj.setImageUrl(null);
			voObj.setCaseMiniImg(null);
		}

		User user = getLoginUser();
		voObj.setShState("0");
		service.update(voObj);
		//审核表
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceDes("产品报价审核");
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_PRICE_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_PRICE_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
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
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除MaterialProductPrice", notes = "根据ID批量删除MaterialProductPrice")
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
	@ApiOperation(value = "根据ID加载MaterialProductPrice", notes = "根据ID加载MaterialProductPrice")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 报价审核列表
	 * @return
	 */
	@RequestMapping(value = "/verifyListPage",method = RequestMethod.POST)
	public ModelAndView verifyListPage(MaterialProductPriceQuery query){
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
	 */
	@RequestMapping(value = "/productPriceVerifyPass",method = RequestMethod.POST)
	public ModelAndView productPriceVerifyPass(String id){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialProductPrice productPrice = service.loadById(id);
		if(productPrice == null){
			return buildErrorResponse(modelAndView,"未查询到产品报价信息");
		}
		productPrice.setShUserId(user.getUserId());
		productPrice.setShUserName(user.getUserName());
		productPrice.setShTime(new Date());
		productPrice.setShState("1");
		service.update(productPrice);
		return buildResponse(modelAndView);
	}

	/**
	 * 审核失败
	 * @return
	 */
	@RequestMapping(value = "/productPriceVerifyFail",method = RequestMethod.POST)
	public ModelAndView productPriceVerifyFail(String id,String failReason){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialProductPrice productPrice = service.loadById(id);
		if(productPrice == null){
			return buildErrorResponse(modelAndView,"未查询到产品报价信息");
		}
		productPrice.setShTime(new Date());
		productPrice.setShUserId(user.getUserId());
		productPrice.setShUserName(user.getUserName());
		productPrice.setShState("-1");
		productPrice.setNotPassText(failReason);
		service.update(productPrice);
		return buildResponse(modelAndView);
	}
	
	
}
