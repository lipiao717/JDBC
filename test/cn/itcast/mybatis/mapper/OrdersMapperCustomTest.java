package cn.itcast.mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;
import cn.itcast.mybatis.po.User;

public class OrdersMapperCustomTest {
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
	public void testFindOrdersUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象,mybatis自动生成mapper对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<OrdersCustom> orderCustomList = ordersMapperCustom.findOrdersUser();
		sqlSession.close();
		System.out.println(orderCustomList);
	}

	@Test
	public void testFindOrdersUserResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象,mybatis自动生成mapper对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> orderCustomList = ordersMapperCustom.findOrdersUserResultMap();
		sqlSession.close();
		System.out.println(orderCustomList);
	}

	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象,mybatis自动生成mapper对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<Orders> orderCustomList = ordersMapperCustom.findOrdersAndOrderDetailResultMap();
		sqlSession.close();
		System.out.println(orderCustomList);
	}

	@Test
	public void testFindUserAndItemsResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象,mybatis自动生成mapper对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
		List<User> orderCustomList = ordersMapperCustom.findUserAndItemsResultMap();
		sqlSession.close();
		System.out.println(orderCustomList);
	}

	@Test
	public void testFindOrdersUserLazyLoading() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 创建代理对象,mybatis自动生成mapper对象
		OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

		// 查询订单信息（单表）
		List<Orders> list = ordersMapperCustom.findOrdersUserLazyLoading();
		// 遍历上面的订单列表
		for (Orders orders : list) {
			// 执行getUser()去查询用户信息，实现按需加载
			User user = orders.getUser();
			System.out.println(user);
		}
		sqlSession.close();
		System.out.println(list);
	}

	// 一级缓存测试
	@Test
	public void testCache1() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// 下边查询时用一个sqlSession
		// 第一次发起请求，查询id为1的用户
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);
		user1.setUsername("测试用户2");
		userMapper.updateUser(user1);
		sqlSession.commit();

		// 第一次发起请求，查询id为1的用户
		User user2 = userMapper.findUserById(1);
		System.out.println(user1);
		sqlSession.close();
	}

	// 二级缓存测试
	@Test
	public void testCache2() throws Exception {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();

		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		// 第一次发起请求，查询id为1的用户
		User user1 = userMapper1.findUserById(1);
		System.out.println(user1);
		//这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
		sqlSession1.close();
		
		//使用sqlSession3执行commit操作
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		user1.setUsername("测试用户1");
		userMapper3.updateUser(user1);
		sqlSession3.commit();
		sqlSession3.close();
		

		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		// 第一次发起请求，查询id为1的用户
		User user2 = userMapper2.findUserById(1);
		System.out.println(user1);
		sqlSession2.close();
	}
}
