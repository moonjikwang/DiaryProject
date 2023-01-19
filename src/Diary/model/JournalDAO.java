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
	
	// -----------------싱글톤 작업 --------------------
	private JournalDAO() {
	}
	public static JournalDAO getInstance() {
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
	//----------------싱글톤 작업 끝-------
	
	//----------------일기 Insert 메서드 ----
	public int registerJournal(JournalDTO Jouranl) {
		int result = 0;
		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into diary (userid,memo,regdate) values (?,?,sysdate)");
			pstmt.setString(1,Jouranl.getUserId());
			pstmt.setString(2,Jouranl.getjournal());
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("일기 저장 예외발생 : " + e.getMessage());
		}
		return result;//성공시 1리턴, 문제시 0 리턴
	}
	//---------------일기 Insert 메서드 끝------
	
	//----------------리스트 출력 메서드---------
	public ResultSet list(String JournalList) {
		ResultSet rs = null;
		conn = getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from Jouranl order by regdate desc");
			pstmt.setString(1, JournalList);
			rs = pstmt.executeQuery();
			
			//regdate 반환
			
		} catch (SQLException e) {
			System.out.println("리스트출력 예외발생" + e.getMessage());
		}
		return rs;
	}
	
	//---------------편집 메서드--------------
//	public static void journalUpdate(Connection con) {
//		Connection conn = con;
//
//	}
	
	//---------------삭제 메서드---------------
	public static int journalDelete(Connection con, String JournalInput) {
		int rs = 0;
		String sql = "Deleate From Journal where JournalInput = ?";
		conn = con;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, JournalInput);
			rs = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	
}//End of JournalDTO class