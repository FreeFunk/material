package com.edgedo.materialqt.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.materialqt.entity.MaterialUser;
import com.edgedo.materialqt.entity.MaterialUserFootmark;
import com.edgedo.materialqt.queryvo.MaterialUserFootmarkQuery;
import com.edgedo.materialqt.service.MaterialUserFootmarkService;
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


@Api(tags = "MaterialUserFootmark")
@Controller
@RequestMapping("/materialUserFootmark")
public class MaterialUserFootmarkController extends BaseController{
	
	@Autowired
	private MaterialUserFootmarkService service;
	@Autowired
	private MaterialUserService materialUserService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialUserFootmark", notes = "分页查询MaterialUserFootmark")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialUserFootmarkQuery query){
		ModelAndView modelAndView = new ModelAndView();
		SysWxUser sysWxUser = getLoginWxUser();
		MaterialUser materialUser = materialUserService.loadByMiniOpenId(sysWxUser.getOpenId());
		query.getQueryObj().setCreateUserId(materialUser.getId());
		query.setOrderBy("CREATE_TIME DESC");
		service.listPage(query,materialUser.getId());
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialUserFootmark", notes = "新增修改MaterialUserFootmark")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialUserFootmark voObj){
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
	@ApiOperation(value = "根据ID批量删除MaterialUserFootmark", notes = "根据ID批量删除MaterialUserFootmark")
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
	@ApiOperation(value = "根据ID加载MaterialUserFootmark", notes = "根据ID加载MaterialUserFootmark")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 添加一条用户足迹
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
	public ModelAndView addCollection(MaterialUserFootmark voObj){
		ModelAndView modelAndView = new ModelAndView();
		String footmarkCls = voObj.getFootmarkCls();
		if(footmarkCls ==null || "".equals(footmarkCls)){
			return buildErrorResponse(modelAndView, "足迹分类不能为空!");
		}
		String relationId = voObj.getRelationId();
		if(relationId ==null || "".equals(relationId)){
			return buildErrorResponse(modelAndView, "关联分类ID不能为空!");
		}
		String footmarkTitle = voObj.getFootmarkTitle();
		if(footmarkTitle ==null || "".equals(footmarkTitle)){
			return buildErrorResponse(modelAndView, "足迹标题不能为空!");
		}
		String imageUrl = voObj.getImageUrl();
		if(imageUrl ==null || "".equals(imageUrl)){
			return buildErrorResponse(modelAndView, "封面图不能为空!");
		}
		SysWxUser sysWxUser = getLoginWxUser();
		MaterialUser materialUser = materialUserService.loadByMiniOpenId(sysWxUser.getOpenId());
		voObj.setCreateUserId(materialUser.getId());
		voObj.setCreateTime(new Date());
		MaterialUserFootmark userFootmark = service.insertOrUpdate(voObj);
		return buildResponse(modelAndView,userFootmark);
	}

	/*删除用户足迹*/
	@RequestMapping(value = "/deleteVo",method = RequestMethod.POST)
	public ModelAndView deleteVo(String type){
		ModelAndView modelAndView = new ModelAndView();
		if(type==null || "".equals(type)){
			return buildErrorResponse(modelAndView, "类型不能为空!");
		}
		SysWxUser sysWxUser = getLoginWxUser();
		MaterialUser materialUser = materialUserService.loadByMiniOpenId(sysWxUser.getOpenId());
		String userId = materialUser.getId();
		service.deleteVo(userId,type);
		return buildResponse(modelAndView);
	}
}
