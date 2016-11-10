package com.maven.lupz.java.LightningServer.database.mongodb.core;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

/**
 * 
 * @author lupz
 * @Description mongo连接管理池类对象
 *
 */
public class MongoManager {
	private static Mongo mongo;
	private DB db;
	
	private static MongoManager instance=null;

	public synchronized static MongoManager getInstance() {
		if(instance ==null){
			instance=new MongoManager(MongoConfig.getDbName(), MongoConfig.getUserName(), MongoConfig.getPwd());
		}
		return instance;
	}

	/**
	 * @param dbName
	 * @param userName
	 * @param pwd
	 *        实例化dbName一个DB
	 */
	private MongoManager(String dbName, String userName, String pwd) {
		if (dbName == null || "".equals(dbName)) {
			throw new NumberFormatException("dbName is null");
		}
		init();
		db = mongo.getDB(dbName);
		if (MongoConfig.isAuthentication() && !db.isAuthenticated()) {
			if (userName == null || "".equals(userName)) {
				throw new NumberFormatException("userName is null");
			}
			if (pwd == null || "".equals(pwd)) {
				throw new NumberFormatException("pwd is null");
			}
			db.authenticate(userName, pwd.toCharArray());
		}
	}

	private MongoManager() {
	
	}

	/**
	 * @param tableName
	 * @return
	 * @Description: 获取表tableName的链接DBCollection
	 */
	public DBCollection getDBCollection(String tableName) {
		return db.getCollection(tableName);
	}

	/**
	 * @Description: mongo连接池初始化
	 */
	private void init() {
		if (MongoConfig.getHost() == null || MongoConfig.getHost().length == 0) {
			throw new NumberFormatException("host is null");
		}
		if (MongoConfig.getPort() == null || MongoConfig.getPort().length == 0) {
			throw new NumberFormatException("port is null");
		}
		if (MongoConfig.getHost().length != MongoConfig.getPort().length) {
			throw new NumberFormatException("host's length is not equals port's length");
		}
		try {
			// 服务列表
			List<ServerAddress> replicaSetSeeds = new ArrayList<ServerAddress>();
			for (int i = 0; i < MongoConfig.getHost().length; i++) {
				replicaSetSeeds.add(new ServerAddress(MongoConfig.getHost()[i], MongoConfig.getPort()[i]));
			}
			// 连接池参数设置
			MongoOptions options = new MongoOptions();
			options.connectionsPerHost = MongoConfig.getConnectionsPerHost();
			options.threadsAllowedToBlockForConnectionMultiplier = MongoConfig.getThreadsAllowedToBlockForConnectionMultiplier();
			
			mongo = new Mongo(replicaSetSeeds, options);
			// 从服务器可读
			mongo.setReadPreference(ReadPreference.SECONDARY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
