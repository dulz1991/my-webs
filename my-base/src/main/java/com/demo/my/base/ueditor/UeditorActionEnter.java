package com.demo.my.base.ueditor;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.ConfigManager;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.demo.my.base.service.file.UeditorImgUploadService;
import com.demo.my.base.util.SpringContextUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class UeditorActionEnter extends ActionEnter {
	
	private static Log logger = LogFactory.getLog(UeditorActionEnter.class);

	private HttpServletRequest request = null;
    private String rootPath = null;
    private String contextPath = null;
    private String actionType = null;
    private ConfigManager configManager = null;
    
    public UeditorActionEnter(HttpServletRequest request, String rootPath) {
        super(request, rootPath);
        this.request = request;
        this.rootPath = rootPath;
        this.actionType = request.getParameter("action");
        this.contextPath = request.getContextPath();
        this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request.getRequestURI());
    }    
    
    @Override    
    public String invoke() {
        if (this.actionType != null && ActionMap.mapping.containsKey(this.actionType)) {
            if (this.configManager != null && this.configManager.valid()) {
                State state = null;
                String result = "";
                int actionCode = ActionMap.getType(this.actionType);
                Map<String, Object> conf = null;
                switch (actionCode) { 
                   case 0: 
                       return this.configManager.getAllConfig().toString();
                    case 1:
                    case 2:
                    case 3:
                    case 4: //图片上传
                        conf = this.configManager.getConfig(actionCode);
                        //注意再这里修改rootPath和savePath，上传的实际路径为rootPath＋savePath
                        /*conf.put("rootPath", uploadPath);*/
                        /*conf.put("savePath", "/upload/" + conf.get("savePath"));*/
                        /*state = (new Uploader(this.request, conf)).doExec();*/
                        try {
                        	UeditorImgUploadService imgUploadService = SpringContextUtil.getBean("imgUploadService");
                        	Map<String, Object> retMap = null;
                        	String imgType = request.getParameter("imgType");
                        	if(imgType.equals("blog")){
                        		retMap = imgUploadService.uploadBlogImage(request);	
                        	} else if(imgType.equals("code")){
                        		retMap = imgUploadService.uploadCodeImage(request);
                        	} else if(imgType.equals("user")){
                        		retMap = imgUploadService.uploadUserImage(request);
                        	} else if(imgType.equals("vue")){
                        		retMap = imgUploadService.uploadVueImage(request);
                        	}
                        	result = retMap.get("result")==null?"":retMap.get("result").toString();
						} catch (Exception e) {
							logger.error("UeditorActionEnter.invoke ex:"+e.getCause());
						}
                        break;
                    case 5:
                        conf = this.configManager.getConfig(actionCode);
                        //注意再这里修改rootPath和savePath，上传的实际路径为rootPath＋savePath
                        conf.put("rootPath", "/Users/mafengli/");
                        conf.put("savePath", "/upload" + conf.get("savePath"));
                        String[] list = this.request.getParameterValues((String) conf.get("fieldName"));
                        state = (new ImageHunter(conf)).capture(list);
                        result = state.toString();
                        break;
                    case 6:
                    case 7:
                        conf = this.configManager.getConfig(actionCode);
                       //注意再这里修改rootPath和savePath，上传的实际路径为rootPath＋savePath
                        conf.put("rootPath", "/Users/mafengli/");
                        conf.put("savePath", "/upload" + conf.get("savePath"));
                        conf.put("dir", "/upload" + conf.get("dir"));
                        int start = this.getStartIndex();
                        state = (new FileManager(conf)).listFile(start);
                        result = state.toString();
                        break;
                }
                return result;
            } else {
                return (new BaseState(false, 102)).toJSONString();
            }
        } else {
            return (new BaseState(false, 101)).toJSONString();
        }
    }
    
    private String getStateResult(String fileName, String type, String url) {
    	String result = "{\"state\":\"SUCCESS\",\"original\":\"\",\"size\":\"\",\"title\":\""+fileName+"\",\"type\":\""+type+"\",\"url\":\""+url+"\"}";
    	return result;
	}
}