package com.demo.my.base.mongodb.service;

import java.util.Map;

import com.demo.my.base.mongodb.config.MongodbDataSource;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;

public class MongodbService {

	private MongodbDataSource mongodbDataSource;
	
	public WriteResult insert(String collectionName, Map<String, Object> map) {
		BasicDBObject document = new BasicDBObject(); 
		document.put("title", "title1");
		document.put("tag", "1");
		return mongodbDataSource.getDbCollection(collectionName).insert(document);
	}
	
	public WriteResult delete(String collectionName) {
		BasicDBObject document = new BasicDBObject(); 
		document.put("tag", "1");
		return mongodbDataSource.getDbCollection(collectionName).remove(document);
	}

	public MongodbDataSource getMongodbDataSource() {
		return mongodbDataSource;
	}

	public void setMongodbDataSource(MongodbDataSource mongodbDataSource) {
		this.mongodbDataSource = mongodbDataSource;
	}
	
}
