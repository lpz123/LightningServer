// 自动生成对象，不要手动修改，以免造成不可知问题!
/**
*@author lupz
*/
package com.maven.lupz.java.LightningServer.database.mongodb.player;

import com.mongodb.BasicDBObject;
import com.maven.lupz.java.LightningServer.database.mongodb.core.ISaveInter;

public class PlayerMon extends BasicDBObject implements ISaveInter {
    private static final long serialVersionUID = 1478687969632L;
    private BasicDBObject basicDBObject=null;
    @Override
    public BasicDBObject getBasicDBObject() {
        return basicDBObject;
    }
    private StringBuffer sb=new StringBuffer();
    public PlayerMon (BasicDBObject basicDBObject) {
        this.basicDBObject=basicDBObject;
    }
    public PlayerMon () {
        this.basicDBObject=new BasicDBObject();
    }
    public String toString() {
        return sb.toString();
    }
    private DBType dbType;
    public enum DBType{
        ADD,UPDATE,DELETE,NOTHING;
    }
    public void setDbType(DBType type){
        this.dbType=type;
    }
    public DBType getDbType(){
        return dbType;
    }
    public Object get_id() {
        return (String)basicDBObject.get("_id");
    }
    public String getRoleName () {
        try{
            return (String)basicDBObject.get("roleName");
        }catch(Exception e){
            return "null";
        }
    }
    public void setRoleName (String roleName) {
        basicDBObject.append("roleName",roleName);
    }
    public int getLevel () {
        try{
            return (int)basicDBObject.get("level");
        }catch(Exception e){
            return 0;
        }
    }
    public void setLevel (int level) {
        basicDBObject.append("level",level);
    }
    public int getProfession () {
        try{
            return (int)basicDBObject.get("profession");
        }catch(Exception e){
            return 0;
        }
    }
    public void setProfession (int profession) {
        basicDBObject.append("profession",profession);
    }
}