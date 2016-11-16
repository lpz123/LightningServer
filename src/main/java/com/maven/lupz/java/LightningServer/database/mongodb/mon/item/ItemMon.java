// 自动生成对象，不要手动修改，以免造成不可知问题!
/**
*@author lupz
*/
package com.maven.lupz.java.LightningServer.database.mongodb.mon.item;

import com.mongodb.BasicDBObject;
import com.maven.lupz.java.LightningServer.database.mongodb.core.ISaveInter;
import com.maven.lupz.java.LightningServer.database.mongodb.core.EDBType;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;

public class ItemMon extends BasicDBObject implements ISaveInter {
    private static final long serialVersionUID = 1479287334722L;
    private BasicDBObject basicDBObject=null;
    public static String tableName="t_game_ItemMon";
    @Override
    public BasicDBObject getBasicDBObject() {
        return basicDBObject;
    }
    public ItemMon (BasicDBObject basicDBObject) {
        this.basicDBObject=basicDBObject;
    }
    private ItemMon () {}
    public static ItemMon create(){
        ItemMon mon=new ItemMon();
        mon.basicDBObject=new BasicDBObject();
        return mon;
    }
    public volatile EDBType dbType=EDBType.NOTHING;
    public synchronized void setDBType(EDBType dbType){
        this.dbType=dbType;
    }
    public EDBType getDBType(){
        return dbType;
    }
    @Override
    public Object get_id() {
        return basicDBObject.get("_id");
    }
    @Override
    public void insertDB(){
        MongoDao.insertDB(tableName, this);
    }
    @Override
    public void deleteDB(){
        MongoDao._deleteData(tableName, this);
    }
    @Override
    public void updateDB(boolean bool){
        if(bool){
            MongoDao._updateDB(tableName, this);
            this.setDBType(EDBType.NOTHING);
        }else{
            this.setDBType(EDBType.UPDATE);
        }
    }
    public Object getPlayer_Id () {
        try{
            Object obj=basicDBObject.get("player_Id");
            if(obj!=null){
                return (Object)basicDBObject.get("player_Id");
            }else{
                return null;
            }
        }catch(Exception e){
            return null;
        }
    }
    public void setPlayer_Id (Object player_Id) {
        basicDBObject.append("player_Id",player_Id);
    }
    public void removePlayer_Id () {
        basicDBObject.remove("player_Id");
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
    public void removeGoodsId () {
        basicDBObject.remove("goodsId");
    }
    public int getUseType () {
        try{
            Object obj=basicDBObject.get("useType");
            if(obj!=null){
                return (int)basicDBObject.get("useType");
            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    public void setUseType (int useType) {
        basicDBObject.append("useType",useType);
    }
    public void removeUseType () {
        basicDBObject.remove("useType");
    }
    public String getName () {
        try{
            Object obj=basicDBObject.get("name");
            if(obj!=null){
                return (String)basicDBObject.get("name");
            }else{
                return null;
            }
        }catch(Exception e){
            return null;
        }
    }
    public void setName (String name) {
        basicDBObject.append("name",name);
    }
    public void removeName () {
        basicDBObject.remove("name");
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
    public void removeHp () {
        basicDBObject.remove("hp");
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
    public void removeAtk () {
        basicDBObject.remove("atk");
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
    public void removeDef () {
        basicDBObject.remove("def");
    }
    public String toString() {
        String s=getBasicDBObject().toMap().toString();
        return s;
    }
}