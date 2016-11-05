package com.maven.lupz.java.LightningServer.database.mongodb.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoDao {
	private static MongoManager manager=MongoManager.getInstance();
	
	public static void insert(String tabName,Object obj){
		
	}

	/**
	 * 查询
	 * @param tableName 表名
	 * @param eSelectMongo 查询类型的枚举   ALL全部数据   WHERE条件查询
	 * @param map 条件查询map 可以为null
	 * @return
	 */
//	public static List<DBObject> selectDB(String tableName,ESelectMongo eSelectMongo,Map<String,String> map){
//		List<DBObject> list=new ArrayList<>();
//		switch(eSelectMongo){
//		case ALL:
//			DBCursor curAll=manager.getDBCollection(tableName).find();
//			while(curAll.hasNext()){
//				list.add(curAll.next());
//			}
//			break;
//		case WHERE:
////			BasicDBList condList = new BasicDBList(); 
//			BasicDBObject cond=new BasicDBObject();
////			cond.put(key, val)
//			for(String str:map.keySet()){
//				cond.put(str, map.get(str));
//			}
////			condList.add(cond);
//			DBCursor curOne=manager.getDBCollection(tableName).find(cond);
//			while(curOne.hasNext()){
//				list.add(curOne.next());
//			}
//			break;
//		}
//		return list;
//	}
	
	/**
	 * 查询全部
	 * @param tableName 表名
	 * @return
	 */
	public static List<DBObject> selectDBAll(String tableName){
		List<DBObject> list=new ArrayList<>();
		DBCursor curAll=manager.getDBCollection(tableName).find();
		while(curAll.hasNext()){
			list.add(curAll.next());
		}
		return list;
	}
	
	/**
	 * 查询单条数据
	 * @param tableName 表名
	 * @param map 查询条件  Map<String,Object>
	 * @return
	 */
	public static List<DBObject> selectDBOne(String tableName,Map<String,Object> map){
		List<DBObject> list=new ArrayList<>();
//		BasicDBList condList = new BasicDBList(); 
		BasicDBObject cond=new BasicDBObject();
		cond.putAll(map);
//		cond.put(key, val)
//		for(String str:map.keySet()){
//			cond.put(str, map.get(str));
//		}
//		condList.add(cond);
		DBCursor curOne=manager.getDBCollection(tableName).find(cond);
		while(curOne.hasNext()){
			list.add(curOne.next());
		}
		return list;
	}
	
//	public enum ESelectMongo{
//		ALL,WHERE;
//	}
}
