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
import com.edgedo.material.entity.MaterialCaseCls;
import com.edgedo.material.queryvo.MaterialCaseClsQuery;
import com.edgedo.material.queryvo.MaterialCaseClsView;
import com.edgedo.material.queryvo.MaterialView;
import com.edgedo.material.service.MaterialCaseClsService;
import com.edgedo.material.service.MaterialProducerRelationService;
import com.edgedo.material.service.MaterialProducerSysUserRelationService;
import com.edgedo.material.util.FileUtil2;
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


@Api(tags = "MaterialCaseCls")
@Controller
@RequestMapping("/material/materialCaseCls")
public class MaterialCaseClsController extends BaseController{
	
	@Autowired
	private MaterialCaseClsService service;
	@Value("${fileForder}")
	private String fileForder;
	@Autowired
	private MaterialProducerSysUserRelationService materialProducerSysUserRelationService;

	@Autowired
	private MaterialProducerRelationService materialProducerRelationService;
	@Autowired
	private SysCheckService sysCheckService;


	@Value("${app.faceaicls.maxfacelength}")
	private Integer app_faceaicls_maxfacelength;
	@Value("${app.faceaicls.imgcompresswidth}")
	private Integer app_faceaicls_imgcompresswidth;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialCaseCls", notes = "分页查询MaterialCaseCls")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialCaseClsQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//案例查询
		if (query.getOrderBy() == null){
			query.setOrderBy("ORDER_NUM asc");
		}
		query.getQueryObj().setType("CASE");
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	@ApiOperation(value = "分页查询MaterialCaseCls", notes = "分页查询MaterialCaseCls")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpageColor",method = RequestMethod.POST)
	public ModelAndView listpageColor(@ModelAttribute MaterialCaseClsQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//查询色卡
		if (query.getOrderBy() == null){
			query.setOrderBy("ORDER_NUM asc");
		}
		query.getQueryObj().setType("COLOR_MAP");
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/***
	 * 根据厂商id 查出相关的所有的案例色卡
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageProducer",method = RequestMethod.POST)
	public ModelAndView listpageProducer(@ModelAttribute MaterialCaseClsQuery query){
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
			//案例查询
			query.getQueryObj().setType("CASE");
			service.listPage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}

	@RequestMapping(value = "/listpageProducerColor",method = RequestMethod.POST)
	public ModelAndView listpageProducerColor(@ModelAttribute MaterialCaseClsQuery query){
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
			//案例查询
			query.getQueryObj().setType("COLOR_MAP");
			service.listPage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}


	/**
	 * 切换启用状态
	 * @param id isEnable
	 * @return
	 */
	@RequestMapping(value = "/switchEnableState",method = RequestMethod.POST)
	public ModelAndView  switchEnableState(String id, String isEnable){
		ModelAndView modelAndView = new ModelAndView();
		service.updateEnableState(id,isEnable);
		return buildResponse(modelAndView);
	}



	@RequestMapping(value = "/materialClsNameAll",method = RequestMethod.POST)
	public ModelAndView  materialClsNameAll(){
		ModelAndView modelAndView = new ModelAndView();
		List<MaterialCaseClsView> list = service.materialClsNameAll();
		return buildResponse(modelAndView,list);
	}

	@RequestMapping(value = "/materialClsNameAllColor",method = RequestMethod.POST)
	public ModelAndView  materialClsNameAllColor(){
		//COLOR_MAP
		ModelAndView modelAndView = new ModelAndView();
		List<MaterialCaseClsView> list = service.materialClsNameAllColor();
		return buildResponse(modelAndView,list);
	}

	@RequestMapping(value = "/materialClsNameAllProduct",method = RequestMethod.POST)
	public ModelAndView  materialClsNameAllProduct(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		//查出厂商id
		String producerId = materialProducerSysUserRelationService.selectBySysUserId(user.getUserId());
		//查出所有的材料id
		List<String> materialIdList = materialProducerRelationService.selectByProducerId(producerId);
		if (materialIdList.size()==0){
			return buildResponse(modelAndView,new ArrayList<>());
		}else {
			List<MaterialCaseClsView> list = service.materialClsNameAllMaterIdList(materialIdList);
			return buildResponse(modelAndView,list);
		}
	}

	@RequestMapping(value = "/materialClsNameAllProductColor",method = RequestMethod.POST)
	public ModelAndView  materialClsNameAllProductColor(){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		//查出厂商id
		String producerId = materialProducerSysUserRelationService.selectBySysUserId(user.getUserId());
		//查出所有的材料id
		List<String> materialIdList = materialProducerRelationService.selectByProducerId(producerId);
		if (materialIdList.size()==0){
			return buildResponse(modelAndView,new ArrayList<>());
		}else {
			List<MaterialCaseClsView> list = service.materialClsNameAllMaterIdListColor(materialIdList);
			return buildResponse(modelAndView,list);
		}
	}


	@RequestMapping(value = "/materialNameAndClsId",method = RequestMethod.POST)
	public ModelAndView  materialNameAndClsId(String materialId){
		ModelAndView modelAndView = new ModelAndView();
		if (materialId!=""){
			List<MaterialCaseClsView> list = service.materialNameAndClsId(materialId);
			return buildResponse(modelAndView,list);
		}else {
			List<MaterialCaseClsView> list = service.materialClsNameAll();
			return buildResponse(modelAndView,list);
		}
	}

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialCaseCls", notes = "新增修改MaterialCaseCls")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialCaseCls voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		User user = getLoginUser();
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil2.getImageBase64Str(file);
				faceOld = FileUtil2.compressImg(faceOld,app_faceaicls_imgcompresswidth);
				filePath = FileUtil2.saveBase64Img(faceOld,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setImageUrl(filePath);
		}else {
			voObj.setImageUrl(null);
		}
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setIsEnable("1");
			voObj.setDataState("1");
			voObj.setShState("1");
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

	@RequestMapping(value = "/saveOrUpdateNew",method = RequestMethod.POST)
	public ModelAndView saveOrUpdateNew(MaterialCaseCls voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		User user = getLoginUser();
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaicls_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setImageUrl(filePath);
		}else {
			voObj.setImageUrl(null);
		}
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setIsEnable("1");
			voObj.setDataState("1");
			voObj.setShState("1");
			errMsg = service.insert(voObj);
		}else{
			errMsg = service.update(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			String clsNameAndId = voObj.getId()+","+voObj.getCaseClsName();
			buildResponse(modelAndView,clsNameAndId);
		}
		return modelAndView;
	}



	/**
	 * 厂商新增案例
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/insertProduct",method = RequestMethod.POST)
	public ModelAndView insertProduct(MaterialCaseCls voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaicls_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setImageUrl(filePath);
		}else {
			voObj.setImageUrl(null);
		}
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setIsEnable("0");
		voObj.setDataState("1");
		voObj.setShState("0");
		service.insert(voObj);
		//审核表
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceDes("案例色卡分类审核");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_CLS_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_CLS_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
	}


	/**
	 * 厂商修改案例
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
	public ModelAndView updateProduct(MaterialCaseCls voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaicls_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setImageUrl(filePath);
		}else {
			voObj.setImageUrl(null);
		}
		voObj.setShState("0");
		service.update(voObj);
		//审核表
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceDes("案例色卡分类审核");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_CLS_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_CLS_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
	}


	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除MaterialCaseCls", notes = "根据ID批量删除MaterialCaseCls")
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
	@ApiOperation(value = "根据ID加载MaterialCaseCls", notes = "根据ID加载MaterialCaseCls")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 审核列表
	 * @return
	 */
	@RequestMapping(value = "/verifyListPage",method = RequestMethod.POST)
	public ModelAndView verifyListPage(MaterialCaseClsQuery query){
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
	 */
	@RequestMapping(value = "/caseClsVerifyPass",method = RequestMethod.POST)
	public ModelAndView caseClsVerifyPass(String id){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialCaseCls caseCls = service.loadById(id);
		if(caseCls == null){
			return buildErrorResponse(modelAndView,"未查询到记录");
		}
		caseCls.setShUserId(user.getUserId());
		caseCls.setShUserName(user.getUserName());
		caseCls.setShTime(new Date());
		caseCls.setShState("1");
		service.update(caseCls);
		return buildResponse(modelAndView);
	}

	/**
	 * 审核失败
	 * @return
	 */
	@RequestMapping(value = "/caseClsVerifyFail",method = RequestMethod.POST)
	public ModelAndView caseClsVerifyFail(String id,String failReason){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialCaseCls caseCls = service.loadById(id);
		if(caseCls == null){
			return buildErrorResponse(modelAndView,"未查询到记录");
		}
		caseCls.setShTime(new Date());
		caseCls.setShUserId(user.getUserId());
		caseCls.setShUserName(user.getUserName());
		caseCls.setShState("-1");
		caseCls.setNotPassText(failReason);
		service.update(caseCls);
		return buildResponse(modelAndView);
	}
	
	
}
