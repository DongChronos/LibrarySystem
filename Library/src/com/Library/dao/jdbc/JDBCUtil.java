package com.Library.dao.jdbc;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库连接静态类
 * @author ubuntu
 *
 */
public class JDBCUtil implements Serializable {

	/**
	 * 序列号，不可更改
	 */
	private static final long serialVersionUID = -619885352631812042L;

	private static String driverName = null;
	private static String dbURL = null;
	private static String userName = null;
	private static String userPwd = null;
	
	/**
	 * 加载jdbc
	 */
	static
	{
		loadProperties();
		try
		{
			Class.forName(driverName);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 载入数据库链接参数
	 */
	private static void loadProperties()
	{
		Properties prop=new Properties();
		
		try
		{
			prop.load(new FileInputStream(Thread.currentThread()
					.getContextClassLoader().getResource("").getPath() + "jdbc.properties"));
			driverName = prop.getProperty("driverClassName");
			dbURL = prop.getProperty("url");
			userName = prop.getProperty("user");
			userPwd = prop.getProperty("password");
		}
		catch(IOException e)
		{
			System.out.println("数据库参数加载错误");
			e.printStackTrace();
		}
	}
	
	/**
	 * 建立数据库连接
	 * @return 返回Connection对象
	 */
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection(dbURL,userName,userPwd);
		}
		catch(SQLException e)
		{
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭数据库对象连接
	 * @param rs 关闭ResultSet
	 * @param stmt 关闭 Statement
	 * @param conn 关闭 Connection 
	 */
	public static void close(ResultSet rs,Statement stmt,Connection conn)
	{
		try
		{
			if(rs != null)
			{
				rs.close();
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			if(stmt != null)
			{
				stmt.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			if(conn != null)
			{
				conn.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据对象类型关闭数据库连接
	 * @param obj
	 */
	public static void close(Object obj)
	{
		try
		{
			if(obj instanceof ResultSet)
			{
				((ResultSet) obj).close();
			}
			else if(obj instanceof Statement)
			{
				((Statement) obj).close();
			}
			else if(obj instanceof Connection)
			{
				((Connection) obj).close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭ResultSet
	 * @param resultSets
	 */
	public static void close(ResultSet...resultSets)
	{
		for(ResultSet rs:resultSets)
		{
			if(rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 打印结果集
	 * @param rs
	 */
	public static void printResultSet(ResultSet rs)
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			ResultSetMetaData meta = rs.getMetaData();
			int clos = meta.getColumnCount();
			while(rs.next())
			{
				for(int i = 1;i < clos;i++)
				{
					sb.append(meta.getColumnName(i) + "=");
					sb.append(rs.getString(i) + "  ");
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
