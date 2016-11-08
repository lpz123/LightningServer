package com.maven.lupz.java.LightningServer;

import com.maven.lupz.java.LightningServer.GameTest;
import com.maven.lupz.java.LightningServer.database.mongodb.core.MongoDao;
import com.maven.lupz.java.LightningServer.database.mongodb.player.PlayerMon;

public class TTTT implements Runnable{

	@Override
	public void run() {
		int i=0;
		while(i<1000){
			i++;
			PlayerMon pm=new PlayerMon();
			pm.setRoleName("2");
			pm.setLevel(1);
			pm.setProfession(1);
			
			MongoDao.insertDB("t_game_role", pm);
		}
		
	}

}
