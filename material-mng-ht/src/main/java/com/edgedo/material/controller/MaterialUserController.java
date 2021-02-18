package com.edgedo.material.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.material.entity.MaterialUser;
import com.edgedo.material.queryvo.MaterialUserQuery;
import com.edgedo.material.service.MaterialUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.security.provider.MD5;


@Api(tags = "MaterialUser")
@Controller
@RequestMapping("/zhyg/material/materialUser")
public class MaterialUserController extends BaseController{
	
	@Autowired
	private MaterialUserService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialUser", notes = "分页查询MaterialUser")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialUserQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialUser", notes = "新增修改MaterialUser")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialUser voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		if(id==null || id.trim().equals("")){
			errMsg = service.insertMaterialUser(voObj);
		}else{
			errMsg = service.updateMaterialUser(voObj);
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
	@ApiOperation(value = "根据ID批量删除MaterialUser", notes = "根据ID批量删除MaterialUser")
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
	@ApiOperation(value = "根据ID加载MaterialUser", notes = "根据ID加载MaterialUser")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 重置用户密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resetUserPwd",method = RequestMethod.POST)
	public ModelAndView resetUserPwd(String id){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = service.resetUserPwd(id);
		return buildResponse(modelAndView,errMsg);
	}

	/**
	 * 导出学员
	 * @return
	 */
	@RequestMapping(value = "/selectUserListForExport",method = RequestMethod.POST)
	public ModelAndView selectUserListForExport(MaterialUserQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listByObj(query);
		List<MaterialUser> userList = query.getList();
		for(int i=0;i<userList.size();i++){
			String userType = userList.get(i).getUserType();
			if("1".equals(userType)){
				userList.get(i).setUserType("员工");
			}else{
				userList.get(i).setUserType("普通用户");
			}
			String isPower = userList.get(i).getIsPower();
			if("1".equals(isPower)){
				userList.get(i).setIsPower("已授权");
			}else{
				userList.get(i).setIsPower("未授权");
			}
		}
		return buildResponse(modelAndView,query);
	}
	
	
}
