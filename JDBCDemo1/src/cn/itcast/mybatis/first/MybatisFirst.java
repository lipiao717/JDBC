package cn.itcast.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.itcast.mybatis.po.User;

public class MybatisFirst {
	// 根据id查询用户信息，得到一条记录结果
	@Test
	public void findUserByIdTest() throws IOException {
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂,传入配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 通过SqlSession操作数据库
		// 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
		// 第二个参数：指定和映射文件中所匹配的paramenterType类型的参数
		// sqlSession.selectOne结果是与映射文件中所匹配的resultType类型的对象（查询一条数据）
		User user = sqlSession.selectOne("test.findUserById", 1);
		System.out.println(user);
		// 释放资源
		sqlSession.close();

	}

	// 根据用户名称模糊查询用户列表
	@Test
	public void findUserByNameTest() throws IOException {
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂,传入配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// list中的user和映射文件中的resultType所指定的类型一致
		List<User> list = sqlSession.selectList("test.findUserByName", "小明");
		for (User user : list) {
			System.out.println(user);
		}
		// 释放资源
		sqlSession.close();
	}

	// 添加用户信息
	@Test
	public void insertUserTest() throws IOException {
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂,传入配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 插入用户对象
		User user = new User();
		user.setUsername("王小军");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("河南郑州");

		sqlSession.insert("test.insertUser", user);
		sqlSession.commit(); 
		System.out.println(user.getId());
		// 释放资源
		sqlSession.close();
	}

	// 删除用户信息
	@Test
	public void deleteUserTest() throws IOException {
		// mybatis配置文件
		String resource = "SqlMapConfig.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建会话工厂,传入配置文件信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过工厂得到SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();

		sqlSession.delete("test.deleteUser", 30);
		sqlSession.commit();
		// 释放资源
		sqlSession.close();
	}
	// 更新用户信息
		@Test
		public void updateUserTest() throws IOException {
			// mybatis配置文件
			String resource = "SqlMapConfig.xml";
			// 得到配置文件流
			InputStream inputStream = Resources.getResourceAsStream(resource);
			// 创建会话工厂,传入配置文件信息
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			// 通过工厂得到SqlSession
			SqlSession sqlSession = sqlSessionFactory.openSession();
			User user = new User();
			//必须设置id
			user.setId(29);
			user.setAddress("上海");
			user.setSex("2");
			user.setUsername("刘大根");
			user.setBirthday(new Date());

			sqlSession.update("test.updateUser", user);
			sqlSession.commit();
			// 释放资源
			sqlSession.close();
		}
}
