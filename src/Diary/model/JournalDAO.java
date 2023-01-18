package Diary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JournalDAO {
//--------------------필드선언 및 초기화 -------------
	private static Connection conn;
	private static JournalDAO dao = new JournalDAO();
//--------------------필드선언 및 초기화 끝-----------
//--------------------DB연결
	
	//----------------싱글톤 작업-------
	private JournalDAO() {
	}
	public static JournalDAO getInstance() {
		return dao;
	}
	private Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@jikwang.net:15210/xe", "green", "1234");
			System.out.println("커넥션 생성 완료, 정보 -> " + conn);
		} catch (Exception e) {
			System.out.println("커넥션 생성시 -> 예외 발생");
			System.out.println("예외 내용 : " + e.getMessage());
		}
		return conn;
	}
	//----------------싱글톤 작업 끝-------
	//----------------메서드 구현 시작-----
	//----------------일기 Insert 구현 메서드 ----
	public int add(JournalDTO Jouranl) {
		int result = 0;
		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into Jouranl(userId,regdate,JournalInput) values (?,?,?)");
			pstmt.setString(1,Jouranl.getUserId());
			pstmt.setString(2,Jouranl.getRegdate());
			pstmt.setString(3,Jouranl.getJournalInput());
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("회원가입 예외발생 : " + e.getMessage());
		}
		return result;//성공시 1리턴, 문제시 0 리턴
	}
	public ResultSet list(String JournalList) {
		ResultSet rs = null;
		conn = getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from account where JournalList = ? order by regdate desc");
			pstmt.setString(1, JournalList);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("리스트출력 예외발생" + e.getMessage());
		}
		return rs;
	}
	
	
}//End of DiaryDAO class
