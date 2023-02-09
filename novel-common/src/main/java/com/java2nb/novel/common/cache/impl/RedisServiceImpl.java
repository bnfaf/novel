package com.java2nb.novel.common.cache.impl;

import com.java2nb.novel.common.cache.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis缓存服务实现
 * @author xiongxiaoyang
 * @version 1.0
 * @since 2020/5/27
 */
@ConditionalOnProperty(prefix = "spring.redis", name = "host", matchIfMissing = false)
@RequiredArgsConstructor
@Service
public class RedisServiceImpl implements CacheService {

	private final StringRedisTemplate stringRedisTemplate;

	private final RedisTemplate<Object,  Object>  redisTemplate;


	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key,value);
	}

	@Override
	public void set(String key, String value, long timeout) {
		stringRedisTemplate.opsForValue().set(key,value,timeout, TimeUnit.SECONDS);

	}

	@Override
	public Object getObject(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void setObject(String key, Object value) {
		redisTemplate.opsForValue().set(key,value);
	}

	@Override
	public void setObject(String key, Object value, long timeout) {
		redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.SECONDS);
	}

	@Override
	public void del(String key) {
		redisTemplate.delete(key);
		stringRedisTemplate.delete(key);
	}

	@Override
	public boolean contains(String key) {
		return redisTemplate.hasKey(key) || stringRedisTemplate.hasKey(key);
	}

	@Override
	public void expire(String key, long timeout) {
		redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
		stringRedisTemplate.expire(key,timeout, TimeUnit.SECONDS);
	}

}
