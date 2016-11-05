package com.maven.lupz.java.LightningServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.msgpack.MessagePack;
import org.msgpack.template.Template;

import com.google.protobuf.InvalidProtocolBufferException;
import com.maven.lupz.java.LightningServer.database.redis.logic.RedisPoolUtil;
import com.maven.lupz.java.LightningServer.database.redis.logic.player.PlayerMP;
import com.maven.lupz.java.LightningServer.database.redis.proto.redis_proto.RedisGroup;
import com.maven.lupz.java.LightningServer.database.redis.proto.redis_proto.RedisGroup.Redis_Player;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		LSGameManage.getInstance().init(args);
		
//		RedisPoolUtil.flushAll();
		
		String dbName="game01";
		
		/***********单一添加****************************************************/
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

//		/***********批量添加****************************************************/
//		long t1=System.currentTimeMillis();
//		Map<String,byte[]> newMap=new HashMap<>();
//		for(int i=0;i<10000;i++){
//			RedisGroup.Redis_Player.Builder redisPlayer=RedisGroup.Redis_Player.newBuilder();
//			String onlyId=RedisPoolUtil.getOnlyId();
//			redisPlayer.setRoleId(onlyId);
//	    	redisPlayer.setRoleName("闪电");
//	    	redisPlayer.setLevel(1);
//	    	newMap.put(onlyId, redisPlayer.build().toByteArray());
//		}
//		long t2=System.currentTimeMillis();
//		RedisPoolUtil.addSomeObj(dbName, newMap);
//		long t3=System.currentTimeMillis();
//		System.out.println("protobuf+redis: "+(t2-t1)+"+"+(t3-t2)+"="+(t3-t1));
		
		/***********修改****************************************************/
//		byte[] body=RedisPoolUtil.getObj(dbName, "100");
//		try {
//			Redis_Player p=Redis_Player.parseFrom(body);			
//			System.out.println(p.toString()+p.getRoleName());
//		} catch (InvalidProtocolBufferException e) {
//			e.printStackTrace();
//		}
//		
//		body=RedisPoolUtil.getObj(dbName, "101");
//		try {
//			Redis_Player p=Redis_Player.parseFrom(body);			
//			System.out.println(p.toString()+p.getRoleName());
//		} catch (InvalidProtocolBufferException e) {
//			e.printStackTrace();
//		}
//		
//		Redis_Player.Builder bb=Redis_Player.newBuilder();
//		bb.setRoleId("100");
//		bb.setRoleName("闪电大牛");
//		bb.setLevel(500);
//		RedisPoolUtil.addObj(dbName,bb.getRoleId(),bb.build().toByteArray());
//		
//		body=RedisPoolUtil.getObj(dbName, "100");
//		try {
//			Redis_Player p=Redis_Player.parseFrom(body);			
//			System.out.println(p.toString()+p.getRoleName());
//		} catch (InvalidProtocolBufferException e) {
//			e.printStackTrace();
//		}
//		
//		body=RedisPoolUtil.getObj(dbName, "101");
//		try {
//			Redis_Player p=Redis_Player.parseFrom(body);			
//			System.out.println(p.toString()+p.getRoleName());
//		} catch (InvalidProtocolBufferException e) {
//			e.printStackTrace();
//		}
		
		
		
		MessagePack mp=new MessagePack();
		mp.register(PlayerMP.class);
		
//		long t11=System.currentTimeMillis();
//		Map<String,byte[]> newMap1=new HashMap<>();
//		String aaa="";
//		for(int i=0;i<10000;i++){
//			PlayerMP p=new PlayerMP();
//			String onlyId=RedisPoolUtil.getOnlyId();
//			p.setRoleId(onlyId);
//			p.setRoleName("闪电侠");
//			p.setLevel(1);
//			if(i==5000){
//				aaa=p.getRoleId();
//			}
//			try {
//				newMap1.put(p.getRoleId(), mp.write(p));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		long t12=System.currentTimeMillis();
//		RedisPoolUtil.addSomeObj(dbName, newMap1);
//		long t13=System.currentTimeMillis();
//		System.out.println("MessagePak+redis: "+(t12-t11)+"+"+(t13-t12)+"="+(t13-t11));
		
		long t14=System.currentTimeMillis();
		PlayerMP pp2=null;
		try {
			pp2=mp.read(RedisPoolUtil.getObj(dbName, "5001"),PlayerMP.class);
			System.out.println(pp2.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		long t15=System.currentTimeMillis();
		System.out.println("查询消耗:"+(t15-t14));
		
		pp2.a="高总2";
		pp2.b=50;
		try {
			RedisPoolUtil.addObj(dbName, pp2.roleId, mp.write(pp2));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			PlayerMP pp3=mp.read(RedisPoolUtil.getObj(dbName, "5001"),PlayerMP.class);
			System.out.println(pp3.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
