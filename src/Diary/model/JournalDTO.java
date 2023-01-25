package Diary.model;

import java.sql.Date;

//-----------JournalDiary 정보를 간직하는 DTO 객체 
public class JournalDTO {
	
	private String userId;
	private String journal;
	private Date regdate;
	private int num;
//	private String JournalList;

	public JournalDTO(String userId, String journal) {
		this.userId = userId;
		this.journal = journal;
	}

	public JournalDTO() {}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getjournal() {
		return journal;
	}

	public void setjournal(String journal) {
		this.journal = journal;
	}
	
	
	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
}