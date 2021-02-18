package com.edgedo.material.controller;


import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.service.SysCheckService;
import com.edgedo.common.base.BaseController;
import com.edgedo.common.shiro.User;
import com.edgedo.common.util.FileUtil;
import com.edgedo.material.constant.VerifyOperateServiceConstant;
import com.edgedo.material.entity.Material;
import com.edgedo.material.entity.MaterialCase;
import com.edgedo.material.entity.MaterialCaseCls;
import com.edgedo.material.entity.MaterialCaseImage;
import com.edgedo.material.queryvo.MaterialCaseImageQuery;
import com.edgedo.material.queryvo.MaterialCaseImageView;
import com.edgedo.material.queryvo.MaterialCaseQuery;
import com.edgedo.material.service.*;
import com.edgedo.material.util.ImageUtil;
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

import static com.edgedo.common.util.HttpRequestUtil.readInputStream;


@Api(tags = "MaterialCase")
@Controller
@RequestMapping("/material/materialCase")
public class MaterialCaseController extends BaseController{
	
	@Autowired
	private MaterialCaseService service;
	@Autowired
	private MaterialCaseImageService caseImageService;
	@Value("${fileForder}")
	private String fileForder;

	@Value("${app.faceai.maxfacelength}")
	private Integer app_faceai_maxfacelength;
	@Value("${app.faceai.imgcompresswidth}")
	private Integer app_faceai_imgcompresswidth;

	@Value("${app.faceaicls.imgcompresswidth}")
	private Integer app_faceaicls_imgcompresswidth;
	@Autowired
	private MaterialProducerSysUserRelationService materialProducerSysUserRelationService;

	@Autowired
	private MaterialProducerRelationService materialProducerRelationService;
	@Autowired
	private SysCheckService sysCheckService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private MaterialCaseClsService materialCaseClsService;
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "分页查询MaterialCase", notes = "分页查询MaterialCase")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/listpage",method = RequestMethod.POST)
	public ModelAndView listpage(@ModelAttribute MaterialCaseQuery query){
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

	@RequestMapping(value = "/selectByCaseNum",method = RequestMethod.POST)
	public ModelAndView selectByCaseNum( String materialId,String caseClsId){
		ModelAndView modelAndView = new ModelAndView();
		Integer num = service.selectByCaseNum(materialId,caseClsId,"CASE")+1;
		Material material = materialService.loadById(materialId);
		MaterialCaseCls materialCaseCls = materialCaseClsService.loadById(caseClsId);
		String caseName = material.getMaterialName()+"-"+materialCaseCls.getCaseClsName()+"-";
		if(num<10){
			caseName = caseName+"0"+num.toString();
		}else {
			caseName = caseName+num.toString();
		}
		buildResponse(modelAndView,caseName);
		return modelAndView;
	}


	@RequestMapping(value = "/selectByCaseNumColor",method = RequestMethod.POST)
	public ModelAndView selectByCaseNumColor( String materialId,String caseClsId){
		ModelAndView modelAndView = new ModelAndView();
		Material material = materialService.loadById(materialId);
		MaterialCaseCls materialCaseCls = materialCaseClsService.loadById(caseClsId);
		String caseName = material.getMaterialName()+"-"+materialCaseCls.getCaseClsName();
		buildResponse(modelAndView,caseName);
		return modelAndView;
	}

	@RequestMapping(value = "/listpageColor",method = RequestMethod.POST)
	public ModelAndView listpageColor(@ModelAttribute MaterialCaseQuery query){
		ModelAndView modelAndView = new ModelAndView();
		//色卡查询
		if (query.getOrderBy() == null){
			query.setOrderBy("ORDER_NUM asc");
		}
		query.getQueryObj().setType("COLOR_MAP");
		service.listPage(query);
		buildResponse(modelAndView,query);
		return modelAndView;
	}

	/**
	 * 厂商查询自己的案例色卡
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/listpageProduct",method = RequestMethod.POST)
	public ModelAndView listpageProduct(@ModelAttribute MaterialCaseQuery query){
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
			//色卡查询
			query.getQueryObj().setType("COLOR_MAP");
			service.listPage(query);
			buildResponse(modelAndView,query);
			return modelAndView;
		}
	}

	@RequestMapping(value = "/listpageProductColor",method = RequestMethod.POST)
	public ModelAndView listpageProductColor(@ModelAttribute MaterialCaseQuery query){
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

	/**
	 * 新增修改
	 * @param voObj
	 * @return
	 */
	@ApiOperation(value = "新增修改MaterialCase", notes = "新增修改MaterialCase")
	@ApiImplicitParam(name = "access-token", value = "用户登录秘钥", paramType = "header", required = true, dataType = "String")
	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(MaterialCase voObj){
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
	@ApiOperation(value = "根据ID批量删除MaterialCase", notes = "根据ID批量删除MaterialCase")
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
	 * 根据主键加载
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "根据ID加载MaterialCase", notes = "根据ID加载MaterialCase")
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
	public ModelAndView verifyListPage(MaterialCaseQuery query){
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
	@RequestMapping(value = "/caseVerifyPass",method = RequestMethod.POST)
	public ModelAndView caseVerifyPass(String id){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialCase materialCase = service.loadById(id);
		if(materialCase == null){
			return buildErrorResponse(modelAndView,"未查询到记录");
		}
		materialCase.setShUserId(user.getUserId());
		materialCase.setShUserName(user.getUserName());
		materialCase.setShTime(new Date());
		materialCase.setShState("1");
		service.update(materialCase);
		return buildResponse(modelAndView);
	}

	/**
	 * 审核失败
	 * @return
	 */
	@RequestMapping(value = "/caseVerifyFail",method = RequestMethod.POST)
	public ModelAndView caseVerifyFail(String id,String failReason){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		MaterialCase materialCase = service.loadById(id);
		if(materialCase == null){
			return buildErrorResponse(modelAndView,"未查询到记录");
		}
		materialCase.setShTime(new Date());
		materialCase.setShUserId(user.getUserId());
		materialCase.setShUserName(user.getUserName());
		materialCase.setShState("-1");
		materialCase.setNotPassText(failReason);
		service.update(materialCase);
		return buildResponse(modelAndView);
	}

	/**
	 * 新增
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/insertNew",method = RequestMethod.POST)
	public ModelAndView insertNew(MaterialCase voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String imageUrl= voObj.getCaseMiniImg();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			String filePathSecond = "";
			File file = new File(imageUrl);
			try {
				//获取图片的宽高
				int[] imageSize = FileUtil.getPhotoWidthAndHeight(file);
				int imageWidth = imageSize[0];
				int imageHeight = imageSize[1];
				if (imageWidth <= 3000){
					filePath = FileUtil.saveFile(file,fileForder,true);
					voObj.setOrgImageUrl(filePath);
				}else {
					imageHeight = imageHeight/(imageWidth/3000);
					imageWidth = 3000;
					String fileNewStr = FileUtil.getImageBase64Str(file);
					fileNewStr = FileUtil.compressImg(fileNewStr,3000);
					filePath = FileUtil.saveBase64Img(fileNewStr,fileForder,true);
					voObj.setOrgImageUrl(filePath);
				}
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
				voObj.setImageHeight(imageHeight);
				voObj.setImageWidth(imageWidth);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			voObj.setCaseMiniImg(null);
			voObj.setShowImageUrl(null);
			voObj.setCaseMiniImg(null);
			voObj.setImageHeight(null);
			voObj.setImageWidth(null);
		}
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setShState("1");
		voObj.setIsEnable("1");
		voObj.setDataState("1");
		//插入案例色卡
		service.insert(voObj);
		return buildResponse(modelAndView);
	}


	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public ModelAndView test(String id){
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("id ======="+id);
		List<MaterialCase> materialCaseList = service.listByWidth(id);
		for (MaterialCase materialCase : materialCaseList){
			String filePath = "";
			String orgImageUrl = materialCase.getOrgImageUrl();
			File file = new File(fileForder+orgImageUrl);
			try {
				//获取图片的宽高
				int[] imageSize = FileUtil.getPhotoWidthAndHeight(file);
				int imageWidth = imageSize[0];
				int imageHeight = imageSize[1];
				if (imageWidth <= 3000){
					filePath = FileUtil.saveFile(file,fileForder,true);
					materialCase.setOrgImageUrl(filePath);
				}else {
					imageHeight = imageHeight/(imageWidth/3000);
					imageWidth = 3000;
					String fileNewStr = FileUtil.getImageBase64Str(file);
					fileNewStr = FileUtil.compressImg(fileNewStr,3000);
					filePath = FileUtil.saveBase64Img(fileNewStr,fileForder,true);
					materialCase.setOrgImageUrl(filePath);
				}
				materialCase.setImageHeight(imageHeight);
				materialCase.setImageWidth(imageWidth);
				service.update(materialCase);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return buildResponse(modelAndView);
	}


	/**
	 * 通过网络url取文件
	 *
	 * @param url  网络地址
	 */
	public byte[] getByteData(String url) {
		byte[] byteDate = null;
		try {
			URL pathUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) pathUrl.openConnection();
			//设置超时间为3秒
			conn.setConnectTimeout(5 * 1000);
			//得到输入流
			InputStream inputStream = conn.getInputStream();
			//获取字节数组
			byteDate = readInputStream(inputStream);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return byteDate;
	}

	@RequestMapping(value = "/insertAllNewProduct",method = RequestMethod.POST)
	public ModelAndView  insertAllNewProduct(MaterialCase voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String[] imgArr = voObj.getCaseMiniImg().split(",");
		for(int i = 0;i<imgArr.length;i++){
			MaterialCase materialCase = new MaterialCase();
			materialCase.setMaterialId(voObj.getMaterialId());
			materialCase.setMaterialName(voObj.getMaterialName());
			materialCase.setCaseClsId(voObj.getCaseClsId());
			materialCase.setCaseClsName(voObj.getCaseClsName());
			materialCase.setType(voObj.getType());
			materialCase.setIsHide(voObj.getIsHide());
			materialCase.setIsTop(voObj.getIsTop());
			String imgUrl = imgArr[i];
			if(imgUrl !=null && !imgUrl.equals("")){
				String filePath = "";
				String filePathSecond = "";
				File file = new File(imgUrl);
				try {
					//原图
					filePath = FileUtil.saveFile(file,fileForder,true);
					materialCase.setOrgImageUrl(filePath);
					//压缩 ==> 展示图
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					materialCase.setShowImageUrl(filePath);
					//二次 ==> 缩略图
					String faceOldSencond = FileUtil.getImageBase64Str(file);
					faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
					filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
					materialCase.setCaseMiniImg(filePathSecond);
					materialCase.setImageHeight(FileUtil.getPhotoHeight(file));
					materialCase.setImageWidth(FileUtil.getPhotoWidth(file));
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				materialCase.setOrgImageUrl(null);
				materialCase.setShowImageUrl(null);
				materialCase.setCaseMiniImg(null);
				materialCase.setImageWidth(null);
				materialCase.setImageHeight(null);
			}
			materialCase.setCreateTime(new Date());
			materialCase.setCreateUserId(user.getUserId());
			materialCase.setDataState("1");
			materialCase.setShState("1");
			materialCase.setIsEnable("1");
			service.insertAll(materialCase);
			//审核表 重新审核
			SysCheck sysCheck = new SysCheck();
			sysCheck.setCreateTime(new Date());
			sysCheck.setCreateUserId(user.getUserId());
			sysCheck.setCreateUserName(user.getUserName());
			sysCheck.setDataState("1");
			sysCheck.setServiceDes("案例色卡信息审核");
			sysCheck.setServiceId(materialCase.getId());
			//verifyOperateServiceConstant
			sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_SERVICE);
			sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_PAGE);
			sysCheck.setShState("0");
			sysCheckService.insert(sysCheck);
		}
		return buildResponse(modelAndView);
	}



	@RequestMapping(value = "/insertAllNewProductColor",method = RequestMethod.POST)
	public ModelAndView  insertAllNewProductColor(MaterialCase voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String[] imgArr = voObj.getCaseMiniImg().split(",");
		for(int i = 0;i<imgArr.length;i++){
			MaterialCase materialCase = new MaterialCase();
			materialCase.setMaterialId(voObj.getMaterialId());
			materialCase.setMaterialName(voObj.getMaterialName());
			materialCase.setCaseClsId(voObj.getCaseClsId());
			materialCase.setCaseClsName(voObj.getCaseClsName());
			materialCase.setType(voObj.getType());
			materialCase.setIsHide(voObj.getIsHide());
			materialCase.setIsTop(voObj.getIsTop());
			String imgUrl = imgArr[i];
			if(imgUrl !=null && !imgUrl.equals("")){
				String filePath = "";
				String filePathSecond = "";
				File file = new File(imgUrl);
				try {
					//原图
					filePath = FileUtil.saveFile(file,fileForder,true);
					materialCase.setOrgImageUrl(filePath);
					//压缩 ==> 展示图
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					materialCase.setShowImageUrl(filePath);
					//二次 ==> 缩略图
					String faceOldSencond = FileUtil.getImageBase64Str(file);
					faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
					filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
					materialCase.setCaseMiniImg(filePathSecond);
					materialCase.setImageHeight(FileUtil.getPhotoHeight(file));
					materialCase.setImageWidth(FileUtil.getPhotoWidth(file));
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				materialCase.setOrgImageUrl(null);
				materialCase.setShowImageUrl(null);
				materialCase.setCaseMiniImg(null);
				materialCase.setImageHeight(null);
				materialCase.setImageWidth(null);
			}
			materialCase.setCreateTime(new Date());
			materialCase.setCreateUserId(user.getUserId());
			materialCase.setDataState("1");
			materialCase.setShState("1");
			materialCase.setIsEnable("1");
			service.insertAllColor(materialCase);
			//审核表 重新审核
			SysCheck sysCheck = new SysCheck();
			sysCheck.setCreateTime(new Date());
			sysCheck.setCreateUserId(user.getUserId());
			sysCheck.setCreateUserName(user.getUserName());
			sysCheck.setDataState("1");
			sysCheck.setServiceDes("案例色卡信息审核");
			sysCheck.setServiceId(materialCase.getId());
			//verifyOperateServiceConstant
			sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_SERVICE);
			sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_PAGE);
			sysCheck.setShState("0");
			sysCheckService.insert(sysCheck);
		}
		return buildResponse(modelAndView);
	}

	@RequestMapping(value = "/insertAllNew",method = RequestMethod.POST)
	public ModelAndView  insertAllNew(MaterialCase voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String[] imgArr = voObj.getCaseMiniImg().split(",");
		for(int i = 0;i<imgArr.length;i++){
			MaterialCase materialCase = new MaterialCase();
			materialCase.setMaterialId(voObj.getMaterialId());
			materialCase.setMaterialName(voObj.getMaterialName());
			materialCase.setCaseClsId(voObj.getCaseClsId());
			materialCase.setCaseClsName(voObj.getCaseClsName());
			materialCase.setType(voObj.getType());
			materialCase.setIsHide(voObj.getIsHide());
			materialCase.setIsTop(voObj.getIsTop());
			String imgUrl = imgArr[i];
			if(imgUrl !=null && !imgUrl.equals("")){
				String filePath = "";
				String filePathSecond = "";
				File file = new File(imgUrl);
				try {
					//原图
					filePath = FileUtil.saveFile(file,fileForder,true);
					materialCase.setOrgImageUrl(filePath);
					//压缩 ==> 展示图
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					materialCase.setShowImageUrl(filePath);
					//二次 ==> 缩略图
					String faceOldSencond = FileUtil.getImageBase64Str(file);
					faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
					filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
					materialCase.setCaseMiniImg(filePathSecond);
					materialCase.setImageHeight(FileUtil.getPhotoHeight(file));
					materialCase.setImageWidth(FileUtil.getPhotoWidth(file));
					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				materialCase.setOrgImageUrl(null);
				materialCase.setShowImageUrl(null);
				materialCase.setCaseMiniImg(null);
				materialCase.setImageWidth(null);
				materialCase.setImageHeight(null);
			}
			materialCase.setCreateTime(new Date());
			materialCase.setCreateUserId(user.getUserId());
			materialCase.setDataState("1");
			materialCase.setShState("1");
			materialCase.setIsEnable("1");
			service.insertAll(materialCase);
		}
		return buildResponse(modelAndView);
	}

	@RequestMapping(value = "/insertAllNewColor",method = RequestMethod.POST)
	public ModelAndView  insertAllNewColor(MaterialCase voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String[] imgArr = voObj.getCaseMiniImg().split(",");
		for(int i = 0;i<imgArr.length;i++){
			MaterialCase materialCase = new MaterialCase();
			materialCase.setMaterialId(voObj.getMaterialId());
			materialCase.setMaterialName(voObj.getMaterialName());
			materialCase.setCaseClsId(voObj.getCaseClsId());
			materialCase.setCaseClsName(voObj.getCaseClsName());
			materialCase.setType(voObj.getType());
			materialCase.setIsHide(voObj.getIsHide());
			materialCase.setIsTop(voObj.getIsTop());
			String imgUrl = imgArr[i];
			if(imgUrl !=null && !imgUrl.equals("")){
				String filePath = "";
				String filePathSecond = "";
				File file = new File(imgUrl);
				try {
					//原图
					filePath = FileUtil.saveFile(file,fileForder,true);
					materialCase.setOrgImageUrl(filePath);
					//压缩 ==> 展示图
					String faceOld = FileUtil.getImageBase64Str(file);
					faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
					filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
					materialCase.setShowImageUrl(filePath);
					//二次 ==> 缩略图
					String faceOldSencond = FileUtil.getImageBase64Str(file);
					faceOldSencond = FileUtil.compressImg(faceOldSencond,app_faceaicls_imgcompresswidth);
					filePathSecond = FileUtil.saveBase64Img(faceOldSencond,fileForder,true);
					materialCase.setCaseMiniImg(filePathSecond);
					//获取图片的宽高
					int[] imageSize = FileUtil.getPhotoWidthAndHeight(file);
					materialCase.setImageWidth(imageSize[0]);
					materialCase.setImageHeight(imageSize[1]);

					//删除临时文件
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				materialCase.setOrgImageUrl(null);
				materialCase.setShowImageUrl(null);
				materialCase.setCaseMiniImg(null);
				materialCase.setImageHeight(null);
				materialCase.setImageWidth(null);
			}
			materialCase.setCreateTime(new Date());
			materialCase.setCreateUserId(user.getUserId());
			materialCase.setDataState("1");
			materialCase.setShState("1");
			materialCase.setIsEnable("1");
			service.insertAll(materialCase);
		}
		return buildResponse(modelAndView);
	}

	@RequestMapping(value = "/insertNewMater",method = RequestMethod.POST)
	public ModelAndView insertNewMater(MaterialCase voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String imageUrl= voObj.getCaseMiniImg();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			File file = new File(imageUrl);
			try {
				//压缩
				String faceOld = FileUtil.getImageBase64Str(file);
				faceOld = FileUtil.compressImg(faceOld,app_faceai_imgcompresswidth);
				filePath = FileUtil.saveBase64Img(faceOld,fileForder,true);
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			voObj.setCaseMiniImg(filePath);
		}else {
			voObj.setCaseMiniImg(null);
		}
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setShState("1");
		voObj.setIsEnable("1");
		voObj.setDataState("1");
		//插入案例色卡
		service.insert(voObj);
		return buildResponse(modelAndView,voObj.getId());
	}

	/**
	 * 厂商新增
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/insertProductCase",method = RequestMethod.POST)
	public ModelAndView insertProductCase(MaterialCase voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String imageUrl= voObj.getCaseMiniImg();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			String filePathSecond = "";
			File file = new File(imageUrl);
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
				voObj.setImageHeight(FileUtil.getPhotoHeight(file));
				voObj.setImageWidth(FileUtil.getPhotoWidth(file));
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			voObj.setOrgImageUrl(null);
			voObj.setShowImageUrl(null);
			voObj.setCaseMiniImg(null);
			voObj.setImageHeight(null);
			voObj.setImageWidth(null);
		}
		voObj.setCreateTime(new Date());
		voObj.setCreateUserId(user.getUserId());
		voObj.setCreateUserName(user.getUserName());
		voObj.setShState("0");
		voObj.setIsEnable("0");
		voObj.setDataState("1");
		//插入案例色卡
		service.insert(voObj);
		//审核表 重新审核
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceDes("案例色卡信息审核");
		sysCheck.setServiceId(voObj.getId());
		//verifyOperateServiceConstant
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
	}



	@RequestMapping(value = "/updateNew",method = RequestMethod.POST)
	public ModelAndView updateNew(MaterialCase voObj){
		ModelAndView modelAndView = new ModelAndView();

		String imageUrl= voObj.getCaseMiniImg();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			String filePathSecond = "";
			File file = new File(imageUrl);
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
				voObj.setImageHeight(FileUtil.getPhotoHeight(file));
				voObj.setImageWidth(FileUtil.getPhotoWidth(file));
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			voObj.setCaseMiniImg(null);
			voObj.setShowImageUrl(null);
			voObj.setCaseMiniImg(null);
			voObj.setImageHeight(null);
			voObj.setImageWidth(null);
		}
		//插入案例色卡
		service.update(voObj);
		return buildResponse(modelAndView);
	}

	/**
	 * 厂商 修改
	 * @param voObj
	 * @return
	 */
	@RequestMapping(value = "/updateNewProduct",method = RequestMethod.POST)
	public ModelAndView updateNewProduct(MaterialCase voObj){
		ModelAndView modelAndView = new ModelAndView();
		User user = getLoginUser();
		String imageUrl= voObj.getCaseMiniImg();
		if(imageUrl !=null && !imageUrl.equals("")){
			String filePath = "";
			String filePathSecond = "";
			File file = new File(imageUrl);
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
				voObj.setImageHeight(FileUtil.getPhotoHeight(file));
				voObj.setImageWidth(FileUtil.getPhotoWidth(file));
				//删除临时文件
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			voObj.setCaseMiniImg(null);
			voObj.setShowImageUrl(null);
			voObj.setCaseMiniImg(null);
			voObj.setImageWidth(null);
			voObj.setImageHeight(null);
		}
		//插入案例色卡
		voObj.setShState("0");
		service.update(voObj);
		//审核表 重新审核
		SysCheck sysCheck = new SysCheck();
		sysCheck.setCreateTime(new Date());
		sysCheck.setCreateUserId(user.getUserId());
		sysCheck.setCreateUserName(user.getUserName());
		sysCheck.setDataState("1");
		sysCheck.setServiceDes("案例色卡信息审核");
		sysCheck.setServiceId(voObj.getId());
		//verifyOperateServiceConstant
		sysCheck.setServiceType(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_SERVICE);
		sysCheck.setServiceHtml(VerifyOperateServiceConstant.VERIFY_MATERIAL_CASE_PAGE);
		sysCheck.setShState("0");
		sysCheckService.insert(sysCheck);
		return buildResponse(modelAndView);
	}
}
