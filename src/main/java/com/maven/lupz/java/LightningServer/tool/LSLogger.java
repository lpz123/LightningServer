package com.maven.lupz.java.LightningServer.tool;

import org.apache.log4j.Logger;

public class LSLogger {
	private static Logger log=Logger.getLogger(LSLogger.class);
	
	public static void infoLogger(Class<?> clazz,Object objx){
		log.info(clazz.getName()+":"+objx);
	}
	
	public static void errorLogger(Class<?> clazz,Object objx){
		log.error(clazz.getName()+":"+objx);
	}
	
}
