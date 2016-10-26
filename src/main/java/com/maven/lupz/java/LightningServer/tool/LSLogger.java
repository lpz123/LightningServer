package com.maven.lupz.java.LightningServer.tool;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class LSLogger {
	private static Logger log=Logger.getLogger(LSLogger.class);
	
	public static void infoLogger(Class<?> clazz,Object objx){
		log.info(clazz.getName()+":"+objx);
	}
	
	public static void errorLogger(Class<?> clazz,Object objx){
		log.error(clazz.getName()+":"+objx);
	}
	
	public static void errorLogger(){
		log.error(System.err);
	}
	
	static{
		PrintStream cPrintStream=null;
		try {
			Date d=new Date();
			cPrintStream=new PrintStream(new FileOutputStream("log/exception.log."+DateFormat.getDateInstance(DateFormat.DEFAULT).format(d),true));
			cPrintStream.println((new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			System.setErr(cPrintStream);
			log.error(System.err);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(cPrintStream);
		}
	}
	
}
