package com.edgedo.check.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.queryvo.SysCheckQuery;
import com.edgedo.check.service.SysCheckService;
import com.edgedo.common.shiro.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "SysCheck")
@Controller
@RequestMapping("/admin/sysCheck")
public class SysCheckController extends BaseController{
	
	@Autowired
	private SysCheckService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询SysCheck", notes = "分页查询SysCheck")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute SysCheckQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy() == null){
			query.setOrderBy("CREATE_TIME DESC");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	
	/**
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载SysCheck", notes = "根据ID加载SysCheck")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 审核记录通过
	 * @author: ZhangCC
	 * @time: 2020/8/20 18:35
	 */
	@RequestMapping(value = "/verifyPassById",method = RequestMethod.POST)
	public ModelAndView verifyPassById(String id){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		SysCheck checkVo = service.loadById(id);
		if(checkVo == null){
			return buildErrorResponse(modelAndView,"未查询到审核记录");
		}
		checkVo.setShState("1");
		checkVo.setShTime(new Date());
		checkVo.setShUserId(user.getUserId());
		checkVo.setShUserName(user.getUserName());
		service.updateVoVerifyInfo(checkVo);
		return buildResponse(modelAndView);
	}

	/**
	 * 审核记录不通过
	 * @author: ZhangCC
	 * @time: 2020/8/20 18:35
	 */
	@RequestMapping(value = "/verifyFailById",method = RequestMethod.POST)
	public ModelAndView verifyFailById(String id,String failReason){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		SysCheck checkVo = service.loadById(id);
		if(checkVo == null){
			return buildErrorResponse(modelAndView,"未查询到审核记录");
		}
		checkVo.setShState("-1");
		checkVo.setShTime(new Date());
		checkVo.setShUserId(user.getUserId());
		checkVo.setShUserName(user.getUserName());
		checkVo.setNotPassText(failReason);
		service.updateVoVerifyInfo(checkVo);
		return buildResponse(modelAndView);
	}
	
	
}
