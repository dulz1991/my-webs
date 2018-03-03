package com.demo.my.base.mongodb.config;

import com.mongodb.DBCollection;

public interface MongodbCollection {
	
	DBCollection getDbCollection(String collectionName);

	/*<T> DBCollection getDbCollection(String collectionName, Class<T> clazz);*/
}
