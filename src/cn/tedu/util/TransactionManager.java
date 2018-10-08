package cn.tedu.util;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * 事务管理器
 * 将与事务相关的操作全部封装在该类里面
 * 实现service与dao层的解耦
 * @author tarena
 *
 */
public class TransactionManager {
	//用于操作每个线程（用户）的map集合管理类对象
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	/**
	 * 开启事务方法
	 */
	public static void startTran(){
		//为当前线程即用户获取连接对象
		Connection conn = JDBCUtils.getConnection();
		//将连接兑现保存在当前线程的map集合中
		tl.set(conn);
		try {
			//基于当前自己的线程开启事务
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 提交事务的方法
	 */
	public static void commit(){
		// 基于当前线程自己的连接对象来提交事务
		Connection conn = tl.get();
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 回滚事务的方法
	 */
	public static void rollback(){
		try {
			//基于当前的自己线程连接对象来回滚事务
			tl.get().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取连接对象的方法--获取的是TM中管理的连接对象
	 */
	public static Connection getConn(){
		return tl.get();
	}
	
	/**
	 * 关闭连接的方法
	 */
	public static void colseConn(){
		Connection conn = tl.get();
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
		//从当前线程中的map集合中删除连接对象
		tl.remove();
	}
}
