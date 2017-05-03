package com.demo.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.bean.UserProfile;
import com.demo.springboot.mongodb.service.MongodbService;
import com.demo.springboot.service.UserProfileService;
import com.mongodb.client.MongoCollection;

@RestController
public class HomeController extends BaseController {
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	 
	 @Autowired
	 private UserProfileService userProfileService; 
	 @Autowired
	 private MongodbService mongodbService;

	 @RequestMapping("/index/{userId}")
	 @ResponseBody
	 public Map<String, Object> index(@PathVariable Long userId){
		 
		 if(null==userId){
			 return responseError(-1, "userId is null");
		 }
		 UserProfile userProfile = userProfileService.getById(userId);
		 return responseOK("hello:"+userProfile.getUsernick());
	 }
	 
	 @RequestMapping("/mongotest")
	 @ResponseBody
	 public Map<String, Object> mongotest(){
		 Map<String, Object> map = new HashMap<String, Object>();
		 mongodbService.insert("test1", map);
		 
		 MongoCollection<Document> collection = mongodbService.getByCollection("test1");
		 return responseOK("hello:"+collection);
	 }
	
}
