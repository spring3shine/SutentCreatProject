/**
 * Servlet implementation class QueryDay
 * 
 * @Parameter:
 * 		userName --String
 * 		userPass --String
 * 		Day		 --String(2018-4-6)
 * 		Time	 --String(16:03:24)
 * 		Count  	 --Int
 * 		Position --String
 * @Response:
 * 		out.append("Insert Success");
 * 
 * @Contral:
 * if 
 * 		userName & userPass match as Administor
 * then
 * 		Insert Into BathRoom as the message;
 * 
 * 
 * @Interface:
 * 		http://localhost:8080/test4/Listen?userName=root&userPass=123456&Day=2018-4-6&Time=18:08:00&Count=4&Position=NorthGirl
 * 		http://47.94.223.169:8080/test4/Listen?userName=root&userPass=123456&Day=2018-4-6&Time=18:08:00&Count=4&Position=NorthGirl
 * 
 * */

package test_4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Listen
 */
@WebServlet("/Listen")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * 初始化 out , 连接数据库
		 */
		PrintWriter out=response.getWriter();
		Connection conn=ConnectionFactory.getConnection(request);
		if(conn==null) {
			out.append("Login False!");out.println("");return;
		}
		
		/*
		 * 根据request ，　新建 message
		 */
		Date day=StrToMysqlDate.strToSqlDate(request.getParameter("Day"));
		Time clock=StrToMysqlDate.strToSqlTime(request.getParameter("Time"));
		int count=Integer.valueOf(request.getParameter("Count"));
		String position=request.getParameter("Position");
		Message m=new Message(day,clock,count,position);
		
		/*
		 * 尝试插入message
		 */
		try {
			SqlOperator.sqlInsert(conn, m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.append("Insert False!");out.println("");return;
		}
		out.append("Insert Success");
		
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
