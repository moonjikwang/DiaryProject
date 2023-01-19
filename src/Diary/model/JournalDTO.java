package Diary.model;

//-----------JournalDiary 정보를 간직하는 DTO 객체 
public class JournalDTO {
	
	private String userId;
	private String journal;
//	private String regdate;
//	private String JournalList;
	
	public JournalDTO(String userId, String journal) {
		this.userId = userId;
		this.journal = journal;
	}

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
	
}