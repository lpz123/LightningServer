package com.maven.lupz.java.LightningServer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoConfig;
import com.maven.lupz.java.LightningServer.database.mysql.logic.core.SqlSessionFactoryUtil;
import com.maven.lupz.java.LightningServer.tool.LSLogger;

public class LSGameManage {
	
	private LSGameManage(){}
	
	private static final LSGameManage instance=new LSGameManage();

	public static LSGameManage getInstance() {
		return instance;
	}
	
	private Properties prop=null;//配置文件读取

	public Properties getProp() {
		return prop;
	}

	/**
	 * 初始化对象数据
	 * @param args
	 * @warning 每个方法块的注释标识了顺序，严格按照顺序执行
	 */
	public void init(String[] args){
		/***********NO.1 堆栈数据日志****************/
//		PrintStream cPrintStream = null;
//		try {
//			Date d = new Date();
//			cPrintStream = new PrintStream(new FileOutputStream("log/exception.log." + DateFormat.getDateInstance(DateFormat.DEFAULT).format(d), true));
//			cPrintStream.println((new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
//			System.setErr(cPrintStream);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		/***********NO.2 properties初始化****************/
		prop = new Properties();
		try {
			prop.load(Object.class.getResourceAsStream("/LS.properties"));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/***********NO.3 mongodb初始化****************/
		String[] address={this.getProp().getProperty("mongoAddress").trim()};
		int[] prot={Integer.parseInt(this.getProp().getProperty("mongoPort").trim())};
		String dbname=this.getProp().getProperty("mongo.dbname").trim();
		MongoConfig.setHost(address);
		MongoConfig.setPort(prot);
		MongoConfig.setDbName(dbname);
		
		/***********通信初始化****************/
	}

}
