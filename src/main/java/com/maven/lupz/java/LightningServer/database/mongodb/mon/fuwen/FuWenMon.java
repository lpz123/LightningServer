// 自动生成对象，不要手动修改，以免造成不可知问题!
/**
*@author lupz
*/
package com.maven.lupz.java.LightningServer.database.mongodb.mon.fuwen;

import com.mongodb.BasicDBObject;
import com.maven.lupz.java.LightningServer.database.mongodb.core.ISaveInter;

public class FuWenMon extends BasicDBObject implements ISaveInter {
    private static final long serialVersionUID = 1479131721758L;
    private BasicDBObject basicDBObject=null;
    @Override
    public BasicDBObject getBasicDBObject() {
        return basicDBObject;
    }
    public FuWenMon (BasicDBObject basicDBObject) {
        this.basicDBObject=basicDBObject;
    }
    private FuWenMon () {}
    public static FuWenMon create(){
        FuWenMon mon=new FuWenMon();
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
    public Object getPlayer_Id () {
        try{
            Object obj=basicDBObject.get("player_Id");
            if(obj!=null){
                return (Object)basicDBObject.get("player_Id");
            }else{
                return "null";
            }
        }catch(Exception e){
            return "null";
        }
    }
    public void setPlayer_Id (Object player_Id) {
        basicDBObject.append("player_Id",player_Id);
    }
    public long getGoodsId () {
        try{
            Object obj=basicDBObject.get("goodsId");
            if(obj!=null){
                return (long)basicDBObject.get("goodsId");
            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    public void setGoodsId (long goodsId) {
        basicDBObject.append("goodsId",goodsId);
    }
    public Object getEquipId () {
        try{
            Object obj=basicDBObject.get("equipId");
            if(obj!=null){
                return (Object)basicDBObject.get("equipId");
            }else{
                return "null";
            }
        }catch(Exception e){
            return "null";
        }
    }
    public void setEquipId (Object equipId) {
        basicDBObject.append("equipId",equipId);
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