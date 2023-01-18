package Diary.model;

//-----------JournalDiary 정보를 간직하는 DTO 객체 
public class JournalDTO {
	
	private String userId;
	private String regdate;
	private String JournalInput;
	private String JournalList;

	public JournalDTO(String userId, String regdate, String JournalInput, String JournalList) {
		this.userId = userId;
		this.regdate = regdate;
		this.JournalInput = JournalInput;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getJournalInput() {
		return JournalInput;
	}

	public void setJournalInput(String journalInput) {
		JournalInput = journalInput;
	}
	
}
