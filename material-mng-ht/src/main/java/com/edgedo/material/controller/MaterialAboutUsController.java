package com.edgedo.material.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.material.entity.MaterialAboutUs;
import com.edgedo.material.queryvo.MaterialAboutUsQuery;
import com.edgedo.material.queryvo.MaterialAboutUsView;
import com.edgedo.material.service.MaterialAboutUsService;
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


@Api(tags = "MaterialAboutUs")
@Controller
@RequestMapping("/material/materialAboutUs")
public class MaterialAboutUsController extends BaseController{
	
	@Autowired
	private MaterialAboutUsService service;

	@Value("${fileForder}")
	private String fileForder;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialAboutUs", notes = "分页查询MaterialAboutUs")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialAboutUsQuery query){
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
	@ApiOperation(value = "新增修改MaterialAboutUs", notes = "新增修改MaterialAboutUs")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialAboutUs voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String errMsg = "";

		//将临时文件转移
		String imageUrl= voObj.getFileUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setFileUrl(filePath);
		}else{
			voObj.setFileUrl(null);
		}

		String id = voObj.getId();
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
	@ApiOperation(value = "根据ID批量删除MaterialAboutUs", notes = "根据ID批量删除MaterialAboutUs")
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
	@ApiOperation(value = "根据ID加载MaterialAboutUs", notes = "根据ID加载MaterialAboutUs")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	@RequestMapping(value = "/loadInfoByUser",method = RequestMethod.POST)
	public ModelAndView loadInfoByUser(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String userId = user.getUserId();
		MaterialAboutUsView aboutUs = service.loadInfoByUser(userId);
		return buildResponse(modelAndView,aboutUs);
	}
	
	
}
