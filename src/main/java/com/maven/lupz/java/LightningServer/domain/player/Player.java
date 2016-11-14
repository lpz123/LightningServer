package com.maven.lupz.java.LightningServer.domain.player;

import com.maven.lupz.java.LightningServer.database.mongodb.mon.player.PlayerMon;

public class Player {

	private PlayerMon playerMon;

	public PlayerMon getPlayerMon() {
		return playerMon;
	}

	public void setPlayerMon(PlayerMon playerMon) {
		this.playerMon = playerMon;
	}
	
	
}
