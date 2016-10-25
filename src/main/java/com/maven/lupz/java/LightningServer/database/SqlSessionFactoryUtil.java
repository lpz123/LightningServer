package com.maven.lupz.java.LightningServer.database;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.maven.lupz.java.LightningServer.tool.LSLogger;

public class SqlSessionFactoryUtil {

	private static SqlSessionFactory sqlSessionFactory=null;
	
	private static SqlSessionFactoryUtil instance=new SqlSessionFactoryUtil();

	public static SqlSessionFactoryUtil getInstance() {
		return instance;
	}
	
	private SqlSessionFactoryUtil(){};
	
	static{
		String resource="mybatis-config.xml";
		InputStream inputStream=null;
		try {
			inputStream=Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			LSLogger.errorLogger(SqlSessionFactoryUtil.class, e.getMessage());
		}
		if(sqlSessionFactory==null){
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
		}
	}
	
	public SqlSession openSqlSession(){
		return sqlSessionFactory.openSession();
	}
	
}
