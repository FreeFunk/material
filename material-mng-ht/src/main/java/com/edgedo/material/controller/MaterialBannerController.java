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
import com.edgedo.material.entity.MaterialBanner;
import com.edgedo.material.queryvo.MaterialBannerQuery;
import com.edgedo.material.service.MaterialBannerService;
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


@Api(tags = "MaterialBanner")
@Controller
@RequestMapping("/material/materialBanner")
public class MaterialBannerController extends BaseController{
	
	@Autowired
	private MaterialBannerService service;
	@Value("${fileForder}")
	private String fileForder;
	@Autowired
	private MaterialProducerSysUserRelationService materialProducerSysUserRelationService;

	@Autowired
	private MaterialProducerRelationService materialProducerRelationService;
	@Autowired
	private SysCheckService sysCheckService;
	@Value("${app.faceaibanner.imgcompresswidth}")
	private Integer app_faceaibanner_imgcompresswidth;
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialBanner", notes = "分页查询MaterialBanner")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialBannerQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if (query.getOrderBy() == null){
			query.setOrderBy("ORDER_NUM asc");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}


	/**
	 * 厂商查看自己的轮播图信息
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageProdouce",method = RequestMethod.POST)
	public ModelAndView listpageProdouce(@ModelAttribute MaterialBannerQuery query){
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
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialBanner", notes = "新增修改MaterialBanner")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialBanner voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		//轮播图
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
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
		User user =getLoginUser();
		if(id==null || id.trim().equals("")){
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setDataState("1");
			voObj.setShState("1");
			voObj.setIsEnable("1");
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
	public ModelAndView saveOrUpdateNew(MaterialBanner voObj){
		ModelAndView modelAndView = new ModelAndView();
		String errMsg = "";
		String id = voObj.getId();
		User user = getLoginUser();
		//轮播图
		String imageUrl= voObj.getImageUrl();
		String[] imageUrlStr = imageUrl.split(",");
		for(String imageUrlS : imageUrlStr){
			String filePath = "";
			File file = new File(imageUrlS);
			try {
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setImageUrl(filePath);

			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setCreateUserName(user.getUserName());
			voObj.setDataState("1");
			voObj.setShState("1");
			voObj.setIsEnable("1");
			errMsg = service.insert(voObj);
		}
		if(errMsg!=null && !errMsg.equals("")){
			buildErrorResponse(modelAndView, errMsg);
		}else{
			buildResponse(modelAndView);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/insertProdouceBanner",method = RequestMethod.POST)
	public ModelAndView insertProdouceBanner(MaterialBanner voObj){
		ModelAndView modelAndView = new ModelAndView();

		//轮播图
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
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
		User user =getLoginUser();
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setDataState("1");
		voObj.setShState("0");
		voObj.setIsEnable("0");
		service.insert(voObj);
		//审核表
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceDes("轮播图审核");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_BANNER_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_BANNER_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/updateProducerBanner",method = RequestMethod.POST)
	public ModelAndView updateProducerBanner(MaterialBanner voObj){
		ModelAndView modelAndView = new ModelAndView();

		//轮播图
		String imageUrl= voObj.getImageUrl();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceaibanner_imgcompresswidth);
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
		User user =getLoginUser();
		voObj.setShState("0");
		service.update(voObj);
		//审核表
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceDes("轮播图审核");
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_BANNER_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_BANNER_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
	}


	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "根据ID批量删除MaterialBanner", notes = "根据ID批量删除MaterialBanner")
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
	@ApiOperation(value = "根据ID加载MaterialBanner", notes = "根据ID加载MaterialBanner")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	/**
	 * 轮播图审核列表
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/verifyListPage",method = RequestMethod.POST)
	public ModelAndView verifyListPage(MaterialBannerQuery query){
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
	@RequestMapping(value = "/bannerVerifyPass",method = RequestMethod.POST)
	public ModelAndView bannerVerifyPass(String id){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialBanner banner = service.loadById(id);
		if(banner == null){
			return buildErrorResponse(modelAndView,"未查询到记录");
		}
		banner.setShTime(new Date());
		banner.setShUserId(user.getUserId());
		banner.setShUserName(user.getUserName());
		banner.setShState("1");
		service.update(banner);
		return buildResponse(modelAndView);
	}

	/**
	 * 审核失败
	 * @return
	 */
	@RequestMapping(value = "/bannerVerifyFail",method = RequestMethod.POST)
	public ModelAndView bannerVerifyFail(String id,String failReason){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialBanner banner = service.loadById(id);
		if(banner == null){
			return buildErrorResponse(modelAndView,"未查询到记录");
		}
		banner.setShTime(new Date());
		banner.setShUserId(user.getUserId());
		banner.setShUserName(user.getUserName());
		banner.setShState("-1");
		banner.setNotPassText(failReason);
		service.update(banner);
		return buildResponse(modelAndView);
	}
	
}
