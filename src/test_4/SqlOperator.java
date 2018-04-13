/**
 * SqlOperator ,基本的数据库查询和更新接口，主要是格式转换
 */

package test_4;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.text.ParseException;

public class SqlOperator {
	
	public static ResultSet sqlQuery(Connection conn,String sql) throws SQLException{
		java.sql.Statement pstmt=conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery(sql);
		return rs;
	}
	
	public static ResultSet sqlQuery(Connection conn,PreparedStatement pstmt) throws SQLException{
		return pstmt.executeQuery();
	}
	
	public static int sqlUpdate(Connection conn,PreparedStatement preparedStatement) throws SQLException{
		return preparedStatement.executeUpdate();
	}
	
	/**
	 * 将mysql查询结果转换为Json格式方便传递
	 * @param rs [in] [ResultSet]  是mysql查询的结果数据
	 * @return StringBuffer类，是Json格式数据
	 * @throws SQLException
	 */
	public static StringBuffer toJson(ResultSet rs) throws SQLException {
		StringBuffer ret=new StringBuffer();
		ResultSetMetaData meta=rs.getMetaData();
		ret.append("{"+ 
				"\"Message\": [" 
				);
		while(rs.next()) {
			ret.append("{");
			for(int i=1;i<=meta.getColumnCount();i++) {
				ret.append("\""+meta.getColumnName(i)+"\"");
				ret.append(":");
				ret.append("\""+rs.getString(i)+"\"");
				if(i!=meta.getColumnCount()) {
					ret.append(",");
				}
			}
			ret.append("}");
			if(rs.isLast()==false) {
				ret.append(",");
			}
		}
		ret.append("]}");
		return ret;
	}
	
	public static int sqlInsert(Connection conn,Message m) throws SQLException {
		String sql = "INSERT BathRoom(day,clock,count,position) VALUES(?,?,?,?);";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setDate(1, m.getDay());
		preparedStatement.setTime(2, m.getClock());
		preparedStatement.setInt(3, m.getCount());
		preparedStatement.setString(4, m.getPosition());
		
		return preparedStatement.executeUpdate();
//		System.out.println(preparedStatement);
//		return 0;
		
		
		//sqlUpdate(conn,sql);//！！这句是错误的，这样会直接传values(?,?,?,?)的sql；而不是设置好值的values('2018-4-6','','','')
	}


	public static void main(String[] args) throws SQLException, ParseException {
		Connection conn = ConnectionFactory.getConnection();
		Message m=new Message();
		m.setDay(StrToMysqlDate.strToSqlDate("2018-4-6"));
		m.setClock(StrToMysqlDate.strToSqlTime("14:16:00"));
		m.setCount(5);
		m.setPosition("NorthBoy");
		m.print();
		sqlInsert(conn,m);

		String sql="select * from BathRoom";
		ResultSet rs=sqlQuery(conn,sql);
		System.out.println(toJson(rs));
	}
}

//Insert BathRoom(day,clock,count,position) values('2018-4-6','12:06:00',6,'NorthBoy');
//INSERT INTO BathRoom(day,clock,count,position) VALUES('2018-04-06','14:16:00'.5.'NorthBoy')