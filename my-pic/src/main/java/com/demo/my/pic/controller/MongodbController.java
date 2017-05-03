package com.demo.my.pic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.my.base.mongodb.service.MongodbService;
import com.mongodb.WriteResult;

@Controller
@RequestMapping("/mongodb")
public class MongodbController extends BaseController {
	
	@Autowired
	private MongodbService mongodbService;
	
	@ResponseBody
	@RequestMapping(value = "/insert", method=RequestMethod.GET)
	public Object insert() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "title1");
		map.put("tag", 1);
		return mongodbService.insert("test", map);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public WriteResult delete() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "title1");
		map.put("tag", 1);
		WriteResult r = mongodbService.delete("test");
		return r;
	}

}
