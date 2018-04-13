/**
 * 数据库连接类，根据输入信息对数据库进行试连接，如果成果返回Connection对象
 * @author spring3shine
 * @Time 	2018/04/13
 */

package test_4;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;



public class ConnectionFactory {
	/** Java数据库驱动. */
	private static String driver="com.mysql.jdbc.Driver";
	
	/** mysql的数据库连接地址. */
	private static String url="jdbc:mysql://localhost:3306/BathRoom?useUnicode=true&characterEncoding=UTF-8";
	
	/** 不那么重要，但作为第三个连接方法的参数. */
	private static String userName="root";
	private static String userPass="123456";
	
	/**
	 * 尝试连接local数据库
	 * 根据输入参数进行数据库连接，返回Connection对象
	 * @param  user [in]  [string]  mysql用户名
	 * @param  pass [in]  [string]  mysql密码
	 * @return Conection类型，成功则返回可用的连接，失败返回null
	*/
	public static Connection getConnection(String user,String pass)
	{
		try {
			Class.forName(driver);
			Connection ret=null;
			ret=DriverManager.getConnection(url,user,pass);
			if(ret!=null)System.out.println("Connect Success!");
			else System.out.println("Connect Error!");
			return ret;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 尝试连接local数据库
	 * 根据输入参数进行数据库连接，返回Connection对象
	 * @param  request [in]  [HttpServletRequest]  根据网络请求获取用户名和密码，再尝试连接
	 * @return Conection类型，成功则返回可用的连接，失败返回null
	*/
	public static Connection getConnection(HttpServletRequest request)
	{
		String user=request.getParameter("userName");
		String pass=request.getParameter("userPass");
		return getConnection(user,pass);
	}
	
	/**
	 * 尝试连接local数据库
	 * 根据静态常量进行数据库连接，返回Connection对象
	 * @return Conection类型，成功则返回可用的连接，失败返回null
	*/
	public static Connection getConnection() {
		return getConnection(userName,userPass);
	}
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = getConnection("root","123456");
		
		String sql="select * from BathRoom";
		java.sql.Statement pstmt=null;
		ResultSet rs=null;
		
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery(sql);
		
		
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2));
		}
		
		
		return;
	}
}