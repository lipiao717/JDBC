package cn.itcast.mybatis.dao;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.User;

public class UserDaoImpTest {
	private SqlSessionFactory sqlSessionFactory;

	// 此方法是在执行testFindUserById之前执行
	@Before
	public void setUp() throws Exception {
		// 创建SQLSessionFactory
		String resource = "SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂,传入配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}

	@Test
	public void testFindUserById() throws Exception {
		//创建userDao的对象
		UserDao userDao = new UserDaoImp(sqlSessionFactory);
//		UserDaoImp userDao = new UserDaoImp(sqlSessionFactory);
		//调用UserDao的方法
		User user = userDao.findUserById(22);
		System.out.println(user);
	}

}
