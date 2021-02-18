package com.edgedo.material.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.service.SysCheckService;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.material.constant.VerifyOperateServiceConstant;
import com.edgedo.material.entity.MaterialBuildIntroduction;
import com.edgedo.material.queryvo.MaterialBuildIntroductionQuery;
import com.edgedo.material.queryvo.MaterialBuildIntroductionView;
import com.edgedo.material.service.MaterialBuildIntroductionService;
import com.edgedo.material.service.MaterialProducerRelationService;
import com.edgedo.material.service.MaterialProducerSysUserRelationService;
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


@Api(tags = "MaterialBuildIntroduction")
@Controller
@RequestMapping("/material/materialBuildIntroduction")
public class MaterialBuildIntroductionController extends BaseController{
	
	@Autowired
	private MaterialBuildIntroductionService service;
	@Value("${app.faceai.imgcompresswidth}")
	private Integer app_faceai_imgcompresswidth;
	@Value("${fileForder}")
	private String fileForder;
	@Autowired
	private MaterialProducerSysUserRelationService materialProducerSysUserRelationService;

	@Autowired
	private MaterialProducerRelationService materialProducerRelationService;
	@Autowired
	private SysCheckService sysCheckService;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialBuildIntroduction", notes = "分页查询MaterialBuildIntroduction")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialBuildIntroductionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 厂商 查询
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageProduct",method = RequestMethod.POST)
	public ModelAndView listpageProduct(@ModelAttribute MaterialBuildIntroductionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//查出当前登陆人的id
		User user = getLoginUser();
		//查出厂商id
		String producerId = materialProducerSysUserRelationService.selectBySysUserId(user.getUserId());
		//查出所有的材料id
		List<String> materialIdList = materialProducerRelationService.selectByProducerId(producerId);
		if (materialIdList.size()==0){
			buildResponse(modelAndView,query);
			return modelAndView;
		}else {
			query.setMaterIdList(materialIdList);
			service.listPage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialBuildIntroduction", notes = "新增修改MaterialBuildIntroduction")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialBuildIntroduction voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		String fileUrl= voObj.getFileUrl();
		if(fileUrl !=null && !fileUrl.equals("")){
			String filePath = "";
			File file = new File(fileUrl);
			try {
				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setFileUrl(filePath);
		}else {
			voObj.setFileUrl(null);
		}
		User user =getLoginUser();
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setShState("1");
			voObj.setDataState("1");
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
	 * 厂商新增
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/insertProductBuild",method = RequestMethod.POST)
	public ModelAndView insertProductBuild(MaterialBuildIntroduction voObj){
		ModelAndView modelAndView = new ModelAndView();
		String fileUrl= voObj.getFileUrl();
		if(fileUrl !=null && !fileUrl.equals("")){
			String filePath = "";
			File file = new File(fileUrl);
			try {
				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setFileUrl(filePath);
		}else {
			voObj.setFileUrl(null);
		}
		User user =getLoginUser();
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setShState("0");
		voObj.setDataState("1");
		service.insert(voObj);
		//审核表 重新审核
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceDes("施工说明审核");
		sysCheck.setServiceId(voObj.getId());
		//verifyOperateServiceConstant
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_BUILD_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_BUILD_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
	}

	/**
	 * 厂商修改
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/updateProductBuild",method = RequestMethod.POST)
	public ModelAndView updateProductBuild(MaterialBuildIntroduction voObj){
		ModelAndView modelAndView = new ModelAndView();
		String fileUrl= voObj.getFileUrl();
		if(fileUrl !=null && !fileUrl.equals("")){
			String filePath = "";
			File file = new File(fileUrl);
			try {
				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setFileUrl(filePath);
		}else {
			voObj.setFileUrl(null);
		}
		User user =getLoginUser();
		voObj.setShState("0");
		service.update(voObj);
		//审核表 重新审核
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceDes("施工说明审核");
		//verifyOperateServiceConstant
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_BUILD_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_BUILD_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除MaterialBuildIntroduction", notes = "根据ID批量删除MaterialBuildIntroduction")
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
	@ApiOperation(value = "根据ID加载MaterialBuildIntroduction", notes = "根据ID加载MaterialBuildIntroduction")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	@RequestMapping(value = "/selectByMaterId",method = RequestMethod.POST)
	public ModelAndView  selectByMaterId(String materId){
		ModelAndView modelAndView = new ModelAndView();
		MaterialBuildIntroductionView materialBuildIntroduction = service.selectByMaterId(materId);
		return buildResponse(modelAndView, materialBuildIntroduction);
	}

	/**
	 * 审核列表
	 * @return
	 */
	@RequestMapping(value = "/verifyListPage",method = RequestMethod.POST)
	public ModelAndView verifyListPage(MaterialBuildIntroductionQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if(query.getOrderBy() == null){
			query.setOrderBy("CREATE_TIME DESC");
		}
		query.getQueryObj().setShState("0");
		service.listPage(query);
		return buildResponse(modelAndView,query);
	}

	/**
	 * 审核通过
	 * @return
	 */
	@RequestMapping(value = "/buildIntroVerifyPass",method = RequestMethod.POST)
	public ModelAndView buildIntroVerifyPass(String id){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialBuildIntroduction introduction = service.loadById(id);
		if(introduction == null){
			return buildErrorResponse(modelAndView,"未查询到记录");
		}
		introduction.setShTime(new Date());
		introduction.setShUserId(user.getUserId());
		introduction.setShUserName(user.getUserName());
		introduction.setShState("1");
		service.update(introduction);
		return buildResponse(modelAndView);
	}

	/**
	 * 审核失败
	 * @return
	 */
	@RequestMapping(value = "/buildIntroVerifyFail",method = RequestMethod.POST)
	public ModelAndView buildIntroVerifyFail(String id,String failReason){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialBuildIntroduction introduction = service.loadById(id);
		if(introduction == null){
			return buildErrorResponse(modelAndView,"未查询到记录");
		}
		introduction.setShTime(new Date());
		introduction.setShUserId(user.getUserId());
		introduction.setShUserName(user.getUserName());
		introduction.setShState("-1");
		introduction.setNotPassText(failReason);
		service.update(introduction);
		return buildResponse(modelAndView);
	}
	
}
