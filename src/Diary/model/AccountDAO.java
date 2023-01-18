package Diary.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
	//---------------------필드선언 및 초기화 --------------------
	private static Connection conn;
	private static AccountDAO dao = new AccountDAO();
	//---------------------필드선언 및 초기화 --------------------
	// -----------------싱글톤 작업 --------------------
	private AccountDAO() {
	}
	public static AccountDAO getInstance() {
		return dao;
	}
	private Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@jikwang.net:15210/xe","green","1234");
		} catch (Exception e) {
			System.out.println("Connection 생성시 예외 발생함 . : " + e.getMessage());
		}
		return conn;
	}
	// 가계부 추가 메서드 
	public int add(AccountDTO account) {
		int result = 0;
		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into account (userid,regdate,category,type,memo,amount) values (?,?,?,?,?,?)");
			pstmt.setString(1, account.getUserId());
			pstmt.setDate(2, Date.valueOf(account.getRegdate()));
			pstmt.setString(3, account.getCategory());
			pstmt.setString(4, account.getType());
			pstmt.setString(5, account.getMemo());
			pstmt.setString(6, account.getAmount());
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("회원가입 예외발생 : " + e.getMessage());
		}
		return result;//문제없이 성공시 1을 리턴, 문제발생시 0을 리턴합니다.
	}
	public ResultSet list(String userid) {
		ResultSet rs = null;
		conn = getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from account where userid = ? order by regdate desc");
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("가계부 리스트출력 예외발생" + e.getMessage());
		}
		return rs;
	}
}