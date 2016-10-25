package com.maven.lupz.java.LightningServer.tool;

import org.apache.log4j.Logger;

public class LSLogger {
	private static Logger log=Logger.getLogger(LSLogger.class);
	
	public static void infoLogger(Class clazz,String message){
		log.info(clazz.getName()+":"+message);
	}
	
	public static void errorLogger(Class clazz,String message){
		log.error(clazz.getName()+":"+message);
	}
	
}
