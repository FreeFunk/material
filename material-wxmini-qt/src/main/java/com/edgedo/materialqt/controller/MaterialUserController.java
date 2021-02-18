package com.edgedo.materialqt.controller;


import com.edgedo.common.base.BaseController;
import com.edgedo.materialqt.entity.MaterialUser;
import com.edgedo.materialqt.service.MaterialUserService;
import com.edgedo.sys.entity.SysWxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "MaterialUser")
@Controller
@RequestMapping("/materialUser")
public class MaterialUserController extends BaseController{
	
	@Autowired
	private MaterialUserService service;
	

	/**
	 * 新增修改
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialUser", notes = "新增修改MaterialUser")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(){
		ModelAndView modelAndView = new ModelAndView();
		SysWxUser sysWxUser = getLoginWxUser();
		return buildResponse(modelAndView,service.saveOrUpdate(sysWxUser));
	}


	/**
	 * 新增修改
	 * @return
	 */
	@RequestMapping(value = "/getLoginUser",method = RequestMethod.POST)
	public ModelAndView getLoginUser(){
		ModelAndView modelAndView = new ModelAndView();
		SysWxUser sysWxUser = getLoginWxUser();
		MaterialUser materialUser = service.loadByMiniOpenId(sysWxUser.getOpenId());
		return buildResponse(modelAndView,materialUser);
	}

	
}
