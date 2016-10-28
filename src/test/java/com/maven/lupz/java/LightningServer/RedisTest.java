package com.maven.lupz.java.LightningServer;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		LSGameManage.getInstance().init(args);
		
		Jedis jedis=new Jedis("192.168.91.4");
		System.out.println(jedis.ping());
	}

}
