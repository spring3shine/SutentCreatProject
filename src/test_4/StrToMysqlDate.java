/**
 *	Java 与　Mysql 的 date格式不同，需要利用String，进行转换传递
 *　本类将String 转化为 java.sql.date/time
 */

package test_4;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class StrToMysqlDate {
	/**
	 * String -> java.sql.date    输入 2016-11-11
	 * @param  strDate [in] [String] 字符串日期
	 * @return 返回java.sql.Date格式的
	 * */
	public static java.sql.Date strToSqlDate(String strDate) {
		String str = strDate;
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d =null;
		try{
			d = format.parse(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		java.sql.Date date =new java.sql.Date(d.getTime());
		return date;
	}

	/**
	 * String -> java.sql.time   输入 12:30:59
	 * @param  strTime [in] [String] 字符串时刻
	 * @return 返回java.sql.Time格式的
	 * */
	public static java.sql.Time strToSqlTime(String strDate) {
		String str = strDate;
		SimpleDateFormat format =new SimpleDateFormat("hh:mm:ss");
		java.util.Date d =null;
		try{
			d = format.parse(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Time date =new java.sql.Time(d.getTime());
		return date;
	}

	public static void main(String[] args) {
		System.out.println(strToSqlDate("2018-4-7"));
		System.out.println(strToSqlTime("14:13:00"));
	}
}
