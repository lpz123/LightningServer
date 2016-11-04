package com.maven.lupz.java.LightningServer;

import java.util.HashMap;
import java.util.Map;

import com.google.protobuf.InvalidProtocolBufferException;
import com.maven.lupz.java.LightningServer.database.redis.logic.RedisPoolUtil;
import com.maven.lupz.java.LightningServer.database.redis.proto.redis_proto.RedisGroup;
import com.maven.lupz.java.LightningServer.database.redis.proto.redis_proto.RedisGroup.Redis_Player;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		LSGameManage.getInstance().init(args);
		
//		RedisPoolUtil.flushAll();
		
		String dbName="game01";
		
//		//单一添加
//		long time=System.currentTimeMillis();
//		for(int i=0;i<10000;i++){
//			RedisGroup.Redis_Player.Builder redisPlayer=RedisGroup.Redis_Player.newBuilder();
//	    	String onlyId=RedisPoolUtil.getOnlyId();
//	    	redisPlayer.setRoleId(onlyId);
//	    	redisPlayer.setRoleName("闪电");
//	    	redisPlayer.setLevel(1);
//			RedisPoolUtil.addObj(dbName,onlyId,redisPlayer.build().toByteArray());
//		}
//		System.out.println(System.currentTimeMillis()-time);
		
//		//批量添加
		long time1=System.currentTimeMillis();
		Map<String,byte[]> newMap=new HashMap<>();
		for(int i=0;i<10000;i++){
			RedisGroup.Redis_Player.Builder redisPlayer=RedisGroup.Redis_Player.newBuilder();
			String onlyId=RedisPoolUtil.getOnlyId();
			redisPlayer.setRoleId(onlyId);
	    	redisPlayer.setRoleName("闪电");
	    	redisPlayer.setLevel(1);
	    	newMap.put(onlyId, redisPlayer.build().toByteArray());
		}
		long time2=System.currentTimeMillis();
		RedisPoolUtil.addSomeObj(dbName, newMap);
		long t1=System.currentTimeMillis()-time1;
		long t2=System.currentTimeMillis()-time2;
		System.out.println(t1+"+"+t2+"="+(t1+t2));
		
//		byte[] body=RedisPoolUtil.getObj(dbName, "100");
//		try {
//			Redis_Player p=Redis_Player.parseFrom(body);			
//			System.out.println(String.format(p.toString(), "UTF-8")+p.getRoleName());
//		} catch (InvalidProtocolBufferException e) {
//			e.printStackTrace();
//		}
//		
//		body=RedisPoolUtil.getObj(dbName, "101");
//		try {
//			Redis_Player p=Redis_Player.parseFrom(body);			
//			System.out.println(String.format(p.toString(), "UTF-8")+p.getRoleName());
//		} catch (InvalidProtocolBufferException e) {
//			e.printStackTrace();
//		}
//		
//		Redis_Player.Builder bb=Redis_Player.newBuilder();
//		bb.setRoleId("100");
//		bb.setRoleName("闪电\351大牛");
//		bb.setLevel(500);
//		RedisPoolUtil.addObj(dbName,bb.getRoleId(),bb.build().toByteArray());
//		
//		body=RedisPoolUtil.getObj(dbName, "100");
//		try {
//			Redis_Player p=Redis_Player.parseFrom(body);			
//			System.out.println(String.format(p.toString(), "UTF-8")+p.getRoleName());
//		} catch (InvalidProtocolBufferException e) {
//			e.printStackTrace();
//		}
//		
//		body=RedisPoolUtil.getObj(dbName, "101");
//		try {
//			Redis_Player p=Redis_Player.parseFrom(body);			
//			System.out.println(String.format(p.toString(), "UTF-8")+p.getRoleName());
//		} catch (InvalidProtocolBufferException e) {
//			e.printStackTrace();
//		}
		
	}

}
