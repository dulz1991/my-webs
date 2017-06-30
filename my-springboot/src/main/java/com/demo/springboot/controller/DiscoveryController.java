package com.demo.springboot.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import com.demo.my.base.model.Comment;
import com.demo.my.base.model.Discovery;
import com.demo.my.base.service.CollectionService;
import com.demo.my.base.service.CommentService;
import com.demo.my.base.service.DiscoveryService;
import com.demo.my.base.service.UserService;
import com.demo.my.base.util.Page;
import com.demo.springboot.service.file.ImageFileService;

@RestController
public class DiscoveryController extends BaseController {
	
	@Autowired
	private DiscoveryService discoveryService;
	@Autowired
	private UserService userService;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CollectionService collectionService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping(value = "/discoveryList", method=RequestMethod.GET)
	public Map<String, Object> discoveryList(@RequestParam(name="pageNo", defaultValue="1") int pageNo, Long discoveryId, String type) {
		int pageSize = 10;
		Discovery discovery = new Discovery();
		discovery.setStatus(1);
		
		if(StringUtils.isBlank(type)){
			Page<Map<String, Object>> page = discoveryService.getMapListByParm(discovery, pageNo, pageSize, "d.ID desc");
			resMap.put("list", page.getList());
		} else{
			List<Map<String, Object>> list = discoveryService.getMapListForDrag(discoveryId, pageSize, type);
			if(list.isEmpty()){
				return responseError(ErrorConstant.ERROR_GENERAL, "没有更多数据");
			}
			Collections.sort(list, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					Long o1Id = Long.valueOf(o1.get("id").toString());
					Long o2Id = Long.valueOf(o2.get("id").toString());
					return -(o1Id).compareTo(o2Id);
				}
			});
			resMap.put("list", list);
		}
		
		resMap.put("pageSize", pageSize);
		return resMap;
	}
	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/discoveryDetail", method=RequestMethod.GET)
	public Map<String, Object> discoveryDetail(Long id) {
		if(id==null){
			return responseError(ErrorConstant.ERROR_404, "页面没找到");
		}
		Map<String, Object> discovery = discoveryService.getMapById(id);
		if(discovery==null || !discovery.get("status").equals(1)){
			return responseError(ErrorConstant.ERROR_404, "页面没找到");
		}
		resMap.put("detail", discovery);
		
		Discovery entity = new Discovery();
		entity.setId(id);
		String clickNum = discovery.get("clickNum")==null?"0":discovery.get("clickNum").toString();
		entity.setClickNum(Integer.valueOf(clickNum)+1);
		discoveryService.update(entity);
		
		//是否登录 如果登陆 判断是否已关注
		Long userId = this.getUserIdFromCookie();
		if(userId!=null){
			Collection collection = new Collection();
			collection.setUserId(userId);
			collection.setDiscoveryId(id);
			int count = collectionService.countByParm(collection);
			if (count>0) {
				resMap.put("hasCollected", true);
			}
		}
		
		return resMap;
	}
	
	/**
	 * 发表图文
	 * @param discovery
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/auth/post", method=RequestMethod.POST)
	public Map<String, Object> doPost(Discovery discovery,
			@RequestParam(name = "attachFile",value="attachFile", required = false) MultipartFile attachFile) {
		
		if(StringUtils.isBlank(discovery.getTitle())){
			return responseError(ErrorConstant.ERROR_GENERAL, "标题不能为空");
		}
		if(StringUtils.isBlank(discovery.getContent())){
			return responseError(ErrorConstant.ERROR_GENERAL, "内容不能为空");
		}
		Long userId = this.getUserIdFromCookie();
		if(userId==null){
			return responseError(ErrorConstant.ERROR_400, "请先登录");
		}
		
		if(attachFile!=null){
			Map<String, Object> resultMap = imageFileService.uploadImage(attachFile);
		    String fileName = resultMap.get("fileName")==null?"":resultMap.get("fileName").toString(); 
		    if(StringUtils.isNotBlank(fileName)){
		    	discovery.setImgPath(fileName);
			}
	    }
		
		if(discovery.getContent().length()>1000){
			discovery.setContent(discovery.getContent().substring(0, 1000));
		}
		discovery.setCreateTime(new Date());
		discovery.setUpdateTime(new Date());
		discovery.setClickNum(0);
		discovery.setStatus(1);
		discovery.setUserId(userId);
		discoveryService.insert(discovery);
		
		return resMap;
	}
	 
	@ResponseBody
	@RequestMapping(value = "/auth/discovery/doComment", method=RequestMethod.POST)
	public Map<String, Object> doComment(Comment comment) {
		//校验
		if(StringUtils.isBlank(comment.getMessageContent())){
			return responseError(ErrorConstant.ERROR_GENERAL, "评论内容不能为空");
		}
		if(comment.getDiscoveryId()==null){
			return responseError(ErrorConstant.ERROR_GENERAL, "检测到违法操作");
		}
		Discovery discovery = discoveryService.getById(comment.getDiscoveryId());
		if(discovery==null){
			return responseError(ErrorConstant.ERROR_GENERAL, "该文章已删除");
		}
		
		Long userId = this.getUserIdFromCookie();
		if(userId==null){
			return responseError(ErrorConstant.ERROR_400, "请先登录");
		}
		
		comment.setToId(discovery.getUserId());
		comment.setFromId(userId);
		comment.setCreateTime(new Date());
		int count = commentService.insert(comment);
		if (count<1) {
			return responseError(ErrorConstant.ERROR_GENERAL, "服务器异常, 请稍后再试");
		}
		return resMap;
	}
	
}
