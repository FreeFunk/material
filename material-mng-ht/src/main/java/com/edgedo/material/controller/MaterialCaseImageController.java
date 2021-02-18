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
import com.edgedo.material.entity.MaterialCase;
import com.edgedo.material.entity.MaterialCaseImage;
import com.edgedo.material.queryvo.MaterialCaseImageQuery;
import com.edgedo.material.queryvo.MaterialCaseImageView;
import com.edgedo.material.service.MaterialCaseImageService;
import com.edgedo.material.service.MaterialCaseService;
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


@Api(tags = "MaterialCaseImage")
@Controller
@RequestMapping("/material/materialCaseImage")
public class MaterialCaseImageController extends BaseController{
	
	@Autowired
	private MaterialCaseImageService service;
	@Value("${fileForder}")
	private String fileForder;

	@Value("${app.faceai.maxfacelength}")
	private Integer app_faceai_maxfacelength;
	@Value("${app.faceai.imgcompresswidth}")
	private Integer app_faceai_imgcompresswidth;

	@Value("${app.faceaicls.imgcompresswidth}")
	private Integer app_faceaicls_imgcompresswidth;
	@Autowired
	private SysCheckService sysCheckService;
	@Autowired
	private MaterialCaseService materialCaseService;


	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialCaseImage", notes = "分页查询MaterialCaseImage")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialCaseImageQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if (query.getOrderBy() == null){
			query.setOrderBy("ORDER_NUM asc");
		}
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}
	

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialCaseImage", notes = "新增修改MaterialCaseImage")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialCaseImage voObj){
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
	@ApiOperation(value = "根据ID批量删除MaterialCaseImage", notes = "根据ID批量删除MaterialCaseImage")
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
	@ApiOperation(value = "根据ID加载MaterialCaseImage", notes = "根据ID加载MaterialCaseImage")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/loadById",method = RequestMethod.POST)
	public ModelAndView  loadById(String id){
		ModelAndView modelAndView = new ModelAndView();
		return buildResponse(modelAndView, service.loadById(id));
	}

	@RequestMapping(value = "/insertNew",method = RequestMethod.POST)
	public ModelAndView  insertNew(MaterialCaseImage voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String[] imgArr = voObj.getOrgImageUrl().split(",");
		for(String imgUrl : imgArr){
			if(imgUrl !=null && !imgUrl.equals("")){
				String filePath = "";
				String filePathSecond = "";
				File file = new File(imgUrl);
				try {
					//原图
					filePath = FileUtil.saveFile(file,fileForder,true);
					voObj.setOrgImageUrl(filePath);
					//压缩 ==> 展示图
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					voObj.setShowImageUrl(filePath);
					//二次 ==> 缩略图
					String faceOldSencond = FileUtil.getImageBase64Str(file);
					faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
					filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
					voObj.setCaseMiniImg(filePathSecond);

					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				voObj.setOrgImageUrl(null);
				voObj.setShowImageUrl(null);
				voObj.setCaseMiniImg(null);
			}
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setDataState("1");
			voObj.setShState("1");
			service.insert(voObj);
			//修改案例色卡信息缩略图
			MaterialCase materialCase = materialCaseService.loadById(voObj.getCaseId());
			materialCase.setCaseMiniImg(voObj.getCaseMiniImg());
			materialCaseService.updateById(materialCase);
		}
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/insertColorNew",method = RequestMethod.POST)
	public ModelAndView  insertColorNew(MaterialCaseImage voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String[] imgArr = voObj.getOrgImageUrl().split(",");
		for(String imgUrl : imgArr){
			if(imgUrl !=null && !imgUrl.equals("")){
				String filePath = "";
				String filePathSecond = "";
				File file = new File(imgUrl);
				try {
					//原图
					filePath = FileUtil.saveFile(file,fileForder,true);
					voObj.setOrgImageUrl(filePath);
					//压缩 ==> 展示图
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					voObj.setShowImageUrl(filePath);
					//二次 ==> 缩略图
					String faceOldSencond = FileUtil.getImageBase64Str(file);
					faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
					filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
					voObj.setCaseMiniImg(filePathSecond);

					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				voObj.setOrgImageUrl(null);
				voObj.setShowImageUrl(null);
				voObj.setCaseMiniImg(null);
			}
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setDataState("1");
			voObj.setShState("1");
			service.insertColor(voObj);
			//修改案例色卡信息缩略图
			MaterialCase materialCase = materialCaseService.loadById(voObj.getCaseId());
			materialCase.setCaseMiniImg(voObj.getCaseMiniImg());
			materialCaseService.updateById(materialCase);
		}
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/updateNew",method = RequestMethod.POST)
	public ModelAndView  updateNew(MaterialCaseImage voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String imgUrl = voObj.getOrgImageUrl();
		if(imgUrl !=null && !imgUrl.equals("")){
			String filePath = "";
			String filePathSecond = "";
			File file = new File(imgUrl);
			try {
				//原图
				filePath = FileUtil.saveFile(file,fileForder,true);
				voObj.setOrgImageUrl(filePath);
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				voObj.setShowImageUrl(filePath);
				//二次 ==> 缩略图
				String faceOldSencond = FileUtil.getImageBase64Str(file);
				faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
				filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
				voObj.setCaseMiniImg(filePathSecond);

				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//修改案例色卡信息缩略图
			MaterialCaseImage materialCaseImage = service.loadById(voObj.getId());
			MaterialCase materialCase = materialCaseService.loadById(materialCaseImage.getCaseId());
			materialCase.setCaseMiniImg(voObj.getCaseMiniImg());
			materialCaseService.updateById(materialCase);
		}
		service.update(voObj);

		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/insertProudctNew",method = RequestMethod.POST)
	public ModelAndView  insertProudctNew(MaterialCaseImage voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String[] imgArr = voObj.getOrgImageUrl().split(",");
		for(String imgUrl : imgArr){
			if(imgUrl !=null && !imgUrl.equals("")){
				String filePath = "";
				String filePathSecond = "";
				File file = new File(imgUrl);
				try {
					//原图
					filePath = FileUtil.saveFile(file,fileForder,true);
					voObj.setOrgImageUrl(filePath);
					//压缩 ==> 展示图
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					voObj.setShowImageUrl(filePath);
					//二次 ==> 缩略图
					String faceOldSencond = FileUtil.getImageBase64Str(file);
					faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
					filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
					voObj.setCaseMiniImg(filePathSecond);

					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				voObj.setOrgImageUrl(null);
				voObj.setShowImageUrl(null);
				voObj.setCaseMiniImg(null);
			}
			voObj.setCreateTime(new Date());
			voObj.setCreateUserId(user.getUserId());
			voObj.setShState("0");
			voObj.setDataState("1");
			service.insert(voObj);
			//审核表
			SysCheck sysCheck = new SysCheck();
			sysCheck.setCreateTime(new Date());
			sysCheck.setCreateUserId(user.getUserId());
			sysCheck.setCreateUserName(user.getUserName());
			sysCheck.setDataState("1");
			sysCheck.setServiceId(voObj.getId());
			sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_IMG_SERVICE);
			sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_IMG_PAGE);
			sysCheck.setShState("0");
			sysCheckService.insert(sysCheck);
		}
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/updateProductNew",method = RequestMethod.POST)
	public ModelAndView  updateProductNew(MaterialCaseImage voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String imgUrl = voObj.getOrgImageUrl();
		if(imgUrl !=null && !imgUrl.equals("")){
			String filePath = "";
			String filePathSecond = "";
			File file = new File(imgUrl);
			try {
				//原图
				filePath = FileUtil.saveFile(file,fileForder,true);
				voObj.setOrgImageUrl(filePath);
				//压缩 ==> 展示图
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				voObj.setShowImageUrl(filePath);
				//二次 ==> 缩略图
				String faceOldSencond = FileUtil.getImageBase64Str(file);
				faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
				filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
				voObj.setCaseMiniImg(filePathSecond);

				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setShState("0");
		}
		service.update(voObj);
		//审核表
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceId(voObj.getId());
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_IMG_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_IMG_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);

		return buildResponse(modelAndView);
	}

	/**
	 * 根据产品id查询关联图片
	 * @author: ZhangCC
	 * @time: 2020/9/2 13:35
	 */
	@RequestMapping(value = "/selectVoByCaseId",method = RequestMethod.POST)
	public ModelAndView selectVoByCaseId(String caseId){
		ModelAndView modelAndView = new ModelAndView();
		MaterialCaseImageView caseImage = service.selectVoByCaseId(caseId);
		return buildResponse(modelAndView,caseImage);
	}

	/**
	 * 查询需要审核的记录
	 * @author: ZhangCC
	 * @time: 2020/9/18 13:53
	 */
	@RequestMapping(value = "/verifyListPage",method = RequestMethod.POST)
	public ModelAndView verifyListPage(@ModelAttribute MaterialCaseImageQuery query){
		ModelAndView modelAndView = new ModelAndView();
		if (query.getOrderBy() == null){
			query.setOrderBy("ORDER_NUM asc");
		}
		query.getQueryObj().setShState("0");
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

}
