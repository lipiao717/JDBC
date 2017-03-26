package cn.itcast.mybatis.mapper;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.User;
import cn.itcast.mybatis.po.UserCustom;
import cn.itcast.mybatis.po.UserQueryVo;

public class UserMapperTest {
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
	//用户信息综合查询总数
	@Test
	public void testFindUserCount() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象,mybatis自动生成mapper对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("张三丰");
		userQueryVo.setUserCustom(userCustom);
		
		int count = userMapper.findUserCount(userQueryVo);
		sqlSession.close();
		System.out.println(count);
	}
	//用户信息综合查询
	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象,mybatis自动生成mapper对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("张三丰");
		userQueryVo.setUserCustom(userCustom);
		
		List<UserCustom> list = userMapper.findUserList(userQueryVo);
		sqlSession.close();
		System.out.println(list);
	}
	//根据id查询用户
	@Test
	public void testFindUserById() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象,mybatis自动生成mapper对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		User user = userMapper.findUserById(22);
		sqlSession.close();
		System.out.println(user);
	}
	//模糊查询数据库
	@Test
	public void testFindUserByName() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象,mybatis自动生成mapper对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		List<User> list = userMapper.findUserByName("小明");
		sqlSession.close();
		System.out.println(list);
	}
	//新增用户数据
	@Test
	public void testInsertUser() throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象,mybatis自动生成mapper对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		User user = new User();
		user.setUsername("刘小红");
		user.setAddress("河南邓州");
		user.setBirthday(new Date());
		user.setSex("2");
		userMapper.insertUser(user);
		sqlSession.commit();
		sqlSession.close();
	}
	//删除用户数据
	@Test
	public void testDeleteUser() throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象,mybatis自动生成mapper对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		userMapper.deleteUser(27);
		sqlSession.commit();
		sqlSession.close();
	}
	//更新用户数据
	@Test
	public void testUpdateUser() throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象,mybatis自动生成mapper对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用userMapper的方法
		User user = new User();
		user.setUsername("艾晓明");
		user.setAddress("安徽安庆");
		user.setBirthday(new Date());
		user.setSex("2");
		user.setId(1);
		userMapper.updateUser(user);
		sqlSession.commit();
		sqlSession.close();
	}
}
