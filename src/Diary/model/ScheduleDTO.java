package Diary.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class ScheduleDTO {
	
	
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	private Date sdate;
	private String title;
	private String memo;
	private boolean attention;
	private Date alert_time;
	private LocalDate localDate;
	
	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
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
	
	
	

}
