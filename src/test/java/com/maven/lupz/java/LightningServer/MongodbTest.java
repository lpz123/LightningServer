package com.maven.lupz.java.LightningServer;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongodbTest {

	public static void main(String[] args) {
		LSGameManage.getInstance().init(args);
		
		MongoClient mongoClient=new MongoClient("192.168.91.4",27017);//创建连接	
		MongoDatabase mongoDatabase=mongoClient.getDatabase("game01");//跳转到指定库
		
		/**************创建表****************/
//		mongoDatabase.createCollection("t_game_role");//创建表
		
		MongoCollection<Document> collection = mongoDatabase.getCollection("t_game_role");//获取集合
		
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
		FindIterable<Document> findIterable=collection.find();
		MongoCursor<Document> cur=findIterable.iterator();
		while(cur.hasNext()){
			System.out.println(cur.next());
		}
		
//		/**************更新****************/
		collection.updateMany(Filters.eq("level", 1), new Document("$set",new Document("level",3)));//当level==1的时候，level=3
		
		FindIterable<Document> findIterable1=collection.find();
		MongoCursor<Document> cur1=findIterable1.iterator();
		while(cur1.hasNext()){
			System.out.println(cur1.next());
		}
		
		
	}

}
