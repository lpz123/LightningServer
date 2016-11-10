package com.maven.lupz.java.LightningServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.bson.types.ObjectId;

import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.player.PlayerMon;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class GameTest {

	private static Scanner scan1;

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
		
		System.out.println("请输入角色名：");
		scan1 = new Scanner(System.in);
		String roleName=scan1.nextLine();
		
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
		map=new HashMap<>();
		map.put("_id", new ObjectId(roleId));
		list=MongoDao.selectDB("t_game_role", map);
		for(BasicDBObject obj:list){
			PlayerMon pm=new PlayerMon(obj);
			System.out.println(pm.getRoleName()+"登陆游戏");
		}
	}
	
	/**
	 * 退出游戏
	 */
	public static void exit(){
		
	}

}
