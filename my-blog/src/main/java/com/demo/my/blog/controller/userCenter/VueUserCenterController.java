package com.demo.my.blog.controller.userCenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.my.base.common.ErrorConstant;
import com.demo.my.base.model.Collection;
import com.demo.my.base.model.Discovery;
import com.demo.my.base.model.User;
import com.demo.my.base.service.CollectionService;
import com.demo.my.base.service.CommentService;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.service.file.ImageFileService;
import com.demo.my.base.util.MD5Util;
import com.demo.my.base.util.Page;
import com.demo.my.blog.controller.common.BaseController;

@RestController
@RequestMapping(value="user")
public class VueUserCenterController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DiscoveryService discoveryService;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CollectionService collectionService;
	
	/**
	 * 首页
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="index", method = RequestMethod.GET)
	public Map<String, Object> index() {
		Map<String, Object> resMap = this.responseOK("");
		
		//用户信息
		User user = this.getCurrentUser();
		if(user==null){
			return this.responseError(ErrorConstant.ERROR_400, "未登录");
		}
		resMap.put("avatar", user.getAvatar());
		resMap.put("username", user.getUsername());
		
		//用户发帖数
		Discovery discovery = new Discovery();
		discovery.setUserId(user.getId());
		int count = discoveryService.countByParm(discovery);
		resMap.put("myPostCount", count);
		
		//用户评论数
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("fromId", user.getId());
		int myCommentCount = discoveryService.getMyCommentDiscoveryCount(parm);
		resMap.put("myCommentCount", myCommentCount);
		
		//用户收藏的
		Collection collection = new Collection();
		collection.setUserId(user.getId());
		int myCollectionCount = collectionService.countByParm(collection);
		resMap.put("myCollectionCount", myCollectionCount);
		
		return resMap;
	}
	
	/**
	 *  我发布的
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="myPost", method = RequestMethod.GET)
	public Map<String, Object> myPost(int pageNo) {
		Map<String, Object> resMap = this.responseOK("");
		int pageSize = 10;
		
		Long userId = this.getCurrentUserId();
		Discovery discovery = new Discovery();
		discovery.setUserId(userId);
		Page<Discovery> page = discoveryService.getBeanListByParm(discovery, pageNo, pageSize, "id desc");
		String contentStr = "";
		for(Discovery d : page.getList()){
			contentStr = d.getContent();
			if(contentStr.length()>80){
				d.setContent(d.getContent().substring(0, 80));
			}
		}
		resMap.put("page", page);
		
		return resMap;
	}
	
	/**
	 * 我评论的
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="myComment", method = RequestMethod.GET)
	public Map<String, Object> myComment(int pageNo) {
		Map<String, Object> resMap = this.responseOK("");
		int pageSize = 10;
		
		Long userId = this.getCurrentUserId();
		
		Page<Map<String, Object>> page = discoveryService.getMyCommentDiscovery(userId, pageNo, pageSize, "c.CREATE_TIME desc");
		String contentStr = "";
		for(Map<String, Object> d : page.getList()){
			contentStr = d.get("content")==null?"":d.get("content").toString();
			if(contentStr.length()>80){
				d.put("content", contentStr.substring(0, 80));
			}
		}
		resMap.put("page", page);
		
		return resMap;
	}
	
	/**
	 * 我收藏的
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="myCollection", method = RequestMethod.GET)
	public Map<String, Object> myCollection(Long colId, String type) {
		Map<String, Object> resMap = this.responseOK("");
		int pageSize = 10;
		
		Long userId = this.getCurrentUserId();
		List<Map<String, Object>> list = collectionService.getMyCollectionForApp(userId, colId, type, "c.id desc");
		String contentStr = "";
		if(list!=null && list.size()>0){
			for(Map<String, Object> d : list){
				contentStr = d.get("content")==null?"":d.get("content").toString();
				if(contentStr.length()>80){
					d.put("content", contentStr.substring(0, 80));
				}
			}
		}
		resMap.put("list", list);
		
		return resMap;
	}
	
	/**
	 * 修改密码
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="doChangepwd", method = RequestMethod.POST)
	public Map<String, Object> doChangepwd(String oldPwd, String newPwd) {
		User user = getCurrentUser();
		user = userService.getById(user.getId());
		MD5Util md5 = new MD5Util(MD5Util.default_salt, "MD5");
		if(!user.getPassword().equals(md5.encode(oldPwd))){
			return responseError(ErrorConstant.ERROR_GENERAL, "修改失败：旧密码错误");
		}
		
		newPwd = md5.encode(newPwd);
		user.setPassword(newPwd);
		userService.update(user);
		
		return this.responseOK("修改成功");
	}

	@ResponseBody
	@RequestMapping(value="uploadAvatar", method = RequestMethod.POST)
	public Map<String, Object> uploadAvatar(@RequestParam(name = "attachFile", required = false) MultipartFile attachFile) {
		if(attachFile==null){
			return this.responseError(ErrorConstant.ERROR_500, "请选择图片");
		}
		
		User user = getCurrentUser();
		if(attachFile!=null){
			Map<String, Object> resultMap = imageFileService.uploadUserImage(attachFile);
		    String avatarUrl = resultMap.get("url")==null?"":resultMap.get("url").toString(); 
		    if(StringUtils.isNotBlank(avatarUrl)){
		    	user.setAvatar(avatarUrl);
			}
	    }
		userService.update(user);
		
		return this.responseOK("上传成功");
	}
}
