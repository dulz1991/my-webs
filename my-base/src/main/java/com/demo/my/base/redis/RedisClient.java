package com.demo.my.base.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.Jedis;

public class RedisClient {

	public static Jedis jedis;

	@Autowired
	@Qualifier("jedisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory;

	/**
	 * 获取一个jedis 客户端
	 * 
	 * @return
	 */
	public Jedis getJedis() {
		if (jedis == null) {
			return jedisConnectionFactory.getShardInfo().createResource();
		}
		return jedis;
	}

}
