package com.maven.lupz.java.LightningServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.bson.types.ObjectId;

import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.player.PlayerMon;
import com.maven.lupz.java.LightningServer.logic.player.PlayerLogic;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class GameTest {

	private static Scanner scan1;
	private static PlayerMon player;

	public static void main(String[] args) {
		LSServerManage lsGM=LSServerManage.getInstance();
		lsGM.init(args);
		
		//批量创建角色 10个线程均分1万个角色
//		for(int i=0;i<100;i++){
//			new Thread(new TTTT()).start();
//		}
		
		boolean run=true;
		while(run){
			System.out.println("您要做什么？");
			System.out.println("1:创建角色，2：登陆角色，3：退出，4：清库");
			scan1 = new Scanner(System.in);
			int scanInt1=scan1.nextInt();
			switch(scanInt1){
			case 1:
				creatPlayer();
				break;
			case 2:
				loginPlayer();
				break;
			case 3:
				exit();
				break;
			case 4:
				MongoDao.deleteTable("t_game_role");
				break;
			case 5:
				break;
			}
		}
	}
	
	/**
	 * 创建角色
	 */
	public static void creatPlayer(){
		System.out.println("请输入帐号：");
		scan1 = new Scanner(System.in);
		String userName=scan1.nextLine();
		
		Map<String,Object> map=new HashMap<>();
		map.put("userName", userName);
		List<BasicDBObject> list=MongoDao.selectDB("t_game_role", map);
		if(list.size()>3){
			System.out.println("该帐号角色已达3个，请重新创建其他帐号");
			return;
		}
		
		System.out.println("请输入角色名：");
		scan1 = new Scanner(System.in);
		String roleName=scan1.nextLine();
		
		map=new HashMap<>();
		map.put("roleName", roleName);
		list=MongoDao.selectDB("t_game_role", map);
		if(list.size()!=0){
			System.out.println("角色名重复");
			return;
		}
		
		System.out.println("请输职业：1：战士，2：法师，3：刺客，4：牧师");
		scan1 = new Scanner(System.in);
		int profession=scan1.nextInt();
		
		PlayerMon pm=new PlayerMon();
		pm.setUserName(userName);
		pm.setRoleName(roleName);
		pm.setLevel(1);
		pm.setProfession(profession);
		
		MongoDao.insertDB("t_game_role", pm);
		
		System.out.println("创建成功 角色id为:"+pm.get_id());
	}
	
	/**
	 * 登陆游戏
	 */
	public static void loginPlayer(){
		System.out.println("请输入帐号：");
		scan1 = new Scanner(System.in);
		String userName=scan1.nextLine();
		
		Map<String,Object> map=new HashMap<>();
		map.put("userName", userName);
		List<BasicDBObject> list=MongoDao.selectDB("t_game_role", map);
		for(BasicDBObject obj:list){
			PlayerMon pm=new PlayerMon(obj);
			System.out.println(pm.toString());
		}
		if(list.size()==0){
			System.err.println("没有该帐号");
			return;
		}
		
		System.out.println("请输入角色id：");
		scan1 = new Scanner(System.in);
		String roleId=scan1.nextLine();
		player=PlayerLogic.getPlayerMon(roleId);
		if(player==null){
			map=new HashMap<>();
			map.put("_id", new ObjectId(roleId));
			list=MongoDao.selectDB("t_game_role", map);
			for(BasicDBObject obj:list){
				player=new PlayerMon(obj);
				PlayerLogic.logicServer(player);
			}
		}
		
		gameStart();
	}
	
	public static void gameStart(){
		boolean ran=true;
		while(ran){
			System.out.println("欢迎来到LSServer世界");
			System.out.println("1：查询当前在线人数，2：查询自己属性，3：查询自己背包，4：查询自己装备，5：登出");
			scan1 = new Scanner(System.in);
			int choose=scan1.nextInt();
			switch(choose){
			case 1:break;
			case 2:break;
			case 3:break;
			case 4:break;
			case 5:
				ran=false;
				break;
			}
		}
	}
	
	/**
	 * 退出游戏
	 */
	public static void exit(){
		for(PlayerMon mon:LSCollectionManage.playerMap.values()){
			Map<String,Object> map=new HashMap<>();
			map.put("_id", mon.get_id());
			MongoDao.updateDB("t_game_role", mon);
		}
		System.exit(1);
	}

}
