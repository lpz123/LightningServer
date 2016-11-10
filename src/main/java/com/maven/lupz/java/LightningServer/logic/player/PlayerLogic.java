package com.maven.lupz.java.LightningServer.logic.player;

import org.bson.types.ObjectId;

import com.maven.lupz.java.LightningServer.LSCollectionManage;
import com.maven.lupz.java.LightningServer.database.mongodb.player.PlayerMon;

public class PlayerLogic implements LSCollectionManage{

	public static void logicServer(PlayerMon pm){
		System.out.println("角色登陆初始化");
		playerMap.put(pm.get_id(), pm);
		System.out.println("角色登陆成功");
	}

	public static PlayerMon getPlayerMon(String _id){
		return playerMap.get(new ObjectId(_id));
	}
}
