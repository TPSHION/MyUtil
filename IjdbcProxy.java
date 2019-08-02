package com.wsyy.proxy;

import java.util.List;

import com.wsyy.util.Model;
/**
 * JDBC相关操作代理接口
 * @author TP
 */
public interface IjdbcProxy {

	//获得连接
	public void getConnect();
	
	//执行更新、修改、删除操作
	public int executeUpdate(String sql,Object...obj);
	
	//查询数据列表
	public List<Object> queryList(String sql,Object...obj);
	
	//查询单个数据
	public Model queryOne(String sql,Object...obj);
	
	//关闭连接
	public void close();
}
