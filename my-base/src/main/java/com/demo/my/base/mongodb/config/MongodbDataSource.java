package com.demo.my.base.mongodb.config;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MongodbDataSource implements MongodbCollection {

	private String url;
	private int port;
	private String dbname;
	
	DB mongoDatabase;
	
	@SuppressWarnings("deprecation")
	@Override
	public DBCollection getDbCollection(String collectionName) {
		if(mongoDatabase==null){
			// 连接到 mongodb 服务
			Mongo mongoClient = new Mongo(this.getUrl(), this.getPort());
	        // 连接到数据库
	        mongoDatabase = mongoClient.getDB(this.getDbname());
	        System.out.println("Connect to database successfully!");
		}
		return mongoDatabase.getCollection(collectionName);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
