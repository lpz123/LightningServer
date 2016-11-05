package com.maven.lupz.java.LightningServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoConfig;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoMap;
import com.mongodb.DBObject;

public class MongodbTest {

	public static void main(String[] args) {
		LSGameManage lsGM=LSGameManage.getInstance();
		lsGM.init(args);
		
		String[] address={lsGM.getProp().getProperty("mongoAddress").trim()};
		int[] prot={Integer.parseInt(lsGM.getProp().getProperty("mongoPort").trim())};
		String dbname=lsGM.getProp().getProperty("mongo.dbname").trim();
		MongoConfig.setHost(address);
		MongoConfig.setPort(prot);
		MongoConfig.setDbName(dbname);
		
		
		/****增加数据*******/
		
		/****批量增加数据*******/
		
		/****删除单个数据*******/
		
		/****批量删除数据*******/
		
		/****修改单个数据*******/
		
		/****批量修改数据*******/
		
		/****查询所有数据*******/
//		List<DBObject> selectAll=MongoDao.selectDBAll("t_game_role");
//		for(DBObject ob:selectAll){
//			System.out.println(ob);
//		}
		
		/****查询条件数据*******/
		Map<String, Object> map=new HashMap<>();
		map.put("roleId", 1);
		List<DBObject> selectOne=MongoDao.selectDBOne("t_game_role", map);
		for(DBObject ob:selectOne){
			System.out.println(ob);
		}
		
		/**************************下面是mongo3.2********************************************************/
//		MongoClient mongoClient=new MongoClient("192.168.91.4",27017);//创建连接	
//		MongoDatabase mongoDatabase=mongoClient.getDatabase("game01");//跳转到指定库
		
		/**************创建表也有清空整表的意思****************/
//		mongoDatabase.createCollection("t_game_role");//创建表
		
//		MongoCollection<Document> collection = mongoDatabase.getCollection("t_game_role");//获取集合
		
		/**************增****************/
//		Document doc=new Document("title","MongoDB");
//		doc.append("roleId", 1);
//		doc.append("roleName", "张三");
//		doc.append("level", 1);
////		collection.insertOne(doc);//单个插入
//		List<Document> docList=new ArrayList<>();
//		docList.add(doc);
//		collection.insertMany(docList);//批量插入
		
//		/**************查****************/
//		FindIterable<Document> findIterable=collection.find();
//		MongoCursor<Document> cur=findIterable.iterator();
//		while(cur.hasNext()){
//			System.out.println(cur.next());
//		}
		
//		/**************更新****************/
//		collection.updateMany(Filters.eq("level", 1), new Document("$set",new Document("level",3)));//当level==1的时候，level=3
//		
//		FindIterable<Document> findIterable1=collection.find();
//		MongoCursor<Document> cur1=findIterable1.iterator();
//		while(cur1.hasNext()){
//			System.out.println(cur1.next());
//		}
		
		
	}

}
