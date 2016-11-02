package com.maven.lupz.java.LightningServer;

import org.apache.ibatis.session.SqlSession;

import com.maven.lupz.java.LightningServer.database.mysql.logic.SqlSessionFactoryUtil;
import com.maven.lupz.java.LightningServer.database.mysql.logic.player.PlayerBean;
import com.maven.lupz.java.LightningServer.database.mysql.logic.player.PlayerMapper;

public class MybatisTest {

	public static void main(String[] args) {
		LSGameManage.getInstance().init(args);// 初始化服务器

		SqlSession sqlSession = null;

		//单一插入
//		long time=System.currentTimeMillis();
//		for(int i=0;i<10000;i++){
//			sqlSession = SqlSessionFactoryUtil.getInstance().openSqlSession();
//			PlayerMapper mapper = sqlSession.getMapper(PlayerMapper.class);
//			PlayerBean pb = new PlayerBean();
//			pb.setRoleName("掌上明珠");
//			pb.setLevel(1);
//			mapper.insertPlayerBean(pb);
//			sqlSession.commit();
//		}
//		System.out.println(System.currentTimeMillis()-time);
		
		//批量插入
		long time=System.currentTimeMillis();
		sqlSession = SqlSessionFactoryUtil.getInstance().openSqlSession();
		for(int i=0;i<10000;i++){
			PlayerMapper mapper = sqlSession.getMapper(PlayerMapper.class);
			PlayerBean pb = new PlayerBean();
			pb.setRoleName("掌上明珠");
			pb.setLevel(1);
			mapper.insertPlayerBean(pb);
		}
		sqlSession.commit();
		System.out.println(System.currentTimeMillis()-time);

		if (sqlSession != null) {
			sqlSession.close();
		}

	}

}
