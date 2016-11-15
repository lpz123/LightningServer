// 自动生成对象，不要手动修改，以免造成不可知问题!
/**
*@author lupz
*/
package com.maven.lupz.java.LightningServer.database.mongodb.mon.equip;

import com.mongodb.BasicDBObject;
import com.maven.lupz.java.LightningServer.database.mongodb.core.ISaveInter;
import com.maven.lupz.java.LightningServer.database.mongodb.core.EDBType;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;

public class EquipMon extends BasicDBObject implements ISaveInter {
    private static final long serialVersionUID = 1479198519581L;
    private BasicDBObject basicDBObject=null;
    @Override
    public BasicDBObject getBasicDBObject() {
        return basicDBObject;
    }
    public EquipMon (BasicDBObject basicDBObject) {
        this.basicDBObject=basicDBObject;
    }
    private EquipMon () {}
    public static EquipMon create(){
        EquipMon mon=new EquipMon();
        mon.basicDBObject=new BasicDBObject();
        return mon;
    }
    public String toString() {
        return getBasicDBObject().toMap().toString();
    }
    public volatile EDBType dbType=EDBType.NOTHING;
    public synchronized void setDBType(EDBType dbType){
        this.dbType=dbType;
    }
    public EDBType getDBType(){
        return dbType;
    }
    public Object get_id() {
        return basicDBObject.get("_id");
    }
    public void save(){
        MongoDao.insertDB("t_game_EquipMon", this);
    }
    public void delete(){
        MongoDao.deleteDB("t_game_EquipMon", this);
    }
    public void update(){
        MongoDao.updateDB("t_game_EquipMon", this);
        this.setDBType(EDBType.NOTHING);
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
    public int getType () {
        try{
            Object obj=basicDBObject.get("type");
            if(obj!=null){
                return (int)basicDBObject.get("type");
            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    public void setType (int type) {
        basicDBObject.append("type",type);
    }
    public int getEquipColmun () {
        try{
            Object obj=basicDBObject.get("equipColmun");
            if(obj!=null){
                return (int)basicDBObject.get("equipColmun");
            }else{
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    public void setEquipColmun (int equipColmun) {
        basicDBObject.append("equipColmun",equipColmun);
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
    public String getName () {
        try{
            Object obj=basicDBObject.get("name");
            if(obj!=null){
                return (String)basicDBObject.get("name");
            }else{
                return "null";
            }
        }catch(Exception e){
            return "null";
        }
    }
    public void setName (String name) {
        basicDBObject.append("name",name);
    }
}