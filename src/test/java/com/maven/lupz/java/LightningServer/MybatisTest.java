package com.maven.lupz.java.LightningServer;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.maven.lupz.java.LightningServer.database.mysql.logic.core.SqlSessionFactoryUtil;
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
//		long t1=System.currentTimeMillis();
//		long a=0;
//		sqlSession = SqlSessionFactoryUtil.getInstance().openSqlSession();
//		for(int i=0;i<10000;i++){
//			PlayerMapper mapper = sqlSession.getMapper(PlayerMapper.class);
//			PlayerBean pb = new PlayerBean();
//			pb.setRoleName("中文大话蜀山");
//			pb.setLevel(1);
//			mapper.insertPlayerBean(pb);
//			if(i==5000){
//				a=pb.getRoleId();
//			}
//		}
//		long t2=System.currentTimeMillis();
//		sqlSession.commit();
//		long t3=System.currentTimeMillis();
//		System.out.println((t2-t1)+"|"+(t3-t2)+"|"+(t3-t1));
//		
//		PlayerMapper mapper = sqlSession.getMapper(PlayerMapper.class);
//		System.out.println(mapper.getPlayerBean(a).getRoleName());
//
//		if (sqlSession != null) {
//			sqlSession.close();
//		}
		
		//批量更新
		long t11=System.currentTimeMillis();
		List<PlayerBean> list=new ArrayList<>();
		sqlSession = SqlSessionFactoryUtil.getInstance().openSqlSession();
		for(int i=0;i<10000;i++){
			PlayerMapper mapper = sqlSession.getMapper(PlayerMapper.class);
			PlayerBean pb = new PlayerBean();
			pb.setRoleName("中文大话蜀山");
			pb.setLevel(1);
			mapper.insertPlayerBean(pb);
			list.add(pb);
		}
		long t12=System.currentTimeMillis();
		sqlSession.commit();
		long t13=System.currentTimeMillis();
		if (sqlSession != null) {
			sqlSession.close();
		}
		System.out.println((t12-t11)+"+"+(t13-t12)+"="+(t13-t11));
		
		long t1=System.currentTimeMillis();
		sqlSession = SqlSessionFactoryUtil.getInstance().openSqlSession();
		for(PlayerBean pb:list){
			PlayerMapper mapper = sqlSession.getMapper(PlayerMapper.class);
			pb.setLevel(50);
			pb.setRoleName("阿拉蕾");
			mapper.updatePlayerBean(pb);
		}
		long t2=System.currentTimeMillis();
		sqlSession.commit();
		long t3=System.currentTimeMillis();
		if (sqlSession != null) {
			sqlSession.close();
		}
		System.out.println((t2-t1)+"+"+(t3-t2)+"="+(t3-t1));
	}

}
