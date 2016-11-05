package com.maven.lupz.java.LightningServer.database.redis.logic.player;

import java.io.Serializable;

import org.msgpack.annotation.Message;
import org.msgpack.annotation.MessagePackMessage;
import org.msgpack.annotation.Optional;

@MessagePackMessage 
public class PlayerMP{

	@Optional
	public String roleId;
	@Optional
	public String roleName;
	@Optional
	private int level;
	@Optional
	public String a;
	@Optional
	public int b;
	
	
	
	public String toString(){
		return roleId+"|"+roleName+"|"+level+"|"+a+"|"+b;
//		return roleId+"|"+roleName+"|"|"+a+"|"+b;
	}

}
