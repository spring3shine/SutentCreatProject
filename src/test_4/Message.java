/**
 * 	Message 是一个容器类, 是用到的信息集合  
 * 	@author spring3shine
 * 	@Time 	2018/04/13
 */


package test_4;

import java.sql.Date;
import java.sql.Time;

public class Message {
	//按照mysql所列，除id;
	private Date day;//日期，DATE_FORMAT(YYYY-MM-DD)
	private Time clock;//时刻，DATE_FORMAT(HH:MM:SS)
	private int count;//当前浴室人数
	private String position;//浴室位置
	
	public Message() {
	}
	public Message(Date day,Time clock,int count ,String position) {
		this.day=day;
		this.clock=clock;
		this.count=count;
		this.position=position;
	}
	
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	
	public Time getClock() {
		return clock;
	}
	public void setClock(Time clock) {
		this.clock = clock;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public void print() {
		System.out.println(getDay());
		System.out.println(getClock());
		System.out.println(getCount());
		System.out.println(getPosition());
		return ;
	}
}
