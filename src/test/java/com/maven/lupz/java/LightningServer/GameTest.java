package com.maven.lupz.java.LightningServer;

import java.util.Scanner;

import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.player.PlayerMon;

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
			System.out.println("1:创建角色，2：登陆角色，3：退出");
			scan1 = new Scanner(System.in);
			int scanInt1=scan1.nextInt();
			switch(scanInt1){
			case 1:
				creatPlayer();
				break;
			case 2:
				creatPlayer();
				break;
			case 3:
				exit();
				break;
			}
		}
	}
	
	/**
	 * 创建角色
	 */
	public static void creatPlayer(){
		System.out.println("请输入角色名：");
		scan1 = new Scanner(System.in);
		String roleName=scan1.nextLine();
		
		System.out.println("请输职业：1：战士，2：法师，3：刺客，4：牧师");
		scan1 = new Scanner(System.in);
		int profession=scan1.nextInt();
		
		PlayerMon pm=new PlayerMon();
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
		System.out.println("请输入角色名：");
	}
	
	/**
	 * 退出游戏
	 */
	public static void exit(){
		
	}

}
