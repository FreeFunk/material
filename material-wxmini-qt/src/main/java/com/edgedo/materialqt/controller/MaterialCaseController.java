package com.edgedo.materialqt.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.materialqt.entity.MaterialCase;
import com.edgedo.materialqt.entity.MaterialUser;
import com.edgedo.materialqt.queryvo.MaterialCaseQuery;
import com.edgedo.materialqt.service.MaterialCaseService;
import com.edgedo.materialqt.service.MaterialUserService;
import com.edgedo.sys.entity.SysWxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "MaterialCase")
@Controller
@RequestMapping("/materialCase")
public class MaterialCaseController extends BaseController{
	
	@Autowired
	private MaterialCaseService service;
	@Autowired
	private MaterialUserService materialUserService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialCase", notes = "分页查询MaterialCase")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialCaseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		SysWxUser sysWxUser = getLoginWxUser();
		MaterialUser materialUser = materialUserService.loadByMiniOpenId(sysWxUser.getOpenId());
		String userId = materialUser.getId();
		query.getQueryObj().setIsEnable("1");
		query.getQueryObj().setShState("1");
		query.getQueryObj().setDataState("1");
		if(query.getOrderBy() == null || "".equals(query.getOrderBy())){
			query.setOrderBy("ORDER_NUM ASC");
		}
		service.listPage(query,userId);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 发现页分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialCase", notes = "分页查询MaterialCase")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/findListPage",method = RequestMethod.POST)
	public ModelAndView findListPage(@ModelAttribute MaterialCaseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		SysWxUser sysWxUser = getLoginWxUser();
		MaterialUser materialUser = materialUserService.loadByMiniOpenId(sysWxUser.getOpenId());
		String userId = materialUser.getId();
		query.getQueryObj().setIsEnable("1");
		query.getQueryObj().setShState("1");
		query.getQueryObj().setDataState("1");
		if(query.getOrderBy() == null || "".equals(query.getOrderBy())){
			query.setOrderBy("IS_TOP DESC , FIND_ORDER_NUM ASC");
		}
		service.listPage(query,userId);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialCase", notes = "新增修改MaterialCase")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialCase voObj){
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
	@ApiOperation(value = "根据ID批量删除MaterialCase", notes = "根据ID批量删除MaterialCase")
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
	@ApiOperation(value = "根据ID加载MaterialCase", notes = "根据ID加载MaterialCase")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		SysWxUser sysWxUser = getLoginWxUser();
		MaterialUser materialUser = materialUserService.loadByMiniOpenId(sysWxUser.getOpenId());
		String userId = materialUser.getId();
		return buildResponse(modelAndView, service.loadById(id,userId));
	}

	/**
	 * 根据主键加载
	 * @param caseLable
	 * @return
	 */
	@RequestMapping(value = "/listLable",method = RequestMethod.POST)
	public ModelAndView  listLable(String caseLable){
		ModelAndView modelAndView = new ModelAndView();
		List<String> listLable = service.listLable(caseLable);
		return buildResponse(modelAndView, listLable);
	}
}
