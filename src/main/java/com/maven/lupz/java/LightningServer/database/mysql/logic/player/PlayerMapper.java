package com.maven.lupz.java.LightningServer.database.mysql.logic.player;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.Alias;

@Mapper
public interface PlayerMapper {
	public void insertPlayerBean(PlayerBean bean);
	public PlayerBean getPlayerBean(long roleId);
	public void updatePlayerBean(PlayerBean bean);
}
