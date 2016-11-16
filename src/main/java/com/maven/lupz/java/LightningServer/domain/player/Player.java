package com.maven.lupz.java.LightningServer.domain.player;

import java.util.concurrent.ConcurrentHashMap;

import com.maven.lupz.java.LightningServer.database.mongodb.core.EDBType;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.equip.EquipMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.fuwen.FuWenMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.item.ItemMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.player.PlayerMon;

public class Player {

	public PlayerMon playerMon=null;
	
	public ConcurrentHashMap<String,ItemMon> itemMonMap = new ConcurrentHashMap<>();//物品包
	
	public ConcurrentHashMap<String,EquipMon> equipMonMap = new ConcurrentHashMap<>();//装备包
	
	public ConcurrentHashMap<String,FuWenMon> fuWenMonMap = new ConcurrentHashMap<>();//符文包
	
	/**
	 * 可用作定时更新 Update操作
	 */
	public void save(){
		playerMon.updateDB(true);//存储角色数据
		
		for(EquipMon em:equipMonMap.values()){//存储装备数据
			if(em.getDBType()==EDBType.UPDATE){
				em.updateDB(true);
			}
		}
		
		for(ItemMon im:itemMonMap.values()){//存储道具数据
			if(im.getDBType()==EDBType.UPDATE){
				im.updateDB(true);
			}
		}
		
		for(FuWenMon fwm:fuWenMonMap.values()){//存储符文数据
			if(fwm.getDBType()==EDBType.UPDATE){
				fwm.updateDB(true);
			}
		}
		
	}
}
