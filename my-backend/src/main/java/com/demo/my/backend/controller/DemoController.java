package com.demo.my.backend.controller;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Demo;
import com.demo.my.base.model.DemoMenu;
import com.demo.my.base.util.DateUtil;
import com.demo.my.base.util.Page;
import com.demo.my.base.util.PropertiesUtil;
import com.demo.my.base.util.ZipUtil;

@Controller
@RequestMapping("/backend/demo")
public class DemoController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping(value="/list")
	public ModelAndView index(Long demoMenuId) {
		ModelAndView model = new ModelAndView("demo/demo_list");
		
		//查询菜单
		model.addObject("demoMenuList", JSONArray.toJSON(demoMenuService.getForTree()));
		
		if(demoMenuId!=null){
			model.addObject("demoMenuId", demoMenuId);
		}
		
		return model;
	}
	
	@RequestMapping(value="/edit")
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("demo/demo_edit");
		
		//查询demo
		if(id!=null){
			Demo entity = demoService.getById(id);
			model.addObject("entity", entity);
		} else {
			model.addObject("entity", new Demo());
		}
		
		//查询菜单
		/*List<DemoMenu> demoMenuList = demoMenuService.getList(null);
		model.addObject("demoMenuList", demoMenuList);*/
		
		model.addObject("demoMenuList", JSONArray.toJSON(demoMenuService.getForTree()));
		
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList")
	public Map<String, Object> getList() {
		Map<String, Object> parmMap = this.getParmMap();
		Page<Map<String, Object>> page = demoService.getPage("DemoMapper.getMapListByParm", parmMap);

		Map<String, Object> resMap = responseOK();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, Object> saveDemo(Demo demo, 
			@RequestParam(value = "attachFile", required = false) MultipartFile attachFile) throws Exception {
		if(StringUtils.isBlank(demo.getTitle())){
			return responseGeneralError("请输入标题");
		}
		if(demo.getMenuId()==null){
			return responseGeneralError("请选择菜单");
		}
		if(demo.getId()==null && attachFile==null){
			return responseGeneralError("请上传demo包");
		}
		if(StringUtils.isBlank(demo.getUrl())){
			return responseGeneralError("请输入访问的文件名");
		}
		if(StringUtils.isBlank(demo.getPicPath())){
			return responseGeneralError("请输入预览的图片");
		}
		/*if(StringUtils.isBlank(demo.getResourcePath())){
			return responseGeneralError("请输入资源包名称");
		}*/
		if(StringUtils.isBlank(demo.getDescription())){
			return responseGeneralError("请输入描述");
		}
		
		/*MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    MultipartFile file = null;  
	    file = multipartRequest.getFile("attachFile");// ��ȡ�ϴ��ļ���
		 */	    
		if(attachFile!=null){
			//保存路径
		    DemoMenu demoMenu = demoMenuService.getById(demo.getMenuId());
		    String basePath = demoMenu.getName();
			String folder = attachFile.getOriginalFilename().substring(0, attachFile.getOriginalFilename().length()-4);
			//上传路径
	    	String uploadPath = PropertiesUtil.get("file_demo_path"); 
	    	uploadPath=uploadPath.replace("{{basePath}}", basePath).replace("{{folder}}", folder);
	    	//上传
		    fileUploadService.uploadAttachFile(attachFile, uploadPath);
		    //解压
		    String zipFilePath = uploadPath+attachFile.getOriginalFilename(); 
		    ZipUtil.unZip(zipFilePath, uploadPath);
		    //图片地址
		    String picPath = PropertiesUtil.get("file_demo_pic_path"); 
		    picPath=picPath.replace("{{basePath}}", basePath).replace("{{folder}}", folder).replace("{{name}}", folder).replace("{{img}}", demo.getPicPath());
		    demo.setPicPath(picPath);
		    //资源路径
		    String resourcePath = PropertiesUtil.get("file_demo_resource_path");
		    resourcePath=uploadPath+attachFile.getOriginalFilename();
		    demo.setResourcePath(resourcePath);
		    //在线预览路径
		    String urlPath = PropertiesUtil.get("file_demo_url_path");
		    urlPath=urlPath.replace("{{basePath}}", basePath).replace("{{folder}}", folder).replace("{{name}}", folder).replace("{{html}}", demo.getUrl());
		    demo.setUrl(urlPath);
	    }
	    
		if (demo.getId() != null) {
			/*Demo d = demoService.getById(demo.getId());
			if(StringUtils.isNotBlank(d.getResourcePath())){
				String delPath = d.getResourcePath().substring(0, d.getResourcePath().lastIndexOf("\\"));
				File f = new File(delPath);
				f.delete();	
			}*/
			demoService.update(demo);
		} else {
			demoService.insert(demo);
		}
		
		Map<String, Object> resMap =  responseOK("");
		resMap.put("id", demo.getId());
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save1")
	public Map<String, Object> saveDemo(Demo demo) throws Exception {
		if(StringUtils.isBlank(demo.getTitle())){
			return responseGeneralError(ErrorConstant.ERROR_EMPTY_TITLE);
		}
		if(StringUtils.isBlank(demo.getDescription())){
			return responseGeneralError(ErrorConstant.ERROR_EMPTY_DESCRIPTIPN);
		}
		if(demo.getMenuId() == null||demo.getMenuId()<0L){
			return responseGeneralError(ErrorConstant.ERROR_EMPTY_MENU_NAME);
		}
		
		if(demo.getId()==null){
			String resourceName = request.getParameter("resourceName");
			if(StringUtils.isBlank(resourceName)){
				return responseGeneralError("资源文件名不能为空");
			}
			String resourceNameOnly = resourceName.substring(0, resourceName.lastIndexOf("."));
			
			String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1);
	    	String uploadPath = PropertiesUtil.get("file_demo_path"); 
	    	uploadPath=uploadPath.replace("{{yyyymmdd}}", dateStr);
	    	File dir = new File(uploadPath);
	        if(!dir.exists()){  
	        	dir.mkdirs();  
	        }
	        
		    //图片地址
		    String picPath = PropertiesUtil.get("file_demo_pic_path"); 
		    picPath=picPath.replace("{{name}}", resourceNameOnly).replace("{{yyyymmdd}}", dateStr);
		    demo.setPicPath(picPath);
		    //资源路径
		    String resourcePath = PropertiesUtil.get("file_demo_resource_path");
		    resourcePath=resourcePath.replace("{{yyyymmdd}}", dateStr).replace("{{name}}", resourceName);
		    demo.setResourcePath(resourcePath);
		    //在线预览路径
		    String urlPath = PropertiesUtil.get("file_demo_url_path");
		    urlPath=urlPath.replace("{{name}}", resourceNameOnly).replace("{{yyyymmdd}}", dateStr);
		    demo.setUrl(urlPath);
		}else {
			if(StringUtils.isBlank(demo.getUrl())){
				return responseGeneralError("访问的文件名不能为空");
			}
			if(StringUtils.isBlank(demo.getPicPath())){
				return responseGeneralError("图片文件名不能为空");
			}
			if(StringUtils.isBlank(demo.getResourcePath())){
				return responseGeneralError("资源文件名不能为空");
			}
			
		}
		
		if (demo.getId() != null) {
			demoService.update(demo);
		} else {
			demoService.insert(demo);
		}
		
		Map<String, Object> resMap =  responseOK("");
		resMap.put("id", demo.getId());
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doDelete")
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "删除的记录不存在");
		}
		int i = demoService.delete(id);
		if(i==0){
			return responseError(-1, "删除失败");
		}
		return responseOK("删除成功");
	}

}
