package com.wsyy.proxy;

import java.lang.reflect.Proxy;
import java.util.List;
import com.wsyy.util.Model;
/**
 * 获取JDBC代理实现类方法工厂类
 * @author TP
 */
public class JdbcFactory{

	/**
	 * 执行更新、删除、修改操作
	 *@author: TP 2019年8月2日
	 *@param:  sql执行sql语句，obj所需参数，多参数以逗号(param1,param2...)分隔
	 *@return: int
	 */
	public static int executeUpdate(String sql, Object... obj) {
		IjdbcProxy jdbc = new IjdbcProxyImpl();
		IjdbcProxy o = (IjdbcProxy)Proxy.newProxyInstance(jdbc.getClass().getClassLoader(), new Class[]{IjdbcProxy.class}, new MyProxy(jdbc));
		return o.executeUpdate(sql, obj);
	}

	/**
	 * 执行查询列表操作
	 *@author: TP 2019年8月2日
	 *@param:  sql执行sql语句，obj所需参数，多参数以逗号(param1,param2...)分隔
	 *@return: List<Object>
	 */
	public static List<Object> queryList(String sql, Object... obj) {
		IjdbcProxy jdbc = new IjdbcProxyImpl();
		IjdbcProxy o = (IjdbcProxy)Proxy.newProxyInstance(jdbc.getClass().getClassLoader(), new Class[]{IjdbcProxy.class}, new MyProxy(jdbc));
		return o.queryList(sql, obj);
	}

	/**
	 * 查询单个数据
	 *@author: TP 2019年8月2日
	 *@param:  sql执行sql语句，obj所需参数，多参数以逗号(param1,param2...)分隔
	 *@return: Model
	 */
	public static Model queryOne(String sql, Object... obj) {
		IjdbcProxy jdbc = new IjdbcProxyImpl();
		IjdbcProxy o = (IjdbcProxy)Proxy.newProxyInstance(jdbc.getClass().getClassLoader(), new Class[]{IjdbcProxy.class}, new MyProxy(jdbc));
		return o.queryOne(sql, obj);
	}

}
