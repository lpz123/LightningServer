package com.maven.lupz.java.LightningServer.logic.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.types.ObjectId;

import com.maven.lupz.java.LightningServer.LSCollectionManage;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.equip.EquipMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.fuwen.FuWenMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.item.ItemMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.player.PlayerMon;
import com.maven.lupz.java.LightningServer.domain.player.Player;
import com.mongodb.BasicDBObject;

public class PlayerLogic implements LSCollectionManage{

	/**
	 * 可用于登陆和获取离线角色
	 * @param roleId 角色id
	 */
	public static Player getPlayerByProvider(String roleId){
		System.out.println("角色登陆初始化");
		
		/*加载角色数据*/
		Player player=PlayerLogic.getPlayerByOnline(roleId);
		if(player==null){
			Map<String,Object> map=new HashMap<>();
			map.put("_id", new ObjectId(roleId));
			
			List<BasicDBObject> list=MongoDao.selectDB("t_game_PlayerMon", map);
			for(BasicDBObject obj:list){
				PlayerMon playerMon=new PlayerMon(obj);
				player=new Player();
				player.playerMon=playerMon;
			}
			if(player==null){//角色不存在
				return null;	
			}else{
				playerMap.put(player.playerMon.get_id(), player);
				loginOther(player);
			}	
		}

		System.out.println("角色登陆成功");
		return player;
	}
	
	private static void loginOther(Player player){
		/*加载装备数据*/
		Map<String,Object> mapEquip=new HashMap<>();
		mapEquip.put("roleId", player.playerMon.get_id());
		List<BasicDBObject> listEquip=MongoDao.selectDB("t_game_role_item", mapEquip);
		ConcurrentHashMap<String,EquipMon> equipMonMap=new ConcurrentHashMap<>();
		for(BasicDBObject obj:listEquip){
			EquipMon mon=new EquipMon(obj);
			equipMonMap.put(mon.get_id().toString(), mon);
		}
		player.equipMonMap.clear();
		player.equipMonMap.putAll(equipMonMap);
		
		/*加载物品数据*/
		Map<String,Object> mapItem=new HashMap<>();
		mapItem.put("roleId", player.playerMon.get_id());
		List<BasicDBObject> listItem=MongoDao.selectDB("t_game_role_item", mapItem);
		ConcurrentHashMap<String,ItemMon> itemMonMap=new ConcurrentHashMap<>();
		for(BasicDBObject obj:listItem){
			ItemMon mon=new ItemMon(obj);
			itemMonMap.put(mon.get_id().toString(), mon);
		}
		player.itemMonMap.clear();
		player.itemMonMap.putAll(itemMonMap);
		
		/*加载符文数据*/
		Map<String,Object> mapFuWen=new HashMap<>();
		mapFuWen.put("roleId", player.playerMon.get_id());
		List<BasicDBObject> listFuWen=MongoDao.selectDB("t_game_role_item", mapFuWen);
		ConcurrentHashMap<String,FuWenMon> fuWenMonMap=new ConcurrentHashMap<>();
		for(BasicDBObject obj:listFuWen){
			FuWenMon mon=new FuWenMon(obj);
			fuWenMonMap.put(mon.get_id().toString(), mon);
		}
		player.fuWenMonMap.clear();
		player.fuWenMonMap.putAll(fuWenMonMap);

	}

	/**
	 * 通过角色唯一id从内存获取玩家
	 * @param _id
	 * @return
	 */
	public static Player getPlayerByOnline(String _id){
		return playerMap.get(new ObjectId(_id));
	}
}
