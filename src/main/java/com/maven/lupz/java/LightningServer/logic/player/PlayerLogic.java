package com.maven.lupz.java.LightningServer.logic.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.maven.lupz.java.LightningServer.LSCollectionManage;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.equip.EquipMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.player.PlayerMon;
import com.maven.lupz.java.LightningServer.domain.player.Player;
import com.mongodb.BasicDBObject;

public class PlayerLogic implements LSCollectionManage{

	/**
	 * 角色登陆
	 * @param pm
	 */
	public static Player logicServer(String roleId){
		System.out.println("角色登陆初始化");
		
		/*加载角色数据*/
		Player player=PlayerLogic.getPlayer(roleId);
		if(player==null){
			Map<String,Object> map=new HashMap<>();
			map.put("_id", new ObjectId(roleId));
			List<BasicDBObject> list=MongoDao.selectDB("t_game_role", map);
			for(BasicDBObject obj:list){
				PlayerMon playerMon=new PlayerMon(obj);
				player=new Player();
				player.setPlayerMon(playerMon);
			}
		}
		if(player==null){//角色不存在
			return null;	
		}
		
		/*加载装备数据*/
		Map<String,Object> mapEquip=new HashMap<>();
		mapEquip.put("roleId", player.getPlayerMon().get_id());
		List<BasicDBObject> listEquip=MongoDao.selectDB("t_game_role_item", mapEquip);
		for(BasicDBObject obj:listEquip){
			EquipMon equipMon=new EquipMon(obj);
		}
		
		/*加载物品数据*/
		
		/*加载符文数据*/
		
		playerMap.put(player.getPlayerMon().get_id(), player);
		System.out.println("角色登陆成功");
		return player;
	}

	public static Player getPlayer(String _id){
		return playerMap.get(new ObjectId(_id));
	}
}
