/**
 * Servlet implementation class QueryDay
 * 
 * @Parameter:
 * 		userName --String
 * 		userPass --String
 * @Response:
 * 		message -- String as Json(eg:
 * 			{"Message": [{"id":"1","day":"2018-04-06","clock":"18:08:00","count":"4","position":"NorthGirl"}]}
 * 		)
 * 
 * @Contral:
 * if 
 * 		userName & userPass match as Administor
 * then
 * 		list all message from database BathRoom
 * 
 * 
 * @Interface:
 * 		http://localhost:8080/test4/Query?userName=root&userPass=123456
 * 		http://47.94.223.169:8080/test4/Query?userName=root&userPass=123456
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

@WebServlet("/Query")
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		ResultSet rs=null;
		
		Connection conn=ConnectionFactory.getConnection(request);
		
		if(conn==null) {
			out.append("Login False!!");out.println("");return;
		}
		String sql="select * from BathRoom";
		try {
			rs=SqlOperator.sqlQuery(conn,sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.append("Query Error");
			e.printStackTrace();
		}
		try {
			out.append(SqlOperator.toJson(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.append("toJson Error");
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static void main(String[] args) {
//		Connection conn=ConnectionFactory.getConnection("root","123456");
//		if(conn==null) {
//			System.out.println(123);
//			return;
//		}
	}
}
