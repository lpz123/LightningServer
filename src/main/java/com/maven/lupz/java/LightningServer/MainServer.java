package com.maven.lupz.java.LightningServer;

import org.apache.ibatis.session.SqlSession;

import com.maven.lupz.java.LightningServer.database.SqlSessionFactoryUtil;
import com.maven.lupz.java.LightningServer.database.player.PlayerBean;
import com.maven.lupz.java.LightningServer.database.player.PlayerMapper;
import com.maven.lupz.java.LightningServer.tool.LSLogger;

public class MainServer {
	
	public static void main(String[] args) {
		SqlSession sqlSession=null;
		try{
			sqlSession=SqlSessionFactoryUtil.getInstance().openSqlSession();
			PlayerMapper mapper=sqlSession.getMapper(PlayerMapper.class);
			PlayerBean pb=new PlayerBean();
			pb.setRoleId(1);
			pb.setRoleName("掌上明珠");
			pb.setLevel(1);
			mapper.insertPlayerBean(pb);
			sqlSession.commit();
		}catch(Throwable t){
			LSLogger.errorLogger(MainServer.class, t);
		}finally {
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
	}

}
