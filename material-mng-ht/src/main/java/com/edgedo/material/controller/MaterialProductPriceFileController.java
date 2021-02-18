package com.edgedo.material.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.service.SysCheckService;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.material.constant.VerifyOperateServiceConstant;
import com.edgedo.material.entity.MaterialProductPrice;
import com.edgedo.material.entity.MaterialProductPriceFile;
import com.edgedo.material.queryvo.MaterialProductPriceFileQuery;
import com.edgedo.material.queryvo.MaterialProductPriceFileView;
import com.edgedo.material.service.MaterialProductPriceFileService;
import com.edgedo.material.service.MaterialProductPriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "MaterialProductPriceFile")
@Controller
@RequestMapping("/material/materialProductPriceFile")
public class MaterialProductPriceFileController extends BaseController{
	
	@Autowired
	private MaterialProductPriceFileService service;
	@Autowired
	private MaterialProductPriceService productPriceService;
	@Autowired
	private SysCheckService sysCheckService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialProductPriceFile", notes = "分页查询MaterialProductPriceFile")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialProductPriceFileQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy() == null){
			query.setOrderBy("ORDER_NUM asc");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialProductPriceFile", notes = "新增修改MaterialProductPriceFile")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialProductPriceFile voObj){
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

	@RequestMapping(value = "/updateProductNew",method = RequestMethod.POST)
	public ModelAndView updateProductNew(MaterialProductPriceFile voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		voObj.setShState("0");
		String errMsg = service.update(voObj);
		//审核表 重新审核
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceDes("报价文件审核");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_INTRO_FILE_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_INTRO_FILE_PAGE);
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
	@ApiOperation(value = "根据ID批量删除MaterialProductPriceFile", notes = "根据ID批量删除MaterialProductPriceFile")
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
	@ApiOperation(value = "根据ID加载MaterialProductPriceFile", notes = "根据ID加载MaterialProductPriceFile")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}


	/**
	 * 从产品报价进入上传的文件
	 * @param materialProductPriceFile
	 * @return
	 */
	@RequestMapping(value = "/insertNew",method = RequestMethod.POST)
	public ModelAndView  insertNew(MaterialProductPriceFile materialProductPriceFile){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		materialProductPriceFile.setCreateUserId(user.getUserId());
		materialProductPriceFile.setCreateTime(new Date());
		materialProductPriceFile.setDataState("1");
		service.insertNew(materialProductPriceFile);
		return buildResponse(modelAndView);
	}



	/**
	 * 从产品报价进入上传的文件
	 * @param materialProductPriceFile
	 * @return
	 */
	@RequestMapping(value = "/insertProductNew",method = RequestMethod.POST)
	public ModelAndView  insertProductNew(MaterialProductPriceFile materialProductPriceFile){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		materialProductPriceFile.setCreateUserId(user.getUserId());
		materialProductPriceFile.setCreateTime(new Date());
		materialProductPriceFile.setDataState("1");
		service.insertProductNew(materialProductPriceFile,user);
		return buildResponse(modelAndView);
	}
	/**
	 * 根据产品报价查询关联文件记录
	 * @author: ZhangCC
	 * @time: 2020/9/1 18:38
	 */
	@RequestMapping(value = "/listByPriceId",method = RequestMethod.POST)
	public ModelAndView listByPriceId(String priceId){
		ModelAndView modelAndView = new ModelAndView();
		MaterialProductPrice productPrice = productPriceService.loadById(priceId);
		if(productPrice == null){
			return  buildErrorResponse(modelAndView,"未查询到产品报价记录");
		}
		List<MaterialProductPriceFileView> priceFileList = service.listByPriceId(priceId);
		return buildResponse(modelAndView,priceFileList);
	}

}
