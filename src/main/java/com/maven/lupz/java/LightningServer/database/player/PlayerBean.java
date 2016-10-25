package com.maven.lupz.java.LightningServer.database.player;

public class PlayerBean {

	private long roleId;
	private String roleName;
	private int level;
	
	public String toString(){
		return roleId+"|"+roleName+"|"+level;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
