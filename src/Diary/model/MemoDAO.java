package Diary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemoDAO {
	//---------------------필드선언 및 초기화 --------------------
		private static Connection conn;
		private static MemoDAO dao = new MemoDAO();
		//---------------------필드선언 및 초기화 --------------------
		// -----------------싱글톤 작업 --------------------
		private MemoDAO() {
		}
		public static MemoDAO getInstance() {
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
		// -----------------싱글톤 작업 --------------------
		// ------------------------회원가입메서드------------------------
			public int registerMemo(MemoDTO memo) {
				int result = 0;
				try {
					conn = getConnection();
					PreparedStatement pstmt = conn.prepareStatement("insert into diary (userid,memo,regdate) values (?,?,sysdate)");
					pstmt.setString(1, memo.getUserId());
					pstmt.setString(2, memo.getMemo());
					result = pstmt.executeUpdate();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					System.out.println("메모저장 예외발생 : " + e.getMessage());
				}
				return result;//문제없이 회원가입성공시 1을 리턴, 문제발생시 0을 리턴합니다.
			}
			// ------------------------ 회원가입메서드 끝 ------------------------
		
			
		

}
