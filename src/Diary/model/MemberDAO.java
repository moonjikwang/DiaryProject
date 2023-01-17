package Diary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDAO {
	//---------------------필드선언 및 초기화 --------------------
	private static Connection conn;
	private static MemberDAO dao = new MemberDAO();
	//---------------------필드선언 및 초기화 --------------------
	// -----------------싱글톤 작업 --------------------
	private MemberDAO() {
	}
	public static MemberDAO getInstance() {
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
		public int registerId(MemberDTO member) {
			int result = 0;
			try {
				conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("insert into users (userid,userpassword,username,regdate) values (?,?,?,sysdate)");
				pstmt.setString(1, member.getUserid());
				pstmt.setString(2, member.getUserpassword());
				pstmt.setString(3, member.getUsername());
				result = pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("회원가입 예외발생 : " + e.getMessage());
			}
			return result;//문제없이 회원가입성공시 1을 리턴, 문제발생시 0을 리턴합니다.
		}
		// ------------------------ 회원가입메서드 끝 ------------------------
		//---------------로그인 ------------------
		public int logIn(MemberDTO member) {// 입력한 이메일이없으면 -1, 비밀번호 틀리면 0 , 정상로그인이면 1을 리턴합니다.
			int temp = 0;
			int result = -1;
			conn = getConnection();
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select count(*) as cnt from users where userid = '"+member.getUserid()+"'");
				if(rs.next()) {
					temp = rs.getInt("cnt");
				}
				if(temp == 1) {
					rs = stmt.executeQuery("select count(*) as cnt from users where userid = '"+member.getUserid()+"' and userpassword = '"+member.getUserpassword()+"'");
					if(rs.next()) {
						result = rs.getInt("cnt");
					}else {
						result = 0;
					}
				}else {
					result = -1;
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("로그인 예외 발생 : " + e.getMessage());
			}
			return result;
		}
		//-----------id to name 메서드-----------------
		public String idToName(MemberDTO member) {
			String name = null;
			conn = getConnection();
			try {
				Statement stmt= conn.createStatement();
				ResultSet rs = stmt.executeQuery("Select username from users where userid='"+member.getUserid()+"'");
				if(rs.next()) {
					name = rs.getString("username");
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("아이디 to 이름 메서드 예외 : " + e.getMessage());
			}
			
			return name;
		}
	
}
