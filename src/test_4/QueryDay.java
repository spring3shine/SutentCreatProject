/**
 * Servlet implementation class QueryDay
 * 
 * @Parameter:
 * 		userName --String
 * 		userPass --String
 * 		searchDay--String
 * @Response:
 * 		message -- String as Json(eg:
 * 			{"Message": [{"id":"1","day":"2018-04-06","clock":"18:08:00","count":"4","position":"NorthGirl"}]}
 * 		)
 * 
 * @Contral:
 * if 
 * 		userName & userPass match as Administor
 * then
 * 		list all message from database BathRoom  where Day==searchDay
 * 
 * 
 * @Interface:
 * 		http://localhost:8080/test4/QueryDay?userName=root&userPass=123456&searchDay=2018-04-06
 * 		http://47.94.223.169:8080/test4/QueryDay?userName=root&userPass=123456&searchDay=2018-04-06
 * 
 * */

package test_4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/QueryDay")
public class QueryDay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryDay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * 初始化response , out
		 */
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		/*
		 * 连接数据库
		 */
		Connection conn=ConnectionFactory.getConnection(request);
		if(conn==null)out.append("Connect Failed");
		
		/*
		 * 获取查询日期，创建preparedStatement
		 */
		String strDate=request.getParameter("searchDay");
		Date day=StrToMysqlDate.strToSqlDate(strDate);
		System.out.println(day);
		String sql="select * from BathRoom where Day=?;";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			//System.out.println(preparedStatement);
			preparedStatement.setDate(1, day);
			//System.out.println(preparedStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * 根据preparedStatement ，　输出message-Json.
		 */
		ResultSet rs;
		try {
			//System.out.println(preparedStatement);
			rs = SqlOperator.sqlQuery(conn, preparedStatement);
			StringBuffer ret=SqlOperator.toJson(rs);
			out.append(ret);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * 关闭释放
		 */
		try {
			conn.close();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
