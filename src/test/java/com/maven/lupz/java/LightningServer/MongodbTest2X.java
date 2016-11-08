package com.maven.lupz.java.LightningServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoConfig;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoMap;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongodbTest2X {

	public static void main(String[] args) {
		LSGameManage lsGM=LSGameManage.getInstance();
		lsGM.init(args);
		
		String[] address={lsGM.getProp().getProperty("mongoAddress").trim()};
		int[] prot={Integer.parseInt(lsGM.getProp().getProperty("mongoPort").trim())};
		String dbname=lsGM.getProp().getProperty("mongo.dbname").trim();
		MongoConfig.setHost(address);
		MongoConfig.setPort(prot);
		MongoConfig.setDbName(dbname);
		
		String tableName="t_game_role";
		
		/****查询该表所有数据*******/
		List<DBObject> selectAll=MongoDao.selectDB(tableName);
		for(DBObject ob:selectAll){
			System.out.println(ob);
		}
		System.out.println("----------------------------------------------------------------------\n");
		
		/****插入数据*******/
		BasicDBObject obj=new BasicDBObject();
		obj.append("roleId", 1);
		obj.append("roleName", "张三");
		obj.append("level", 1);
		MongoDao.insertDB(tableName, obj);
		System.out.println("----------------------------------------------------------------------\n");

		/****删除表*******/
//		MongoDao.deleteDB(tableName);
//		System.out.println("----------------------------------------------------------------------\n");
		
		/****条件删除数据*******/
//		Map<String,Object> mapDelete=new HashMap<>();
//		mapDelete.put("id", "abcedefg01");
//		MongoDao.deleteDB(tableName,mapDelete);
//		System.out.println("----------------------------------------------------------------------\n");
		
		/****按照条件查询数据*******/
//		Map<String,Object> mapSelect=new HashMap<>();
//		mapSelect.put("roleName", "张三");
//		selectAll=MongoDao.selectDB("t_game_role",mapSelect);
//		for(DBObject ob:selectAll){
//			System.out.println(ob);
//		}
//		System.out.println("----------------------------------------------------------------------\n");
		
		/****修改数据*******/
//		Map<String,Object> queryMap=new HashMap<>();//设置查询条件
//		queryMap.put("roleId", 1);
//		
//		BasicDBObject obj=new BasicDBObject();//设置修改后的数据
//		obj.append("roleId", 1);
//		obj.append("roleName", "张三");
//		obj.append("level", 3);
//		MongoDao.updateDB(tableName, queryMap, obj);
//		System.out.println("----------------------------------------------------------------------\n");

		/****查询该表所有数据*******/
		selectAll=MongoDao.selectDB(tableName);
		for(DBObject ob:selectAll){
			System.out.println(ob);
		}
		System.out.println("----------------------------------------------------------------------\n");
		
	}

}
