/**
 * Servlet implementation class DeleteDay
 * 
 * @Parameter:
 * 		userName --String
 * 		userPass --String
 * 		deleteDay--String
 * @Response:
 * 		delete message count: xx
 * 
 * @Contral:
 * if 
 * 		userName & userPass match as Administor
 * then
 * 		Delete all message from database BathRoom  where Day==deleteDay
 * 
 * 
 * @Interface:
 * 		http://localhost:8080/test4/DeleteDay?userName=root&userPass=123456&deleteDay=2018-4-6
 * 		http://47.94.223.169:8080/test4/DeleteDay?userName=root&userPass=123456&deleteDay=2018-4-6
 * 
 * */

package test_4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteDay
 */
@WebServlet("/DeleteDay")
public class DeleteDay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDay() {
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
		 * 获取删除id，创建preparedStatement
		 */
		String deleteDay0=request.getParameter("deleteDay");
		Date deleteDay=StrToMysqlDate.strToSqlDate(deleteDay0);
		String sql="Delete from BathRoom where Day=?;";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			//System.out.println(preparedStatement);
			preparedStatement.setDate(1, deleteDay);
			//System.out.println(preparedStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * 根据preparedStatement ，　删除数据.
		 */
		try {
			//System.out.println(preparedStatement);
			int rs = SqlOperator.sqlUpdate(conn, preparedStatement);
			//StringBuffer ret=SqlOperator.toJson(rs);
			out.append("delete message count: "+rs);
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
