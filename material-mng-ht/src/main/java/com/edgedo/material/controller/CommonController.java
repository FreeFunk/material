package com.edgedo.material.controller;


import com.edgedo.common.base.BaseController;
import com.edgedo.common.util.FileUtil;
import com.edgedo.common.util.Guid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/material/common")
public class CommonController extends BaseController{

	@Value("${temFileForder}")
	private String temFileForder;
	@Value("${fileForder}")
	private String fileForder;


	/*
	* 文件上传到临时文件目录
	* */
	@RequestMapping(value = "/uploadTempFile",method = RequestMethod.POST)
	public ModelAndView uploadTempFile(MultipartFile file){
		ModelAndView modelAndView = new ModelAndView();
		String tempFilePath = "";
		try{
			//保存到临时文件目录
			tempFilePath = FileUtil.saveFile(file,temFileForder,true);
		}catch (Exception e){
			e.printStackTrace();
		}
		return buildResponse(modelAndView,temFileForder+tempFilePath);
	}

	/**
	 * 下载文件
	 * @author: ZhangCC
	 * @time: 2020/9/12 11:20
	 */
	@RequestMapping(value = "/downloadFile",method = RequestMethod.POST)
	public ModelAndView downloadFile(MultipartFile file){
		ModelAndView modelAndView = new ModelAndView();

		return buildResponse(modelAndView);
	}

}
