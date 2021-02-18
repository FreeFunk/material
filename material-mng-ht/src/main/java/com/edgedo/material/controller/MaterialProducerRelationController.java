package com.edgedo.material.controller;


import java.util.ArrayList;
import java.util.List;

import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.material.entity.MaterialProducer;
import com.edgedo.material.entity.MaterialProducerRelation;
import com.edgedo.material.queryvo.MaterialProducerRelationQuery;
import com.edgedo.material.service.MaterialProducerRelationService;
import com.edgedo.material.service.MaterialProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Api(tags = "MaterialProducerRelation")
@Controller
@RequestMapping("/material/materialProducerRelation")
public class MaterialProducerRelationController extends BaseController{
	
	@Autowired
	private MaterialProducerRelationService service;
	@Autowired
	private MaterialProducerService producerService;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialProducerRelation", notes = "分页查询MaterialProducerRelation")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialProducerRelationQuery query){
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
	@ApiOperation(value = "新增修改MaterialProducerRelation", notes = "新增修改MaterialProducerRelation")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialProducerRelation voObj){
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
	@ApiOperation(value = "根据ID批量删除MaterialProducerRelation", notes = "根据ID批量删除MaterialProducerRelation")
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
	@ApiOperation(value = "根据ID加载MaterialProducerRelation", notes = "根据ID加载MaterialProducerRelation")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 将材料授权给厂商
	 * @author: ZhangCC
	 * @time: 2020/9/10 9:17
	 */
	@RequestMapping(value = "/authMaterialToProducer",method = RequestMethod.POST)
	public ModelAndView authMaterialToProducer(String materialIds,String producerId){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialProducer producer = producerService.loadById(producerId);
		if(producer == null){
			return buildErrorResponse(modelAndView,"未查询到厂商信息");
		}
		service.insertProMaterialRel(user,materialIds,producerId);
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/delMatAndProRel",method = RequestMethod.POST)
	public ModelAndView delMatAndProRel(String materialId,String producerId){
		ModelAndView modelAndView = new ModelAndView();
		service.deleteByMatAndPro(producerId,materialId);
		return buildResponse(modelAndView);
	}
	
	
}
