package com.bota.util;

import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis {
	private static JedisPool pool = null;

	/**
	 * 锟斤拷锟斤拷redis锟斤拷锟接筹拷
	 * 
	 * @param ip
	 * @param port
	 * @return JedisPool
	 */
	public static JedisPool getPool() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			// 锟斤拷锟斤拷一锟斤拷pool锟缴凤拷锟斤拷锟斤拷俑锟絡edis实锟斤拷通锟斤拷pool.getResource()锟斤拷锟斤拷取锟斤拷
			// 锟斤拷锟街滴�1锟斤拷锟斤拷锟绞撅拷锟斤拷锟斤拷疲锟斤拷锟斤拷pool锟窖撅拷锟斤拷锟斤拷锟斤拷maxActive锟斤拷jedis实锟斤拷锟斤拷锟绞眕ool锟斤拷状态为exhausted(锟侥撅拷)锟斤拷
			config.setMaxActive(500);
			// 锟斤拷锟斤拷一锟斤拷pool锟斤拷锟斤拷卸锟斤拷俑锟阶刺猧dle(锟斤拷锟叫碉拷)锟斤拷jedis实锟斤拷
			config.setMaxIdle(5);
			// 锟斤拷示锟斤拷borrow(锟斤拷锟斤拷)一锟斤拷jedis实锟斤拷时锟斤拷锟斤拷锟侥等达拷时锟戒，锟斤拷锟斤拷却锟绞憋拷洌拷锟街憋拷锟斤拷壮锟絁edisConnectionException锟斤拷
			config.setMaxWait(1000 * 100);
			// 锟斤拷borrow一锟斤拷jedis实锟斤拷时锟斤拷锟角凤拷锟斤拷前锟斤拷锟斤拷validate锟斤拷锟斤拷锟斤拷锟斤拷锟轿猼rue锟斤拷锟斤拷玫锟斤拷锟絡edis实锟斤拷锟斤拷强锟斤拷玫模锟�
			config.setTestOnBorrow(true);
			pool = new JedisPool(config, "192.168.0.159");
		}
		return pool;
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟接筹拷
	 * 
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(JedisPool pool, Jedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	/**
	 * 锟斤拷取锟斤拷锟�
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = null;

		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			// 锟酵凤拷redis锟斤拷锟斤拷
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 锟斤拷锟斤拷锟斤拷锟斤拷锟接筹拷
			returnResource(pool, jedis);
		}
		return value;
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷
	 * 
	 * @param key
	 * @return
	 */
	public static void set(String key, String value) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			// 锟酵凤拷redis锟斤拷锟斤拷
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 锟斤拷锟斤拷锟斤拷锟斤拷锟接筹拷
			returnResource(pool, jedis);
		}
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷
	 * 
	 * @param mapStr
	 * @return
	 */
	public static void set(String key, Map<String, String> mapStr) {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.hmset(key, mapStr);
		} catch (Exception e) {
			// 锟酵凤拷redis锟斤拷锟斤拷
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 锟斤拷锟斤拷锟斤拷锟斤拷锟接筹拷
			returnResource(pool, jedis);
		}
	}

	/**
	 * 锟斤拷锟斤拷欠锟斤拷锟斤拷
	 * 
	 * @param mapStr
	 * @return
	 */
	public static boolean exist(String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		boolean bool = false;
		try {
			pool = getPool();
			jedis = pool.getResource();
			bool = jedis.exists(key);
		} catch (Exception e) {
			// 锟酵凤拷redis锟斤拷锟斤拷
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 锟斤拷锟斤拷锟斤拷锟斤拷锟接筹拷
			returnResource(pool, jedis);
		}
		return bool;
	}
	
	public static void outAllInfo() {
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			System.out.println(jedis.keys("*"));
		} catch (Exception e) {
			// 锟酵凤拷redis锟斤拷锟斤拷
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			// 锟斤拷锟斤拷锟斤拷锟斤拷锟接筹拷
			returnResource(pool, jedis);
		}
	}
}
