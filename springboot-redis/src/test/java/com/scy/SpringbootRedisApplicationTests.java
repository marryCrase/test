package com.scy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {

	@Qualifier("redisTemplate")
	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	void contextLoads() {

		//redisTemplate 操作不同的数据类型，ops和我们的指令是一样的
		//opsForValue 操作字符串  类似string

		//除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务，和基本的CRUD

		//获取redis的连接对象
//		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//		connection.flushDb();
//		connection.flushAll();

		redisTemplate.opsForValue().set("scy","lalala");
		System.out.println(redisTemplate.opsForValue().get("scy"));

	}

}
