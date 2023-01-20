package Diary.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;

public class ScheduleDTO {
	
	
	private String userId;
	private String sdate;
	private String title;
	private String memo;
	private boolean attention;
	private Date alert_time;
	private int num;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public boolean isAttention() {
		return attention;
	}
	public void setAttention(boolean attention) {
		this.attention = attention;
	}
	public Date getAlert_time() {
		return alert_time;
	}
	public void setAlert_time(Date alert_time) {
		this.alert_time = alert_time;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	

}
