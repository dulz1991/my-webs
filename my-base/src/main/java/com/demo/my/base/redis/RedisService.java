/*package com.demo.my.base.redis;

import java.util.Set;

public class RedisService extends RedisClient {

	*//**
	 * 通过key删除（字节）
	 * 
	 * @param key
	 *//*
	public void del(byte[] key) {
		this.getJedis().del(key);
	}

	*//**
	 * 通过key删除
	 * 
	 * @param key
	 *//*
	public void del(String key) {
		this.getJedis().del(key);
	}

	*//**
	 * 添加key value 并且设置存活时间(byte)
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 *//*
	public void set(byte[] key, byte[] value, int liveTime) {
		this.set(key, value);
		this.getJedis().expire(key, liveTime);
	}

	*//**
	 * 添加key value 并且设置存活时间
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 *//*
	public void set(String key, String value, int liveTime) {
		this.set(key, value);
		this.getJedis().expire(key, liveTime);
	}

	*//**
	 * 添加key value
	 * 
	 * @param key
	 * @param value
	 *//*
	public void set(String key, String value) {
		try {
			this.getJedis().set(key, value);	
		} catch (Exception e) {
			return;
		}
	}

	*//**
	 * 添加key value (字节)(序列化)
	 * 
	 * @param key
	 * @param value
	 *//*
	public void set(byte[] key, byte[] value) {
		try {
			this.getJedis().set(key, value);	
		} catch (Exception e) {
			return;
		}
		
	}

	*//**
	 * 获取redis value (String)
	 * 
	 * @param key
	 * @return
	 *//*
	public String get(String key) {
		try {
			String value = this.getJedis().get(key);
			return value;	
		} catch (Exception e) {
			return null;
		}
		
	}

	*//**
	 * 获取redis value (byte [] )(反序列化)
	 * 
	 * @param key
	 * @return
	 *//*
	public byte[] get(byte[] key) {
		try {
			return this.getJedis().get(key);	
		} catch (Exception e) {
			return null;
		}
		
	}

	*//**
	 * 通过正则匹配keys
	 * 
	 * @param pattern
	 * @return
	 *//*
	public Set<String> keys(String pattern) {
		return this.getJedis().keys(pattern);
	}

	*//**
	 * 检查key是否已经存在
	 * 
	 * @param key
	 * @return
	 *//*
	public boolean exists(String key) {
		return this.getJedis().exists(key);
	}

	*//**
	 * 清空redis 所有数据
	 * 
	 * @return
	 *//*
	public String flushDB() {
		return this.getJedis().flushDB();
	}

	*//**
	 * 查看redis里有多少数据
	 *//*
	public long dbSize() {
		return this.getJedis().dbSize();
	}

	*//**
	 * 检查是否连接成功
	 * 
	 * @return
	 *//*
	public String ping() {
		return this.getJedis().ping();
	}

}
*/