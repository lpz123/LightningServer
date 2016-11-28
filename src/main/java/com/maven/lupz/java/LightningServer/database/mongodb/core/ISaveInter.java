package com.maven.lupz.java.LightningServer.database.mongodb.core;

import com.mongodb.BasicDBObject;

public interface ISaveInter {

	public BasicDBObject getBasicDBObject();
	
	public Object get_id();
	
	public void insertDB(boolean bool);
	
	public void deleteDB(boolean bool);
	
	/**
	 * @param bool  true马上更新   flase 延迟更新
	 */
	public void updateDB(boolean bool);
   
}
