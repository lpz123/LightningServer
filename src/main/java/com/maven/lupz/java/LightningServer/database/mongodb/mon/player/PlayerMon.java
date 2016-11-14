// 自动生成对象，不要手动修改，以免造成不可知问题!
/**
*@author lupz
*/
package com.maven.lupz.java.LightningServer.database.mongodb.mon.player;

import com.mongodb.BasicDBObject;
import com.maven.lupz.java.LightningServer.database.mongodb.core.ISaveInter;

public class PlayerMon extends BasicDBObject implements ISaveInter {
    private static final long serialVersionUID = 1479131721160L;
    private BasicDBObject basicDBObject=null;
    @Override
    public BasicDBObject getBasicDBObject() {
        return basicDBObject;
    }
    public PlayerMon (BasicDBObject basicDBObject) {
        this.basicDBObject=basicDBObject;
    }
    private PlayerMon () {}
    public static PlayerMon create(){
        PlayerMon mon=new PlayerMon();
        mon.basicDBObject=new BasicDBObject();
        return mon;
    }
    public String toString() {
        return getBasicDBObject().toMap().toString();
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
        return basicDBObject.get("_id");
    }
    public String getUserName () {
        try{
            Object obj=basicDBObject.get("userName");
            if(obj!=null){
                return (String)basicDBObject.get("userName");
            }else{
                return "null";
            }
        }catch(Exception e){
            return "null";
        }
    }
    public void setUserName (String userName) {
        basicDBObject.append("userName",userName);
    }
    public String getRoleName () {
        try{
            Object obj=basicDBObject.get("roleName");
            if(obj!=null){
                return (String)basicDBObject.get("roleName");
            }else{
                return "null";
            }
        }catch(Exception e){
            return "null";
        }
    }
    public void setRoleName (String roleName) {
        basicDBObject.append("roleName",roleName);
    }
    public int getProfession () {
        try{
            Object obj=basicDBObject.get("profession");
            if(obj!=null){
                return (int)basicDBObject.get("profession");
            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    public void setProfession (int profession) {
        basicDBObject.append("profession",profession);
    }
    public long getHp () {
        try{
            Object obj=basicDBObject.get("hp");
            if(obj!=null){
                return (long)basicDBObject.get("hp");
            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    public void setHp (long hp) {
        basicDBObject.append("hp",hp);
    }
    public long getAtk () {
        try{
            Object obj=basicDBObject.get("atk");
            if(obj!=null){
                return (long)basicDBObject.get("atk");
            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    public void setAtk (long atk) {
        basicDBObject.append("atk",atk);
    }
    public long getDef () {
        try{
            Object obj=basicDBObject.get("def");
            if(obj!=null){
                return (long)basicDBObject.get("def");
            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    public void setDef (long def) {
        basicDBObject.append("def",def);
    }
}