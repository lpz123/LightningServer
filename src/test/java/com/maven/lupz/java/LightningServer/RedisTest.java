package com.maven.lupz.java.LightningServer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.maven.lupz.java.LightningServer.database.redis.logic.RedisPoolUtil;
import com.maven.lupz.java.LightningServer.database.redis.proto.redis_proto.RedisGroup;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		LSGameManage.getInstance().init(args);
		
		RedisGroup.Redis_Player.Builder redisPlayer=RedisGroup.Redis_Player.newBuilder();
    	String onlyId=RedisPoolUtil.getOnlyId();
    	redisPlayer.setRoleId(onlyId);
    	redisPlayer.setRoleName("闪电");
    	redisPlayer.setLevel(1);
    	
		RedisPoolUtil.addObj(onlyId,redisPlayer.build().toByteArray());
		
		byte[] redisPlayerbyte=RedisPoolUtil.getObj(onlyId);
		RedisGroup.Redis_Player redisPlayer2=null;
		try {
			redisPlayer2=RedisGroup.Redis_Player.parseFrom(redisPlayerbyte);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		System.out.println(redisPlayer2.toString());

	}

}
