package com.demo.my.backend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.demo.my.backend.common.BaseBackendController;
import com.demo.my.backend.service.file.FileUploadService;
import com.demo.my.base.service.DemoService;
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
	
	@Autowired
	private DemoService demoService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("demo/demo_list");
		
		//查询菜单
		List<DemoMenu> demoMenuList = demoService.excute("DemoMenuMapper.getBeanListByParm", null);
		model.addObject("demoMenuList", demoMenuList);
		
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
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
		List<DemoMenu> demoMenuList = demoService.excute("DemoMenuMapper.getBeanListByParm", null);
		model.addObject("demoMenuList", demoMenuList);
		
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(Demo demo,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Page<Map<String, Object>> page = demoService.getMapListByParm(demo, pageNo, pageSize, "");

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("list", page.getList());
		resMap.put("page", page);
		
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, Object> saveDemo(Demo demo, 
			@RequestParam(value = "attachFile", required = false) MultipartFile attachFile) throws Exception {
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
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
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
