package com.maven.lupz.java.LightningServer;

import java.util.ArrayList;
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

public class GameTest_MongoDB {

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
		List<BasicDBObject> list=MongoDao.selectDB(PlayerMon.tableName, map);
		if(list.size()>3){
			System.out.println("该帐号角色已达3个，请重新创建其他帐号");
			return;
		}
		
		System.out.println("请输入角色名：");
		scan1 = new Scanner(System.in);
		String roleName=scan1.nextLine();
		
		map=new HashMap<>();
		map.put("roleName", roleName);
		list=MongoDao.selectDB(PlayerMon.tableName, map);
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
		pm.insertDB();
		
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
		List<BasicDBObject> list=MongoDao.selectDB(PlayerMon.tableName, map);
		List<PlayerMon> playerMonList=new ArrayList<>();
		for(BasicDBObject obj:list){
			PlayerMon pm=new PlayerMon(obj);
			playerMonList.add(pm);
			System.out.println("编号："+playerMonList.indexOf(pm)+"   "+pm.toString());
		}
		if(list.size()==0){
			System.err.println("没有该帐号");
			return;
		}
		
		System.out.println("请输入角色编号：");
		scan1 = new Scanner(System.in);
		int num=scan1.nextInt();
		if(num>=playerMonList.size()){
			System.out.println("输入错误");
			return;
		}
		Player player=PlayerLogic.getPlayerByProvider(playerMonList.get(num).get_id().toString());
		if(player==null){
			System.out.println("输入错误");
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
				System.out.println("当前属性：\n"+player.playerMon.toString());
				break;
			case 3:
				System.out.println("*****************"+player.playerMon.getRoleName()+"的背包***************************");
				System.out.println("*************************道具背包******************************");
				for(ItemMon im:player.itemMonMap.values()){
					System.out.println("*"+im.toString());
				}
				System.out.println("*************************装备背包***************************");
				for(EquipMon em:player.equipMonMap.values()){
					System.out.println("*"+em.toString());
//					if(em.getFuWenList().size()>0){
//						System.out.println("    该物品有符文属性");
//						for(FuWenMon fwmTemp:em.getFuWenList()){
//							System.out.println(fwmTemp.toString());
//						}
//					}
				}
				System.out.println("************************符文背包***************************");
				for(FuWenMon fwm:player.fuWenMonMap.values()){
					if(fwm.getEquipId()==null){
						System.out.println("*"+fwm.toString());
					}
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
			System.out.println("1：镶嵌符文，2：拆除符文，3：删除物品，4：退出");
			scan1 = new Scanner(System.in);
			int choose=scan1.nextInt();
			switch(choose){
			case 1:
				qianEquip(player);
				break;
			case 2:
				unQianEquip(player);
				break;
			case 3:
				deleteGoods(player);
				break;
			case 4:
				ran=false;
				break;
			}
		}
	}
	
	/**
	 * 删除物品
	 * @param player
	 */
	public static void deleteGoods(Player player){
		boolean ran=true;
		while(ran){
			System.out.println("1：删除道具，2：删除装备，3：删除符文，4：退出");
			scan1 = new Scanner(System.in);
			int choose1=scan1.nextInt();
			switch(choose1){
			case 1:
				deleteItem(player);
				break;
			case 2:
				deleteEquip(player);
				break;
			case 3:
				deleteFuWen(player);
				break;
			case 4:
				ran=false;
				break;
			}
		}
	}
	
	/**
	 * 删除道具
	 * @param player
	 */
	public static void deleteItem(Player player){
		System.out.println("请输入要删除的道具id:");
		boolean ran=true;
		while(ran){
			scan1 = new Scanner(System.in);
			String choose=scan1.nextLine();
			ItemMon im=player.itemMonMap.get(choose);
			if(im==null){
				System.out.println("没有该道具");
				ran=false;
			}
			System.out.println("是否删除该道具："+im.toString()+"\n    1：是，2：否");
			scan1 = new Scanner(System.in);
			int choose1=scan1.nextInt();
			switch(choose1){
			case 1:
				player.itemMonMap.remove(choose);
				im.deleteDB();
				ran=false;
				break;
			case 2:
				ran=false;
				break;
			}
		}
	}
	
	/**
	 * 删除装备
	 * @param player
	 */
	public static void deleteEquip(Player player){
		System.out.println("请输入要删除的装备id:");
		boolean ran=true;
		while(ran){
			scan1 = new Scanner(System.in);
			String choose=scan1.nextLine();
			EquipMon em=player.equipMonMap.get(choose);
			if(em==null){
				System.out.println("没有该装备");
				ran=false;
			}
			if(em.fuWenList.size()>0){
				System.out.println("该装备有符文，如果删除则一起被删除?   1：是，2：否");
				scan1 = new Scanner(System.in);
				int choose1=scan1.nextInt();
				if(choose1==1){
					for(FuWenMon fwm:em.fuWenList){
						player.fuWenMonMap.remove(fwm.get_id().toString());
						fwm.deleteDB();
					}
					player.equipMonMap.remove(choose);
					em.deleteDB();
					ran=false;
					continue;
				}else{
					ran=false;
					continue;
				}
			}

			System.out.println("是否删除该装备?    1：是，2：否");
			scan1 = new Scanner(System.in);
			int choose1=scan1.nextInt();
			switch(choose1){
			case 1:
				player.equipMonMap.remove(choose);
				em.deleteDB();
				ran=false;
				break;
			case 2:
				ran=false;
				break;
			}
		}
	}

	/**
	 * 删除符文
	 * @param player
	 */
	public static void deleteFuWen(Player player){
		System.out.println("请输入要删除的符文id:");
		boolean ran=true;
		while(ran){
			scan1 = new Scanner(System.in);
			String choose=scan1.nextLine();
			FuWenMon fwm=player.fuWenMonMap.get(choose);
			if(fwm==null){
				System.out.println("没有该符文");
				ran=false;
			}
			System.out.println("是否删除该符文："+fwm.toString()+"\n    1：是，2：否");
			scan1 = new Scanner(System.in);
			int choose1=scan1.nextInt();
			switch(choose1){
			case 1:
				player.fuWenMonMap.remove(choose);
				fwm.deleteDB();
				ran=false;
				break;
			case 2:
				ran=false;
				break;
			}
		}
	}
	
	/**
	 * 拆除符文
	 */
	public static void unQianEquip(Player player){
		if(player.equipMonMap.size()==0){
			System.out.println("您还没有武器，去打一些武器吧！");
			return;
		}
		boolean ran=true;
		while(ran){
			System.out.println("请输入要镶嵌的武器Id");
			scan1 = new Scanner(System.in);
			String choose1=scan1.nextLine();
			EquipMon em=player.equipMonMap.get(choose1);
			if(em==null){
				System.out.println("您输入有误");
				ran=false;
				continue;
			}
			if(em.fuWenList.size()==0){
				System.out.println("该装备并没有符文,请选择其他装备");
				continue;
			}
			System.out.println("您选择了："+em.toString()+"  是否确定：1确定，2：退出");
			scan1 = new Scanner(System.in);
			int choose2=scan1.nextInt();
			if(choose2==2){
				ran=false;
				continue;
			}
			System.out.println("请输入要拆除的符文Id");
			scan1 = new Scanner(System.in);
			String choose3=scan1.nextLine();
			FuWenMon fwm=player.fuWenMonMap.get(choose3);
			if(fwm==null){
				System.out.println("您输入有误");
				ran=false;
				continue;
			}
			System.out.println("您选择了："+fwm.toString()+"  是否确定：1确定，2：退出");
			scan1 = new Scanner(System.in);
			int choose4=scan1.nextInt();
			if(choose4==2){
				ran=false;
				continue;
			}
			fwm.removeEquipId();
			fwm.updateDB(true);
			em.getFuWenList().remove(fwm);
			ran=false;
		}
	}
	
	/**
	 * 镶嵌符文
	 * @param player
	 */
	public static void qianEquip(Player player){
		if(player.equipMonMap.size()==0){
			System.out.println("您还没有武器，去打一些武器吧！");
			return;
		}
		if(player.fuWenMonMap.size()==0){
			System.out.println("您还没有武器，去打一些符文吧！");
			return;
		}
		boolean ran=true;
		while(ran){
			System.out.println("请输入要镶嵌的武器Id");
			scan1 = new Scanner(System.in);
			String choose1=scan1.nextLine();
			EquipMon em=player.equipMonMap.get(choose1);
			if(em==null){
				System.out.println("您输入有误");
				ran=false;
				continue;
			}
			System.out.println("您选择了："+em.toString()+"  是否确定：1确定，2：退出");
			scan1 = new Scanner(System.in);
			int choose2=scan1.nextInt();
			if(choose2==2){
				ran=false;
				continue;
			}
			System.out.println("请输入要镶嵌的符文Id");
			scan1 = new Scanner(System.in);
			String choose3=scan1.nextLine();
			FuWenMon fwm=player.fuWenMonMap.get(choose3);
			if(fwm==null){
				System.out.println("您输入有误");
				ran=false;
				continue;
			}
			System.out.println("您选择了："+fwm.toString()+"  是否确定：1确定，2：退出");
			scan1 = new Scanner(System.in);
			int choose4=scan1.nextInt();
			if(choose4==2){
				ran=false;
				continue;
			}
			fwm.setEquipId(em.get_id());
			fwm.updateDB(true);
			em.getFuWenList().add(fwm);
			ran=false;
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
//		int mnum=1;
		if(mnum==0){//掉道具
			ItemMon item=ItemMon.create();
			item.setPlayer_Id(player.playerMon.get_id());
			item.setGoodsId(50003);
			item.setUseType(1);
			item.setName("月饼");
			item.setHp((int)Math.random()*9);
			item.setAtk((int)Math.random()*7);
			item.setDef((int)Math.random()*5);
			item.insertDB();
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
			equip.insertDB();
			player.equipMonMap.put(equip.get_id().toString(), equip);
		}else if(mnum==2){//掉符文
			FuWenMon fuWen=FuWenMon.create();
			fuWen.setPlayer_Id(player.playerMon.get_id());//角色唯一id
			fuWen.setGoodsId(2001);//物品模板id
			fuWen.setHp((int)Math.random()*10);//物品增加血量
			fuWen.setAtk((int)Math.random()*10);//物品增加攻击力
			fuWen.setDef((int)Math.random()*10);//物品增加防御力
			fuWen.setName("万能符文");
			fuWen.insertDB();
			player.fuWenMonMap.put(fuWen.get_id().toString(), fuWen);
		}
	}

}
