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
import com.edgedo.common.util.Guid;
import com.edgedo.material.constant.VerifyOperateServiceConstant;
import com.edgedo.material.entity.Material;
import com.edgedo.material.entity.MaterialProducerRelation;
import com.edgedo.material.queryvo.MaterialQuery;
import com.edgedo.material.queryvo.MaterialView;
import com.edgedo.material.service.MaterialProducerRelationService;
import com.edgedo.material.service.MaterialProducerSysUserRelationService;
import com.edgedo.material.service.MaterialService;
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


@Api(tags = "Material")
@Controller
@RequestMapping("/material/material")
public class MaterialController extends BaseController{
	
	@Autowired
	private MaterialService service;
	@Value("${fileForder}")
	private String fileForder;
	@Autowired
	private MaterialProducerSysUserRelationService materialProducerSysUserRelationService;

	@Autowired
	private MaterialProducerRelationService materialProducerRelationService;
	@Autowired
	private SysCheckService sysCheckService;


	@Value("${app.faceaibanner.maxfacelength}")
	private Integer app_faceaibanner_maxfacelength;
	@Value("${app.faceaibanner.imgcompresswidth}")
	private Integer app_faceaibanner_imgcompresswidth;

	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询Material", notes = "分页查询Material")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if (query.getOrderBy() == null){
			query.setOrderBy("ORDER_NUM asc");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 厂商只能看自己的材料
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageProducer",method = RequestMethod.POST)
	public ModelAndView listpageProducer(@ModelAttribute MaterialQuery query){
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
	 * 厂商关联的材料
	 * @param query
	 * @param producerId
	 * @return
	 */
	@RequestMapping(value = "/proMaterialListpage",method = RequestMethod.POST)
	public ModelAndView proMaterialListpage(@ModelAttribute MaterialQuery query,String producerId){
		ModelAndView modelAndView = new ModelAndView();
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
	 * 厂商未关联的材料
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/notRelListpage",method = RequestMethod.POST)
	public ModelAndView notRelListpage(@ModelAttribute MaterialQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//查出所有的材料id
		service.notRelListPage(query);
		return buildResponse(modelAndView,query);
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
	/**
	 * 切换是否展示产品样式的状态状态
	 * @param id isEnable
	 * @return
	 */
	@RequestMapping(value = "/switchIsShowProductStyleState",method = RequestMethod.POST)
	public ModelAndView  switchIsShowProductStyleState(String id, String isShowProductStyle){
		ModelAndView modelAndView = new ModelAndView();
		service.updateIsShowProductStyleState(id,isShowProductStyle);
		return buildResponse(modelAndView);
	}

	@RequestMapping(value = "/materialNameAll",method = RequestMethod.POST)
	public ModelAndView  materialNameAll(){
		ModelAndView modelAndView = new ModelAndView();
		List<MaterialView> list = service.materialNameAll();
		return buildResponse(modelAndView,list);
	}

	/**
	 * 查出本厂商下的所有的材料
	 * @return
	 */
	@RequestMapping(value = "/materialNameAllProdouce",method = RequestMethod.POST)
	public ModelAndView  materialNameAllProdouce(){
		ModelAndView modelAndView = new ModelAndView();//查出当前登陆人的id
		User user = getLoginUser();
		//查出厂商id
		String producerId = materialProducerSysUserRelationService.selectBySysUserId(user.getUserId());
		//查出所有的材料id
		List<String> materialIdList = materialProducerRelationService.selectByProducerId(producerId);
		if (materialIdList.size()!=0){
			List<MaterialView> list = service.materialNameProdouceId(materialIdList);
			return buildResponse(modelAndView,list);
		}else {
			return buildResponse(modelAndView);
		}

	}



	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改Material", notes = "新增修改Material")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(Material voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		//产品报价图
		String productPriceImage= voObj.getProductPriceImage();
		//产品介绍图
		String productIntroductionImage= voObj.getProductIntroductionImage();
		//施工说明图
		String buildIntroductionImage= voObj.getBuildIntroductionImage();
		if(productPriceImage !=null && !productPriceImage.equals("")){
			String filePath = "";
			File file = new File(productPriceImage);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);

				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
//				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setProductPriceImage(filePath);
		}else {
			voObj.setProductPriceImage(null);
		}
		if(productIntroductionImage !=null && !productIntroductionImage.equals("")){
			String filePath = "";
			File file = new File(productIntroductionImage);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
//				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setProductIntroductionImage(filePath);
		}else {
			voObj.setProductIntroductionImage(null);
		}
		if(buildIntroductionImage !=null && !buildIntroductionImage.equals("")){
			String filePath = "";
			File file = new File(buildIntroductionImage);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
//				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setBuildIntroductionImage(filePath);
		}else {
			voObj.setBuildIntroductionImage(null);
		}
		User user =getLoginUser();
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setDataState("1");
			voObj.setShState("1");
			voObj.setIsEnable("1");
			if (voObj.getOrderNum()==null){
				voObj.setOrderNum(service.countAllMaterNum());
			}
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


	@RequestMapping(value = "/updateProducer",method = RequestMethod.POST)
	public ModelAndView updateProducer(Material voObj){
		ModelAndView modelAndView = new ModelAndView();
		//产品报价图
		String productPriceImage= voObj.getProductPriceImage();
		//产品介绍图
		String productIntroductionImage= voObj.getProductIntroductionImage();
		//施工说明图
		String buildIntroductionImage= voObj.getBuildIntroductionImage();
		if(productPriceImage !=null && !productPriceImage.equals("")){
			String filePath = "";
			File file = new File(productPriceImage);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
//				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setProductPriceImage(filePath);
		}else {
			voObj.setProductPriceImage(null);
		}
		if(productIntroductionImage !=null && !productIntroductionImage.equals("")){
			String filePath = "";
			File file = new File(productIntroductionImage);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
//				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setProductIntroductionImage(filePath);
		}else {
			voObj.setProductIntroductionImage(null);
		}
		if(buildIntroductionImage !=null && !buildIntroductionImage.equals("")){
			String filePath = "";
			File file = new File(buildIntroductionImage);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
//				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setBuildIntroductionImage(filePath);
		}else {
			voObj.setBuildIntroductionImage(null);
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
		sysCheck.setServiceDes("材料审核");
		//verifyOperateServiceConstant
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return  buildResponse(modelAndView);
	}

	/**
	 * 厂商管理员新增方法
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/insertProduce",method = RequestMethod.POST)
	public ModelAndView insertProduce(Material voObj){
		ModelAndView modelAndView = new ModelAndView();
		//产品报价图
		String productPriceImage= voObj.getProductPriceImage();
		//产品介绍图
		String productIntroductionImage= voObj.getProductIntroductionImage();
		//施工说明图
		String buildIntroductionImage= voObj.getBuildIntroductionImage();
		if(productPriceImage !=null && !productPriceImage.equals("")){
			String filePath = "";
			File file = new File(productPriceImage);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
//				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setProductPriceImage(filePath);
		}else {
			voObj.setProductPriceImage(null);
		}
		if(productIntroductionImage !=null && !productIntroductionImage.equals("")){
			String filePath = "";
			File file = new File(productIntroductionImage);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
//				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setProductIntroductionImage(filePath);
		}else {
			voObj.setProductIntroductionImage(null);
		}
		if(buildIntroductionImage !=null && !buildIntroductionImage.equals("")){
			String filePath = "";
			File file = new File(buildIntroductionImage);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
//				filePath = FileUtil.saveFile(file,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setBuildIntroductionImage(filePath);
		}else {
			voObj.setBuildIntroductionImage(null);
		}
		User user =getLoginUser();
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setDataState("1");
		voObj.setShState("0");
		voObj.setIsEnable("0");
		voObj.setOrderNum(service.countAllMaterNum());
		//插入厂商材料关联表
		service.insert(voObj);
		//查出厂商id
		String producerId = materialProducerSysUserRelationService.selectBySysUserId(user.getUserId());
		MaterialProducerRelation materialProducerRelation = new MaterialProducerRelation();
		materialProducerRelation.setCreateTime(new Date());
		materialProducerRelation.setCreateUserId(user.getUserId());
		materialProducerRelation.setMaterialId(voObj.getId());
		materialProducerRelation.setProducerId(producerId);
		materialProducerRelationService.insert(materialProducerRelation);
		//审核表
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceDes("材料审核");
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
	}
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除Material", notes = "根据ID批量删除Material")
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
	@ApiOperation(value = "根据ID加载Material", notes = "根据ID加载Material")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 材料审核列表
	 * @return
	 */
	@RequestMapping(value = "/verifyListPage",method = RequestMethod.POST)
	public ModelAndView verifyListPage(MaterialQuery query){
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
	@RequestMapping(value = "/materialVerifyPass",method = RequestMethod.POST)
	public ModelAndView materialVerifyPass(String id){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		Material material = service.loadById(id);
		if(material == null){
			return buildErrorResponse(modelAndView,"未查询到材料信息");
		}
		material.setShUserId(user.getUserId());
		material.setShUserName(user.getUserName());
		material.setShTime(new Date());
		material.setShState("1");
		service.update(material);
		return buildResponse(modelAndView);
	}

	/**
	 * 审核失败
	 * @return
	 */
	@RequestMapping(value = "/materialVerifyFail",method = RequestMethod.POST)
	public ModelAndView materialVerifyFail(String id,String failReason){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		Material material = service.loadById(id);
		if(material == null){
			return buildErrorResponse(modelAndView,"未查询到材料信息");
		}
		material.setShTime(new Date());
		material.setShUserId(user.getUserId());
		material.setShUserName(user.getUserName());
		material.setShState("-1");
		material.setNotPassText(failReason);
		service.update(material);
		return buildResponse(modelAndView);
	}
	
	
}
