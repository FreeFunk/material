package com.edgedo.materialqt.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.materialqt.entity.MaterialSysMessage;
import com.edgedo.materialqt.entity.MaterialUser;
import com.edgedo.materialqt.queryvo.MaterialSysMessageQuery;
import com.edgedo.materialqt.service.MaterialSysMessageService;
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


@Api(tags = "MaterialSysMessage")
@Controller
@RequestMapping("/materialSysMessage")
public class MaterialSysMessageController extends BaseController{
	
	@Autowired
	private MaterialSysMessageService service;
	@Autowired
	private MaterialUserService materialUserService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialSysMessage", notes = "分页查询MaterialSysMessage")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialSysMessageQuery query){
		SysWxUser sysWxUser = getLoginWxUser();
		MaterialUser materialUser = materialUserService.loadByMiniOpenId(sysWxUser.getOpenId());
		ModelAndView modelAndView = new ModelAndView();
		query.getQueryObj().setDataState("1");
		service.listPage(query,materialUser.getId());
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialSysMessage", notes = "新增修改MaterialSysMessage")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialSysMessage voObj){
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
	@ApiOperation(value = "根据ID批量删除MaterialSysMessage", notes = "根据ID批量删除MaterialSysMessage")
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
	@ApiOperation(value = "根据ID加载MaterialSysMessage", notes = "根据ID加载MaterialSysMessage")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}



	/**
	 * @Param
	 * @Description: 查询用户未读公告的数量
	 * @return
	 * @Author: ZhaoSiDa
	 * @DateTime: 2020/8/31 19:05
	 */
	@RequestMapping(value = "/unReadMsgNum",method = RequestMethod.POST)
	public ModelAndView  unReadMsgNum(){
		ModelAndView modelAndView = new ModelAndView();
		SysWxUser sysWxUser = getLoginWxUser();
		MaterialUser materialUser = materialUserService.loadByMiniOpenId(sysWxUser.getOpenId());
		String userId = materialUser.getId();
		return buildResponse(modelAndView, service.unReadMsgNum(userId));
	}

}
