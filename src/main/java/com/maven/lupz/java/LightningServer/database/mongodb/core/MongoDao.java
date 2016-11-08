package com.maven.lupz.java.LightningServer.database.mongodb.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * 
 * @author lupz
 *
 */
public class MongoDao{
	private static MongoManager manager=MongoManager.getInstance();
	
	/**
	 * 插入数据
	 * @param tableName 表名
	 * @param objList 插入数据对象集合
	 */
	public static void insertDB(String tableName,ISaveInter ...objList){
		long startTime=System.currentTimeMillis();
		DBCollection collection=manager.getDBCollection(tableName);
		System.out.println("插入“"+tableName+"”表前共有"+collection.count()+"条数据");
		DBObject[] Objs=new DBObject[objList.length];
		for(int i=0;i<objList.length;i++){
			Objs[i]=objList[i].getBasicDBObject();
		}
		collection.insert(Objs);
		System.out.println("插入“"+tableName+"”表后共有"+collection.count()+"条数据    总耗时:"+(System.currentTimeMillis()-startTime));
	}
	
	/**
	 * 删除表
	 * @param tableNameList 表名集合
	 */
	public static void deleteDB(String ...tableNameList){
		for(String str:tableNameList){
			manager.getDBCollection(str).drop();
			System.out.println("删除“"+str+"”表成功");
		}
	}
	
	/**
	 * 条件删除
	 * @param tableName 表名
	 * @param map 查询条件  Map<String,Object>
	 */
	public static void deleteDB(String tableName,Map<String,Object> map){
		long startTime=System.currentTimeMillis();
		DBCollection collection=manager.getDBCollection(tableName);
		System.out.println("删除“"+tableName+"”表前的count="+collection.count());
		BasicDBObject cond=new BasicDBObject();
		cond.putAll(map);
		manager.getDBCollection(tableName).remove(cond);
		System.out.println("删除“"+tableName+"”表后的count="+collection.count()+"    总耗时:"+(System.currentTimeMillis()-startTime));
	}
	
	/**
	 * 查询该表所有数据
	 * @param tableName 表名
	 * @return
	 */
	public static List<BasicDBObject> selectDB(String tableName){
		long startTime=System.currentTimeMillis();
		List<BasicDBObject> list=new ArrayList<>();
		DBCursor curAll=manager.getDBCollection(tableName).find();
		while(curAll.hasNext()){
			list.add((BasicDBObject) curAll.next());
		}
		System.out.println("查询“"+tableName+"”表所有数据,共有:"+list.size()+"条数据    总耗时:"+(System.currentTimeMillis()-startTime));
		return list;
	}
	
	/**
	 * 条件查询
	 * @param tableName 表名
	 * @param map 查询条件  Map<String,Object>
	 * @return
	 */
	public static List<DBObject> selectDB(String tableName,Map<String,Object> map){
		long startTime=System.currentTimeMillis();
		List<DBObject> list=new ArrayList<>();
		BasicDBObject cond=new BasicDBObject();
		cond.putAll(map);
		DBCursor curOne=manager.getDBCollection(tableName).find(cond);
		while(curOne.hasNext()){
			list.add(curOne.next());
		}
		System.out.println("条件查询“"+tableName+"”表数据,共有:"+list.size()+"条数据    总耗时:"+(System.currentTimeMillis()-startTime));
		return list;
	}
	
	/**
	 * 条件更新
	 * @param tableName
	 * @param queryMap 查询条件 当满足XXX=???的时候 <变量名，参数>
	 * @param newObj 满足条件的数据改为当前newObj数据
	 */
	public static void updateDB(String tableName,Map<String,Object> queryMap,BasicDBObject newObj){
		long startTime=System.currentTimeMillis();
		BasicDBObject query=new BasicDBObject();
		query.putAll(queryMap);
		manager.getDBCollection(tableName).updateMulti(query, new BasicDBObject("$set",newObj));
		System.out.println("条件更新“"+tableName+"”表数据    总耗时:"+(System.currentTimeMillis()-startTime));
	}
	
}
