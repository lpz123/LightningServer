// 自动生成对象，不要手动修改，以免造成不可知问题!
/**
*@author lupz
*/
package com.maven.lupz.java.LightningServer.database.mongodb.Item;

import com.mongodb.BasicDBObject;
import com.maven.lupz.java.LightningServer.database.mongodb.core.ISaveInter;

public class ItemMon extends BasicDBObject implements ISaveInter {
    private static final long serialVersionUID = 1478768941668L;
    private BasicDBObject basicDBObject=null;
    @Override
    public BasicDBObject getBasicDBObject() {
        return basicDBObject;
    }
    public ItemMon (BasicDBObject basicDBObject) {
        this.basicDBObject=basicDBObject;
    }
    public ItemMon () {
        this.basicDBObject=new BasicDBObject();
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
            return (Object)basicDBObject.get("player_Id");
        }catch(Exception e){
            return "null";
        }
    }
    public void setPlayer_Id (Object player_Id) {
        basicDBObject.append("player_Id",player_Id);
    }
    public long getGoodsId () {
        try{
            return (long)basicDBObject.get("goodsId");
        }catch(Exception e){
            return 0;
        }
    }
    public void setGoodsId (long goodsId) {
        basicDBObject.append("goodsId",goodsId);
    }
    public int getType () {
        try{
            return (int)basicDBObject.get("type");
        }catch(Exception e){
            return 0;
        }
    }
    public void setType (int type) {
        basicDBObject.append("type",type);
    }
    public long getHp () {
        try{
            return (long)basicDBObject.get("hp");
        }catch(Exception e){
            return 0;
        }
    }
    public void setHp (long hp) {
        basicDBObject.append("hp",hp);
    }
    public long getAtk () {
        try{
            return (long)basicDBObject.get("atk");
        }catch(Exception e){
            return 0;
        }
    }
    public void setAtk (long atk) {
        basicDBObject.append("atk",atk);
    }
    public long getDef () {
        try{
            return (long)basicDBObject.get("def");
        }catch(Exception e){
            return 0;
        }
    }
    public void setDef (long def) {
        basicDBObject.append("def",def);
    }
    public long getDex () {
        try{
            return (long)basicDBObject.get("dex");
        }catch(Exception e){
            return 0;
        }
    }
    public void setDex (long dex) {
        basicDBObject.append("dex",dex);
    }
}