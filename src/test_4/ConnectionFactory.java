package test_4;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;



public class ConnectionFactory {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/BathRoom?useUnicode=true&characterEncoding=UTF-8";
	private static String userName="root";
	private static String userPass="123456";
	
	
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
	
	public static Connection getConnection(HttpServletRequest request)
	{
		String user=request.getParameter("userName");
		String pass=request.getParameter("userPass");
		return getConnection(user,pass);
	}
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