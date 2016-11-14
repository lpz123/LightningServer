package com.maven.lupz.java.LightningServer;

import java.util.concurrent.ConcurrentHashMap;

import com.maven.lupz.java.LightningServer.database.mongodb.mon.item.ItemMon;
import com.maven.lupz.java.LightningServer.domain.player.Player;


public interface LSCollectionManage {
	
	public ConcurrentHashMap<Object, Player> playerMap=new ConcurrentHashMap<>();
	
	public ConcurrentHashMap<Object, ConcurrentHashMap<Object,ItemMon>> playerBagMap=new ConcurrentHashMap<>();
	
	/*******以下为临时代码*************************************/
	class Monster{
		public Monster(String name,long hp,long atk,long def){
			this.name=name;
			this.hp=hp;
			this.atk=atk;
			this.def=def;
		}
		public String name;
		public long hp;
		public long atk;//攻击力
		public long def;//防御力
	}
	
}
