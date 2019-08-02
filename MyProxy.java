package com.wsyy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * JDBC代理接口与实现类处理类
 * 使用JDK动态代理
 * 功能：
 * 1.打开数据库连接
 * 2.执行数据库操作
 * 3.关闭数据库连接
 * @author TP
 */
public class MyProxy implements InvocationHandler{

	//JDBC相关操作代理接口
	private IjdbcProxy target = null;
	
	public MyProxy(){};
	
	//初始化代理接口实现
	public MyProxy(IjdbcProxy target){
		this.target = target;	
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long l = System.currentTimeMillis();//记录操作花费时间
	    target.getConnect();//获得数据库连接
        Object object = method.invoke(target, args);//需要传递要代理的接口以及参数
        target.close();//关闭连接
        long l1 = System.currentTimeMillis();
        System.out.println("总用毫秒数:"+(l1-l)+"ms");
        return object;
	}

}
