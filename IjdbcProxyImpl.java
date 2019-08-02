package com.wsyy.proxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.wsyy.util.Model;
import base.BaseJDBCControl;
/**
 * JDBC代理接口实现类
 * @author TP
 */
public class IjdbcProxyImpl extends BaseJDBCControl implements IjdbcProxy{

	private Connection conn = null;
	
	private PreparedStatement preparedStatement = null;
	
	private ResultSet resultSet = null;
	
	/**
	 * 获取数据库连接
	 */
	@Override
	public void getConnect() {
		if(null == conn){
			try {
				conn = this.openConnection();
				System.out.println("已连接");
			} catch (SQLException e) {
				System.out.println("打开连接失败");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 执行更新、删除、修改操作
	 */
	@Override
	public int executeUpdate(String sql, Object... obj) {
		try {
			preparedStatement = conn.prepareStatement(sql);
			for (int i=0;i<obj.length;i++){
                //给执行的sql语句里面的？赋值
                preparedStatement.setObject(i+1,obj[i]);
            }
			 //执行增删改的方法
            int i = preparedStatement.executeUpdate();
            //返回行数
            return i;
		} catch (SQLException e) {
			System.out.println("jdbc操作失败");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 执行查询列表操作
	 */
	@Override
	public List<Object> queryList(String sql, Object... obj) {
		List<Object> list = new ArrayList<Object>();
		 try {
	            //用PreparedStatement对象去执行sql代码
	            preparedStatement = conn.prepareStatement(sql);
	            //不定长参数产生的是一个数组所以我们要用for循环去遍历它的长度（不定长参数可以为空）
	            for (int i=0;i<obj.length;i++){
	                //给执行的sql语句里面的？赋值
	                preparedStatement.setObject(i+1,obj[i]);
	            }
	            //执行查询的方法
	            resultSet = preparedStatement.executeQuery();
	            ResultSetMetaData metaData = resultSet.getMetaData();
	    		int columnCount = metaData.getColumnCount();
	    		while(resultSet.next()){
	    			Map<String, Object> map = new HashMap<String,Object>();
	    			for(int i=1; i<=columnCount; i++){
	    				map.put(metaData.getColumnName(i), resultSet.getObject(i) != null ? resultSet.getObject(i) : "");
	    			}
	    			list.add(map);
	    		}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 return list;
	}

	/**
	 * 关闭数据连接
	 */
	@Override
	public void close() {
		if(resultSet!=null){
            try {
                resultSet.close();
                System.out.println("resultSet-已关闭");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
                System.out.println("preparedStatement-已关闭");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
                System.out.println("conn-已关闭");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

	/**
	 * 查询单个数据
	 */
	@Override
	public Model queryOne(String sql, Object... obj) {
		Model model = new Model();
		 try {
	            //用PreparedStatement对象去执行sql代码
	            preparedStatement = conn.prepareStatement(sql);
	            //不定长参数产生的是一个数组所以我们要用for循环去遍历它的长度（不定长参数可以为空）
	            for (int i=0;i<obj.length;i++){
	                //给执行的sql语句里面的？赋值
	                preparedStatement.setObject(i+1,obj[i]);
	            }
	            //执行查询的方法
	            resultSet = preparedStatement.executeQuery();
	            ResultSetMetaData metaData = resultSet.getMetaData();
	    		int columnCount = metaData.getColumnCount();
	    		while(resultSet.next()){
	    			for(int i=1; i<=columnCount; i++){
	    				model.put(metaData.getColumnName(i), resultSet.getObject(i) != null ? resultSet.getObject(i) : "");
	    			}
	    		}
	        } catch (SQLException e) {
	            e.printStackTrace();
	            //捕获到异常则返回-1
	        }
		 return model;
	}

}
