package com.maven.lupz.java.LightningServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.maven.lupz.java.LightningServer.LSCollectionManage.Monster;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.item.ItemMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.player.PlayerMon;
import com.maven.lupz.java.LightningServer.domain.player.Player;
import com.mongodb.BasicDBObject;

public class GameTest {

	/**
	 * 一个角色可以装备2把武器：分左右 1左，2右
	 * 一个角色可以装备8个防具：11：头盔，12：胸甲，13：裙甲，14：护腿，15：护靴，16：肩甲，17：臂甲，18：护手
	 */
	
	private static Scanner scan1;
	private static PlayerMon player;

	public static void main(String[] args) {
		LSServerManage lsGM=LSServerManage.getInstance();
		lsGM.init(args);
		
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
		
		System.out.println("请输职业：1：战士，2：法师");
		scan1 = new Scanner(System.in);
		int profession=scan1.nextInt();
		
		PlayerMon pm=PlayerMon.create();
		pm.setUserName(userName);
		pm.setRoleName(roleName);
		pm.setProfession(profession);
		switch(profession){
		case 1:
			pm.setHp(100);
			pm.setAtk(50);
			pm.setDef(20);
			break;
		case 2:
			pm.setHp(80);
			pm.setAtk(60);
			pm.setDef(16);
			break;
		}
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
//		player=PlayerLogic.getPlayerMon(roleId);
//		if(player==null){
//			map=new HashMap<>();
//			map.put("_id", new ObjectId(roleId));
//			list=MongoDao.selectDB("t_game_role", map);
//			for(BasicDBObject obj:list){
//				player=new PlayerMon(obj);
//				PlayerLogic.logicServer(player);
//			}
//		}
//		gameStart(player);
	}
	
	/**
	 * 退出游戏
	 */
	public static void exit(){
		for(Player player:LSCollectionManage.playerMap.values()){
			Map<String,Object> map=new HashMap<>();
			map.put("_id", player.getPlayerMon().get_id());
			MongoDao.updateDB("t_game_role", player.getPlayerMon());
		}
		System.exit(1);
	}
	
	public static void gameStart(PlayerMon player){
		boolean ran=true;
		while(ran){
			System.out.println("欢迎"+player.getRoleName()+"来到LSServer世界");
			System.out.println("0：寻怪，1：查询当前在线人数，2：查询自己属性，3：查询自己背包，4：查询自己装备，5：登出");
			scan1 = new Scanner(System.in);
			int choose=scan1.nextInt();
			switch(choose){
			case 0:
				searchMonster();
				break;
			case 1:
				System.out.println("当前在线人数："+LSCollectionManage.playerMap.size()+"人");
				break;
			case 2:
				System.out.println("当前属性：\n"+player.toString());
				break;
			case 3:
				System.out.println("*****************"+player.getRoleName()+"的背包,共有"+LSCollectionManage.playerBagMap.get(player.get_id()).size()+"个物品***************************");
				for(ItemMon im:LSCollectionManage.playerBagMap.get(player.get_id()).values()){
					System.out.println("*   "+im.toString());
				}
				System.out.println("******************************************************8***************************");
				break;
			case 4:break;
			case 5:
				ran=false;
				break;
			}
		}
	}
	
	/**
	 * 寻怪
	 */
	public static void searchMonster(){
		boolean ran=true;
		String[] mStr={"怪物A","怪物B","怪物C","怪物D","Boss"};
		while(ran){
			int mnum=(int) (Math.random()*1000%mStr.length);
			Monster m=new Monster(mStr[mnum], 30, 20, 20);

			System.out.println("发现"+m.name+"   hp:"+m.hp+"   atk:"+m.atk+"   def:"+m.def);
			
			System.out.println("是否战斗？     1:是      2:退出");
			scan1 = new Scanner(System.in);
			int choose=scan1.nextInt();
			switch(choose){
			case 1:
				fightStart(m);
				break;
			case 2:
				ran=false;
				break;
			}
		}
	}
	
	public static void fightStart(Monster m){
		System.out.println("怪物死亡");
		int mnum=(int) (Math.random()*1000%3);
		if(mnum==0){//掉道具
//			ItemMon item=new ItemMon();
//			item.setPlayer_Id(player.get_id());
//			item.setGoodsId(50003);
			
//			private int type;//物品类型     1：符文，2：药水
//			private long hp;//物品增加血量
//			private long atk;//物品增加攻击力
//			private long def;//物品增加防御力
		}else if(mnum==1){//掉装备
			
		}else if(mnum==2){//掉道具和装备
			
		}
	}

}
