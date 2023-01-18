package Diary.model;


public class AccountDTO {

	private String category;
	private String type;
	private String userId;
	private String regdate;
	private String amount;
	private String memo;
	
	public AccountDTO(String category,String type,String userId,String regdate, String amount, String memo) {
		this.category = category;
		this.type = type;
		this.userId = userId;
		this.regdate = regdate;
		this.amount = amount;
		this.memo = memo;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String cetegory) {
		this.category = cetegory;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
