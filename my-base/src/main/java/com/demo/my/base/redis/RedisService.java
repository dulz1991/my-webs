/*package com.demo.my.base.redis;

import java.util.Set;

public class RedisService extends RedisClient {

	*//**
	 * ͨ��keyɾ�����ֽڣ�
	 * 
	 * @param key
	 *//*
	public void del(byte[] key) {
		this.getJedis().del(key);
	}

	*//**
	 * ͨ��keyɾ��
	 * 
	 * @param key
	 *//*
	public void del(String key) {
		this.getJedis().del(key);
	}

	*//**
	 * ���key value �������ô��ʱ��(byte)
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
	 * ���key value �������ô��ʱ��
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
	 * ���key value
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
	 * ���key value (�ֽ�)(���л�)
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
	 * ��ȡredis value (String)
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
	 * ��ȡredis value (byte [] )(�����л�)
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
	 * ͨ������ƥ��keys
	 * 
	 * @param pattern
	 * @return
	 *//*
	public Set<String> keys(String pattern) {
		return this.getJedis().keys(pattern);
	}

	*//**
	 * ���key�Ƿ��Ѿ�����
	 * 
	 * @param key
	 * @return
	 *//*
	public boolean exists(String key) {
		return this.getJedis().exists(key);
	}

	*//**
	 * ���redis ��������
	 * 
	 * @return
	 *//*
	public String flushDB() {
		return this.getJedis().flushDB();
	}

	*//**
	 * �鿴redis���ж�������
	 *//*
	public long dbSize() {
		return this.getJedis().dbSize();
	}

	*//**
	 * ����Ƿ����ӳɹ�
	 * 
	 * @return
	 *//*
	public String ping() {
		return this.getJedis().ping();
	}

}
*/