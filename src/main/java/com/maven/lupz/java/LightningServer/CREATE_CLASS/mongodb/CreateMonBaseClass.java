package com.maven.lupz.java.LightningServer.CREATE_CLASS.mongodb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
			if(data.contains("$class ")){
				sbList=new ArrayList<>();//初始化toString
				
				String[] strS1=data.split("[ ]");
				if(strS1.length<2){
					System.err.println(data+"格式错误,缺少必要的空格");
				}
				String fileName=strS1[1].trim();
				if(fileName.contains("{")){
					fileName=fileName.replace("{", "");
				}
				String[] packageNameSp=fileName.split("[/]");//切割文件路径
				className=packageNameSp[packageNameSp.length-1];//类名
				String packageName=fileName.replace(className, "");//包名
				
				File fileMK = new File("./src/main/java/"+packageName);
				if (!fileMK.exists()) {
					fileMK.mkdirs();
		        }
				System.out.println("创建"+packageName+"文件夹");
				
				file = new File("./src/main/java/"+packageName+className+".java");
				file.createNewFile();
				System.out.println("创建"+className+".java文件");

				in = new FileOutputStream(file);
				
				writeFile(in,"// 自动生成对象，不要手动修改，以免造成不可知问题!\n/**\n*@author lupz\n*/");
				writeFile(in, "\n");//空格 
				String newPackageName="package ";
				for(int i=0;i<packageNameSp.length-1;i++){
					if(i==0){
						newPackageName+=packageNameSp[i];
					}else{
						newPackageName+="."+packageNameSp[i];
					}
				}
				writeFile(in,newPackageName+";\n");//包地址
				writeFile(in, "\n");//空格 
				writeFile(in,"import com.mongodb.BasicDBObject;\n");
				writeFile(in,"import com.maven.lupz.java.LightningServer.database.mongodb.core.ISaveInter;\n");
				writeFile(in,"import com.maven.lupz.java.LightningServer.database.mongodb.core.EDBType;\n");
				writeFile(in,"import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;\n");
				writeFile(in, "\n");//空格 
				writeFile(in, "public class "+className+" extends BasicDBObject implements ISaveInter {\n");
				
				writeFile(in, "    private static final long serialVersionUID = "+System.currentTimeMillis()+"L;\n");//空格
				
				writeFile(in, "    private BasicDBObject basicDBObject=null;\n");//定义变量
				
				writeFile(in, "    public static String tableName=\"t_game_"+className+"\";\n");//数据库表名
				
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
			    
				sbList.add("    public String toString() {\n");//toString
				sbList.add("        String s=getBasicDBObject().toMap().toString();\n");//toString
				
				writeFile(in, "    public volatile EDBType dbType=EDBType.NOTHING;\n");//枚举
				writeFile(in, "    public synchronized void setDBType(EDBType dbType){\n");
				writeFile(in, "        this.dbType=dbType;\n");
				writeFile(in, "    }\n");
				writeFile(in, "    public EDBType getDBType(){\n");
				writeFile(in, "        return dbType;\n");
				writeFile(in, "    }\n");
				
				writeFile(in, "    @Override\n");
				writeFile(in, "    public Object get_id() {\n");//唯一id
				writeFile(in, "        return basicDBObject.get(\"_id\");\n");
				writeFile(in, "    }\n");
				
				writeFile(in, "    @Override\n");
				writeFile(in, "    public void insertDB(){\n");//增
				writeFile(in, "        MongoDao.insertDB(tableName, this);\n");
				writeFile(in, "    }\n");
			    
				writeFile(in, "    @Override\n");
				writeFile(in, "    public void deleteDB(){\n");//删
				writeFile(in, "        MongoDao._deleteData(tableName, this);\n");
				writeFile(in, "    }\n");
			    
				writeFile(in, "    @Override\n");
				writeFile(in, "    public void updateDB(boolean bool){\n");//更新
				writeFile(in, "        if(bool){\n");
				writeFile(in, "            MongoDao._updateDB(tableName, this);\n");
				writeFile(in, "            this.setDBType(EDBType.NOTHING);\n");
				writeFile(in, "        }else{\n");
				writeFile(in, "            this.setDBType(EDBType.UPDATE);\n");
				writeFile(in, "        }\n");
				writeFile(in, "    }\n");
			}else{
				if(!data.equals("}") && data.contains(";")){
					String dataStr=data.split("[;]")[0].trim();//剥离注释
					String[] strS1=dataStr.split(" ");//剥离方法名
					String type=strS1[0];
					String name=strS1[1];
					
					if(data.contains("List")){//List处理
						writeFile(in, "    public java.util."+type+" "+name+"=new java.util.ArrayList<>();\n");//空格
						writeFile(in, "    public java.util."+type+" get"+captureName(name)+" () {\n");//空格
						writeFile(in, "        return "+name+";\n");//空格
						writeFile(in, "    }\n");//空格
						
						sbList.add("        s+=\"\\n    "+name+"----->\\n\";\n");//toString
						sbList.add("        for("+type.replace("List<", "").replace(">","")+" oo:"+name+"){\n");//toString
						sbList.add("            s+=\"    \"+oo.toString()+\"\\n\";\n");//toString
						sbList.add("        }\n");//toString
						sbList.add("        s+=\"    <-----\";\n");//toString
						
						continue;
					}
					
					if(data.contains("Map")){//Map处理
						System.err.println("暂时不支持Map格式");
						continue;
					}

					String methodNameGet="get"+captureName(name);//定义get
					writeFile(in, "    public "+type+" "+methodNameGet+" () {\n");
					writeFile(in, "        try{\n");
					writeFile(in, "            Object obj=basicDBObject.get(\""+name+"\");\n");
					writeFile(in, "            if(obj!=null){\n");
					writeFile(in, "                return ("+type+")basicDBObject.get(\""+name+"\");\n");
					writeFile(in, "            }else{\n");
					if(type.equals("int") || type.equals("long") || type.equals("byte")){
						writeFile(in, "                return 0;\n");
					}else if(type.equals("String") || type.equals("Object")){
						writeFile(in, "                return null;\n");
					}else if(type.equals("float")){
						writeFile(in, "                return 0f;\n");
					}else{
						System.err.println("出现不存在的类型："+type);
						writeFile(in, "                return null;\n");
					}
					writeFile(in, "            }\n");//空格
					writeFile(in, "        }catch(Exception e){\n");
					if(type.equals("int") || type.equals("long") || type.equals("byte")){
						writeFile(in, "            return 0;\n");
					}else if(type.equals("String") || type.equals("Object")){
						writeFile(in, "            return null;\n");
					}else if(type.equals("float")){
						writeFile(in, "            return 0f;\n");
					}else{
						System.err.println("出现不存在的类型："+type);
						writeFile(in, "            return null;\n");
					}
					
					writeFile(in, "        }\n");//空格
					writeFile(in, "    }\n");//空格

					String methodNameSet="set"+captureName(name);//定义set
					writeFile(in, "    public void "+methodNameSet+" ("+type+" "+name+") {\n");
					writeFile(in, "        basicDBObject.append(\""+name+"\","+name+");\n");
					writeFile(in, "    }\n");//空格

					String methodNameRemove="remove"+captureName(name);//定义remove
					writeFile(in, "    public void "+methodNameRemove+" () {\n");
					writeFile(in, "        basicDBObject.remove(\""+name+"\");\n");
					writeFile(in, "    }\n");
				}else if(data.equals("}")){
					for(String s:sbList){//toString
						writeFile(in, s);//toString
					}//toString
					writeFile(in, "        return s;\n");//toString
					writeFile(in, "    }\n");//toString
					
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
