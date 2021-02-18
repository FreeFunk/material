package com.edgedo.material.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.material.entity.MaterialLabel;
import com.edgedo.material.queryvo.MaterialLabelQuery;
import com.edgedo.material.service.MaterialLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "MaterialLabel")
@Controller
@RequestMapping("/material/materialLabel")
public class MaterialLabelController extends BaseController{
	
	@Autowired
	private MaterialLabelService service;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialLabel", notes = "分页查询MaterialLabel")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialLabelQuery query){
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
	@ApiOperation(value = "新增修改MaterialLabel", notes = "新增修改MaterialLabel")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialLabel voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String errMsg = "";
		String id = voObj.getId();
		voObj.setIsEnable("1");
		BigDecimal orderNum = voObj.getOrderNum();
		if(orderNum == null){
			BigDecimal maxOrderNum = service.selectMaxOrderNumByType(voObj.getLabelType());
			if(maxOrderNum == null){
				maxOrderNum = new BigDecimal(0);
			}else{
				maxOrderNum = maxOrderNum.add(new BigDecimal(1));
			}
			voObj.setOrderNum(maxOrderNum);
		}
		if(id==null || id.trim().equals("")){
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
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
	@ApiOperation(value = "根据ID批量删除MaterialLabel", notes = "根据ID批量删除MaterialLabel")
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
	@ApiOperation(value = "根据ID加载MaterialLabel", notes = "根据ID加载MaterialLabel")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 更新标签的启用状态
	 * @author: ZhangCC
	 * @time: 2020/9/3 19:00
	 */
	@RequestMapping(value = "/switchEnableState",method = RequestMethod.POST)
	public ModelAndView switchEnableState(String id,String isEnable){
		ModelAndView modelAndView = new ModelAndView();
		service.updateEnableState(id,isEnable);
		return buildResponse(modelAndView);
	}
	
	
}
