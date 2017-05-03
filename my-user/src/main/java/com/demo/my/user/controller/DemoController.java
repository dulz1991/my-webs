package com.demo.my.user.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.base.bean.Demo;
import com.demo.my.base.bean.DemoMenu;
import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.DemoServiceBean;
import com.demo.my.base.util.DateUtil;
import com.demo.my.base.util.PageUtil;
import com.demo.my.base.converter.BeanConverter;
import com.demo.my.user.service.FileUploadService;
import com.demo.my.base.util.PropertiesUtil;
import com.demo.my.base.util.ZipUtil;

@Controller
@RequestMapping("/auth/demo")
public class DemoController extends BaseController {

	@Resource(name = "demoServiceBean")
	private DemoServiceBean demoService;
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("demo/demo_list");
		List<DemoMenu> menuList = demoService.getBeanListByParm(null, null, KeyConstant.MAPPER_DEMO_MENU);
		mv.addObject("menuList", menuList);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public HashMap<String, Object> list(Demo demo, PageUtil pageUtil)
			throws UnsupportedEncodingException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		pageUtil = new PageUtil(pageUtil.getPageNum(), pageUtil.getPageSize());
		if (StringUtils.isNotBlank(demo.getTitle())) {
			String title = URLDecoder.decode(demo.getTitle(), "utf-8");
			paramMap.put("title", title);
		}
		if (demo.getMenuId() != null && demo.getMenuId() != -1) {
			paramMap.put("menuId", demo.getMenuId());
		}
		List<Demo> list = demoService.getBeanListByParm(pageUtil, paramMap, KeyConstant.MAPPER_DEMO);
		Integer count = demoService.countByParm(paramMap,  KeyConstant.MAPPER_DEMO);

		HashMap<String, Object> resMap = new HashMap<String, Object>();
		pageUtil = new PageUtil(pageUtil.getCurrentPage(), pageUtil.getPageSize(), count);
		resMap.put("list", new BeanConverter().bean2Map(list, null));
		resMap.put("page", pageUtil);

		return resMap;
	}

	@ResponseBody
	@RequestMapping(value = "/doDelete", method = RequestMethod.GET)
	public HashMap<String, Object> doDelete(@RequestParam(value = "id", defaultValue = "0") Long id) {
		Demo demo = demoService.getById(id, KeyConstant.MAPPER_DEMO);
		if (null == demo) {
			return responseError(ErrorConstant.ERROR_404, ErrorConstant.ERROR_NO_RECORD);
		}
		demoService.delete(id, KeyConstant.MAPPER_DEMO);
		return responseOK("ɾ���ɹ�");
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editDemo(String id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("demo/demo_edit");
		List<DemoMenu> menuList = demoService.getBeanListByParm(null, null, KeyConstant.MAPPER_DEMO_MENU);
		mv.addObject("menuList", menuList);
		if (StringUtils.isNotBlank(id)) {
			Demo demo = demoService.getById(Long.parseLong(id), KeyConstant.MAPPER_DEMO);
			mv.addObject("title", "�༭demo");
			mv.addObject("demo", demo);
		} else {
			mv.addObject("title", "����demo");
		}
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, Object> saveDemo(Demo demo, @RequestParam(value = "attachFile", required = false) MultipartFile attachFile, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(StringUtils.isBlank(demo.getTitle())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_TITLE);
		}
		if(StringUtils.isBlank(demo.getDescription())){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_DESCRIPTIPN);
		}
		if(demo.getMenuId() == null||demo.getMenuId()<0L){
			return responseError(ErrorConstant.ERROR_500, ErrorConstant.ERROR_EMPTY_MENU_NAME);
		}
		
		/*MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	    MultipartFile file = null;  
	    file = multipartRequest.getFile("attachFile");// ��ȡ�ϴ��ļ���
*/	    if(attachFile!=null){
	    	String uploadPath = PropertiesUtil.get(PropertiesUtil.FILE_DEMO_PATHE); 
	    	String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATETIME_FORMATE_1); 
		    uploadPath += dateStr + "\\";
		    fileUploadService.uploadAttachFile(attachFile, uploadPath);
		    String zipFilePath = uploadPath+attachFile.getOriginalFilename(); 
		    
		    ZipUtil.unZip(zipFilePath, uploadPath);
		    String picPath = PropertiesUtil.get(PropertiesUtil.FILE_DEMO_PIC_PATH); 
		    
		    demo.setPicPath(picPath+dateStr+"\\"+"demo.png");
		    String resourcePath = PropertiesUtil.get(PropertiesUtil.FILE_DEMO_RESOURCE_PATH); 
		    demo.setResourcePath(resourcePath+dateStr+"\\"+attachFile.getOriginalFilename());
		    String urlPath = PropertiesUtil.get(PropertiesUtil.FILE_DEMO_URL_PATH); 
		    demo.setUrl(urlPath+dateStr+"\\"+"demo.html");
	    }
	    
		demo.setDownloadTimes(0);
		if (demo.getId() != null) {
			/*Demo d = demoService.getById(demo.getId());
			if(StringUtils.isNotBlank(d.getResourcePath())){
				String delPath = d.getResourcePath().substring(0, d.getResourcePath().lastIndexOf("\\"));
				File f = new File(delPath);
				f.delete();	
			}*/
			demoService.update(demo, KeyConstant.MAPPER_DEMO);
		} else {
			demoService.insert(demo, KeyConstant.MAPPER_DEMO);
		}
		
		Map<String, Object> resMap =  responseOK("");
		resMap.put("id", demo.getId());
		return resMap;
	}

}
