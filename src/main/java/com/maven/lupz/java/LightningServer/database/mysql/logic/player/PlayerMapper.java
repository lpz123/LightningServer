package com.maven.lupz.java.LightningServer.database.mysql.logic.player;

public interface PlayerMapper {
	public void insertPlayerBean(PlayerBean bean);
	public PlayerBean getPlayerBean(long roleId);
	public void updatePlayerBean(PlayerBean bean);
}
