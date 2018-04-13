/**
 * Servlet implementation class QueryLatest
 * 
 * @Parameter:
 * 		userName --String
 * 		userPass --String
 * 	
 * @Response:
 * 		message -- String as Json(eg:
 * 			{"Message": [{"id":"1","day":"2018-04-06","clock":"18:08:00","count":"4","position":"NorthGirl"}]}
 * 		)
 * 
 * @Contral:
 * if 
 * 		userName & userPass match as Administor
 * then
 * 		list all message from database BathRoom  where id==LAST_INSERT_ID()
 * 
 * 
 * @Interface:
 * 		http://localhost:8080/test4/QueryLatest?userName=root&userPass=123456
 * 		http://47.94.223.169:8080/test4/QueryLatest?userName=root&userPass=123456
 * 
 * */

package test_4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryLatest
 */
@WebServlet("/QueryLatest")
public class QueryLatest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryLatest() {
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
		String sql="select * from BathRoom where id=(select MAX(id) from BathRoom);";
		
		/*
		 * 根据preparedStatement ，　删除数据.
		 */
		try {
			//System.out.println(preparedStatement);
			ResultSet rs = SqlOperator.sqlQuery(conn, sql);
			//StringBuffer ret=SqlOperator.toJson(rs);
			out.append(SqlOperator.toJson(rs));
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
