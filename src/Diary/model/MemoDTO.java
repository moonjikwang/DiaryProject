package Diary.model;

public class MemoDTO {
	private String userId;
	private String memo;
	
	public MemoDTO(String userId, String memo) {
		this.userId = userId;
		this.memo = memo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	

}
