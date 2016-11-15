package com.maven.lupz.java.LightningServer.CREATE_CLASS.mongodb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.maven.lupz.java.LightningServer.database.mongodb.core.EDBType;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.mon.player.PlayerMon;
import com.mongodb.BasicDBObject;

/**
 * 生成Mongodb持久化类<br>
 * config/MonClass<br>
 * @author lupz
 */
public class CreateMonBaseClass {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		create();
	}

	public static void create() throws IOException, InterruptedException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("config/MonClass.txt")));// 源
		String data = null;
		File file=null;
		FileOutputStream in=null;
		String className="";
		List<String> sbList=null;//toString初始化
		while ((data = br.readLine()) != null) {
			if(data.contains("public class ")){
				String[] strS1=data.split("[ ]");
				if(strS1.length!=4){
					System.err.println(data+"格式错误,缺少必要的空格");
				}
				String publicStr=strS1[0];
				String classStr=strS1[1];
				String packageName=strS1[2];
				sbList=new ArrayList<>();
				String[] packageNameS=strS1[2].split("[/]");
				className=packageNameS[packageNameS.length-1];
				packageName=packageName.replaceAll(className, "");
				System.out.println("创建"+packageName+"文件夹");
				System.out.println("创建"+className+".java文件");
				File fileMK = new File("./src/main/java/"+packageName);
				if (!fileMK.exists()) {
					fileMK.mkdirs();
		        }
				file = new File("./src/main/java/"+packageName+className+".java");
				file.createNewFile();
				String newPackageName="package ";
				for(int i=0;i<packageNameS.length-1;i++){
					if(i==0){
						newPackageName=newPackageName+packageNameS[i];
					}else{
						newPackageName=newPackageName+"."+packageNameS[i];
					}
				}
				in = new FileOutputStream(file);
				
				writeFile(in,"// 自动生成对象，不要手动修改，以免造成不可知问题!\n/**\n*@author lupz\n*/");
				writeFile(in, "\n");//空格 
				writeFile(in,newPackageName+";\n");//包地址
				writeFile(in, "\n");//空格 
				writeFile(in,"import com.mongodb.BasicDBObject;\n");
				writeFile(in,"import com.maven.lupz.java.LightningServer.database.mongodb.core.ISaveInter;\n");
				writeFile(in,"import com.maven.lupz.java.LightningServer.database.mongodb.core.EDBType;\n");
				writeFile(in,"import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;\n");
				writeFile(in, "\n");//空格 
				writeFile(in, publicStr+" "+classStr+" "+className+" extends BasicDBObject implements ISaveInter {\n");
				
				writeFile(in, "    private static final long serialVersionUID = "+System.currentTimeMillis()+"L;\n");//空格
				
				writeFile(in, "    private BasicDBObject basicDBObject=null;\n");//定义变量
				
				writeFile(in, "    @Override\n");
				writeFile(in, "    public BasicDBObject getBasicDBObject() {\n");//定义变量
				writeFile(in, "        return basicDBObject;\n");
				writeFile(in, "    }\n");
				
				writeFile(in, "    public "+className+" (BasicDBObject basicDBObject) {\n");//带参初始化
				writeFile(in, "        this.basicDBObject=basicDBObject;\n");
				writeFile(in, "    }\n");
				
				writeFile(in, "    private "+className+" () {}\n");//非带参初始化
				
				writeFile(in, "    public static "+className+" create(){\n");
				writeFile(in, "        "+className+" mon=new "+className+"();\n");
				writeFile(in, "        mon.basicDBObject=new BasicDBObject();\n");
				writeFile(in, "        return mon;\n");
				writeFile(in, "    }\n");
			    	
				writeFile(in, "    public String toString() {\n");//toString
				writeFile(in, "        return getBasicDBObject().toMap().toString();\n");
				writeFile(in, "    }\n");
				
				writeFile(in, "    public volatile EDBType dbType=EDBType.NOTHING;\n");//枚举
				writeFile(in, "    public synchronized void setDBType(EDBType dbType){\n");
				writeFile(in, "        this.dbType=dbType;\n");
				writeFile(in, "    }\n");
				writeFile(in, "    public EDBType getDBType(){\n");
				writeFile(in, "        return dbType;\n");
				writeFile(in, "    }\n");
				
				writeFile(in, "    public Object get_id() {\n");//唯一id
				writeFile(in, "        return basicDBObject.get(\"_id\");\n");
				writeFile(in, "    }\n");
				
				writeFile(in, "    public void save(){\n");//增
				writeFile(in, "        MongoDao.insertDB(\"t_game_"+className+"\", this);\n");
				writeFile(in, "    }\n");
			    
				writeFile(in, "    public void delete(){\n");//删
				writeFile(in, "        MongoDao.deleteDB(\"t_game_"+className+"\", this);\n");
				writeFile(in, "    }\n");
			    
				writeFile(in, "    public void update(){\n");//更新
				writeFile(in, "        MongoDao.updateDB(\"t_game_"+className+"\", this);\n");
				writeFile(in, "        this.setDBType(EDBType.NOTHING);\n");
				writeFile(in, "    }\n");
			}else{
				if(!data.equals("}") && data.contains(";")){
					String[] strS1=data.split("[;]");//剥离注释
					String[] strS2=strS1[0].split("[ ]");//剥离方法名
					String type=strS2[1];
					String name=strS2[2];

					String methodNameGet="get"+captureName(name);//定义get
					writeFile(in, "    public "+type+" "+methodNameGet+" () {\n");//空格
					writeFile(in, "        try{\n");//空格
					writeFile(in, "            Object obj=basicDBObject.get(\""+name+"\");\n");//空格
					writeFile(in, "            if(obj!=null){\n");//空格
					writeFile(in, "                return ("+type+")basicDBObject.get(\""+name+"\");\n");//空格
					writeFile(in, "            }else{\n");//空格
					if(type.equals("int") || type.equals("long") || type.equals("byte")){
						writeFile(in, "                return 0;\n");
					}else if(type.equals("String") || type.equals("Object")){
						writeFile(in, "                return \"null\";\n");
					}else if(type.equals("float")){
						writeFile(in, "                return 0f;\n");
					}else{
						System.err.println("出现不存在的类型："+type);
						writeFile(in, "                return \"null\";\n");
					}
					writeFile(in, "            }\n");//空格
					writeFile(in, "        }catch(Exception e){\n");
					if(type.equals("int") || type.equals("long") || type.equals("byte")){
						writeFile(in, "            return 0;\n");
					}else if(type.equals("String") || type.equals("Object")){
						writeFile(in, "            return \"null\";\n");
					}else if(type.equals("float")){
						writeFile(in, "            return 0f;\n");
					}else{
						System.err.println("出现不存在的类型："+type);
						writeFile(in, "            return \"null\";\n");
					}
					
					writeFile(in, "        }\n");//空格
					writeFile(in, "    }\n");//空格
					
					String methodNameSet="set"+captureName(name);//定义set
					writeFile(in, "    public void "+methodNameSet+" ("+type+" "+name+") {\n");//空格
					writeFile(in, "        basicDBObject.append(\""+name+"\","+name+");\n");//空格
					writeFile(in, "    }\n");//空格

					sbList.add(name+"&"+methodNameGet+"()");
				}else if(data.equals("}")){
					writeFile(in, "}");
				}
			}
			Thread.sleep(20);
		}
		br.close();
		in.close();
	}
	
	private static void writeFile(FileOutputStream in, String str) throws IOException {
		byte[] bt = str.getBytes();
		in.write(bt, 0, bt.length);
	}
	
	public static String captureName(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;
	}
	
}