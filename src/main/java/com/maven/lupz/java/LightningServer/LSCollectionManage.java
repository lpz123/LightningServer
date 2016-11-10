package com.maven.lupz.java.LightningServer;

import java.util.concurrent.ConcurrentHashMap;

import com.maven.lupz.java.LightningServer.database.mongodb.player.PlayerMon;

public interface LSCollectionManage {
	
	public ConcurrentHashMap<Object, PlayerMon> playerMap=new ConcurrentHashMap<>();
	
}
