package Diary.model;

import java.sql.Date;

public class MemberDTO {
	private String userid;
	private String userpassword;
	private String username;
	private Date regdate;
	
	public MemberDTO(String userid, String userpassword) {
		this.userid = userid;
		this.userpassword = userpassword;
	}
	public MemberDTO(String userid,String username, String userpassword) {
		this.userid = userid;
		this.username = username;
		this.userpassword = userpassword;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
