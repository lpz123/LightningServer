package com.maven.lupz.java.LightningServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.maven.lupz.java.LightningServer.LSCollectionManage.Monster;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.equip.EquipMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.fuwen.FuWenMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.item.ItemMon;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.player.PlayerMon;
import com.maven.lupz.java.LightningServer.domain.player.Player;
import com.maven.lupz.java.LightningServer.logic.player.PlayerLogic;
import com.mongodb.BasicDBObject;

public class GameTest {

	/**
	 * 一个角色可以装备2把武器：分左右 1左，2右
	 * 一个角色可以装备8个防具：11：头盔，12：胸甲，13：裙甲，14：护腿，15：护靴，16：肩甲，17：臂甲，18：护手
	 */
	
	private static Scanner scan1;
	
	public static void main(String[] args) {
		LSServerManage lsGM=LSServerManage.getInstance();
		lsGM.init(args);
		
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
				loginPlayer();
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
		pm.save();
		
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
		List<BasicDBObject> list=MongoDao.selectDB("t_game_PlayerMon", map);
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
		Player player=PlayerLogic.getPlayerByProvider(roleId);
		if(player==null){
			System.out.println("该角色不存在");
		}else{
			gameStart(player);
		}
	}
	
	/**
	 * 退出游戏
	 */
	public static void exit(){
		for(Player player:LSCollectionManage.playerMap.values()){
			player.save();
		}
		System.out.println("LServer测试结束");
		System.exit(1);
	}
	
	/**
	 * 开始游戏
	 * @param player
	 */
	public static void gameStart(Player player){
		boolean ran=true;
		while(ran){
			System.out.println("欢迎"+player.playerMon.getRoleName()+"来到LSServer世界");
			System.out.println("0：寻怪，1：查询当前在线人数，2：查询自己属性，3：背包与相关玩法，4：查询身上的装备，5：登出");
			scan1 = new Scanner(System.in);
			int choose=scan1.nextInt();
			switch(choose){
			case 0:
				searchMonster(player);
				break;
			case 1:
				System.out.println("当前在线人数："+LSCollectionManage.playerMap.size()+"人");
				break;
			case 2:
				System.out.println("当前属性：\n"+player.toString());
				break;
			case 3:
				System.out.println("*****************"+player.playerMon.getRoleName()+"的背包***************************");
				int ItemNum=player.itemMonMap.size();
				System.out.println("*****************道具背包共有:"+ItemNum+"件物品***************************");
				for(ItemMon im:player.itemMonMap.values()){
					System.out.println("*"+im.toString());
				}
				System.out.println("*****************装备背包共有:"+ItemNum+"件物品***************************");
				for(EquipMon em:player.equipMonMap.values()){
					System.out.println("*"+em.toString());
				}
				System.out.println("*****************符文背包共有:"+ItemNum+"件物品***************************");
				for(FuWenMon fwm:player.fuWenMonMap.values()){
					System.out.println("*"+fwm.toString());
				}
				System.out.println("************************************************************************************");
				bagPlay(player);
				break;
			case 4:
				System.out.println("*******************"+player.playerMon.getRoleName()+"身上的装备**************************************");
				for(EquipMon em:player.equipMonMap.values()){
					if(em.getEquipColmun()!=0){
						System.out.println(em.toString());
					}
				}
				break;
			case 5:
				ran=false;
				break;
			}
		}
	}
	
	/**
	 * 背包与相关玩法
	 */
	public static void bagPlay(Player player){
		boolean ran=true;
		while(ran){
			System.out.println("1：镶嵌符文，2：拆除符文");
		}
	}
	
	/**
	 * 寻怪
	 */
	public static void searchMonster(Player player){
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
				fightStart(player,m);
				break;
			case 2:
				ran=false;
				break;
			}
		}
	}
	
	public static void fightStart(Player player,Monster m){
		System.out.println("怪物死亡");
		int mnum=(int) (Math.random()*1000%3);
		if(mnum==0){//掉道具
			ItemMon item=ItemMon.create();
			item.setPlayer_Id(player.playerMon.get_id());
			item.setGoodsId(50003);
			item.setUseType(1);
			item.setName("月饼");
			item.setHp((int)Math.random()*9);
			item.setAtk((int)Math.random()*7);
			item.setDef((int)Math.random()*5);
			item.save();
			player.itemMonMap.put(item.get_id().toString(), item);
		}else if(mnum==1){//掉装备
			EquipMon equip=EquipMon.create();
			equip.setPlayer_Id(player.playerMon.get_id());//角色唯一id
			equip.setGoodsId(5002);//物品模板id
			equip.setType(1);//物品类型     1：武器，2：防具
			equip.setEquipColmun(0);//装备位置  0：未装备
			equip.setHp((int)Math.random()*100);//物品增加血量
			equip.setAtk((int)Math.random()*100);//物品增加攻击力
			equip.setDef((int)Math.random()*100);//物品增加防御力
			equip.setName("万能装备");
			equip.save();
			player.equipMonMap.put(equip.get_id().toString(), equip);
		}else if(mnum==2){//掉符文
			FuWenMon fuWen=FuWenMon.create();
			fuWen.setPlayer_Id(player.playerMon.get_id());//角色唯一id
			fuWen.setGoodsId(2001);//物品模板id
			fuWen.setHp((int)Math.random()*10);//物品增加血量
			fuWen.setAtk((int)Math.random()*10);//物品增加攻击力
			fuWen.setDef((int)Math.random()*10);//物品增加防御力
			fuWen.setName("万能符文");
			fuWen.save();
			player.fuWenMonMap.put(fuWen.get_id().toString(), fuWen);
		}
	}

}
