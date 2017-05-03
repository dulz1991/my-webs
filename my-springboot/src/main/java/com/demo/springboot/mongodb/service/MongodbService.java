package com.demo.springboot.mongodb.service;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.demo.springboot.mongodb.config.MongodbDataSource;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;

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
	
	public MongoCollection<Document> getByCollection(String collectionName) {
		BasicDBObject document = new BasicDBObject(); 
		document.put("tag", "1");
		
		MongoCollection<Document> collection = (MongoCollection<Document>) mongodbDataSource.getDbCollection("test");
		
		return collection;
	}
	
	
	

	public MongodbDataSource getMongodbDataSource() {
		return mongodbDataSource;
	}

	public void setMongodbDataSource(MongodbDataSource mongodbDataSource) {
		this.mongodbDataSource = mongodbDataSource;
	}
	
}
