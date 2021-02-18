package com.edgedo.material.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.material.entity.MaterialProducerSysUserRelation;
import com.edgedo.material.queryvo.MaterialProducerSysUserRelationQuery;
import com.edgedo.material.queryvo.MaterialProducerSysUserRelationView;
import com.edgedo.material.service.MaterialProducerSysUserRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "MaterialProducerSysUserRelation")
@Controller
@RequestMapping("/zhyg/material/materialProducerSysUserRelation")
public class MaterialProducerSysUserRelationController extends BaseController{
	
	@Autowired
	private MaterialProducerSysUserRelationService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialProducerSysUserRelation", notes = "分页查询MaterialProducerSysUserRelation")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialProducerSysUserRelationQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy() == null){
			query.setOrderBy("CREATE_TIME DESC");
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
	@ApiOperation(value = "新增修改MaterialProducerSysUserRelation", notes = "新增修改MaterialProducerSysUserRelation")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialProducerSysUserRelation voObj){
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
	@ApiOperation(value = "根据ID批量删除MaterialProducerSysUserRelation", notes = "根据ID批量删除MaterialProducerSysUserRelation")
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
	@ApiOperation(value = "根据ID加载MaterialProducerSysUserRelation", notes = "根据ID加载MaterialProducerSysUserRelation")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resetPwdById",method = RequestMethod.POST)
	public ModelAndView resetPwdById(String id){
		ModelAndView modelAndView = new ModelAndView();
		service.resetPwdById(id);
		return buildResponse(modelAndView);
	}

	/**
	 * 修改账号
	 * @param id
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "/updateProducerUserCode",method = RequestMethod.POST)
	public ModelAndView updateProducerUserCode(String id,String userCode){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = service.updateUserCode(id,userCode);
		if(errMsg.equals("")){
			return buildResponse(modelAndView);
		}else{
			return buildErrorResponse(modelAndView,errMsg);
		}
	}

	/**
	 * 添加新的厂商账号
	 * @return
	 */
	@RequestMapping(value = "/insertProducerUserCode",method = RequestMethod.POST)
	public ModelAndView insertProducerUserCode(MaterialProducerSysUserRelationView userRelationView){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = service.insertProducerUser(userRelationView);
		if(errMsg.equals("")){
			return buildResponse(modelAndView);
		}else{
			return buildErrorResponse(modelAndView,errMsg);
		}
	}

	/**
	 * 删除厂商账号
	 * @return
	 */
	@RequestMapping(value = "/deleteProducerUserCode",method = RequestMethod.POST)
	public ModelAndView deleteProducerUserCode(String id){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = service.deleteProducerUser(id);
		if(errMsg.equals("")){
			return buildResponse(modelAndView);
		}else{
			return buildErrorResponse(modelAndView,errMsg);
		}
	}
	
	
}
