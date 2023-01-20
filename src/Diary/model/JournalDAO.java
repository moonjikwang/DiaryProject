package Diary.model;
/*
 * 1. 커넥션 연결
 * 2. JournalDAO 인스턴스 생성
 * 3. 메서드 정의
 * 	3_1. 일기 저장 메서드 
 * 	3_2. 일기 목록 출력 메서드 정의
 * 	3_3. 일기 삭제 메서드 정의 //미
 * 	3_4. 일기 편집 메서드 정의 //미
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JournalDAO {
//--------------------필드선언 및 초기화 -------------
	private static Connection conn;
	private static JournalDAO dao = new JournalDAO();
//--------------------필드선언 및 초기화 끝-----------
	
	//-----------------싱글톤 작업 --------------------
	private JournalDAO() {
	}
	public static JournalDAO getInstance() {
		return dao;
	}
	private static Connection getConnection() {
		try {//DB연결
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@jikwang.net:15210/xe","green","1234");
		} catch (Exception e) {
			System.out.println("Connection 생성시 예외 발생함 . : " + e.getMessage());
		}
		return conn;
	}
	//---------------싱글톤 작업 끝--------------
	
	//--------------일기 저장 메서드 시작--------
	public int registerJournal(JournalDTO Jouranl) {
		int result = 0;
		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into diary (userid,memo,regdate) values (?,?,sysdate)");
			pstmt.setString(1,Jouranl.getUserId());
			pstmt.setString(2,Jouranl.getjournal());
			
			result = pstmt.executeUpdate();
			System.out.println("일기 저장 완료");
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("일기 저장 예외발생 : " + e.getMessage());
		}
		return result;//성공시 1리턴, 문제시 0 리턴
	}
	//----------------일기 저장 메서드 종료-----------
	//----------------리스트 출력 메서드 시작---------
	public List<JournalDTO> getlist(String id) {
		List<JournalDTO> jourList = Collections.EMPTY_LIST;
		JournalDTO dto = null;
		String sql = "select * from Jouranl where userid = ? order by regdate desc";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			//데이터가 있으면,
			if(rs.next()) {
				jourList = new ArrayList<JournalDTO>();
				do {
					dto = new JournalDTO();
					dto.setUserId(rs.getString("userid"));
					dto.setRegdate(rs.getDate("regdate"));
//					dto.setjournal(rs.getString("memo")); //목록에 들어가므로 제외
					
					//jourList dto에 담기
					jourList.add(dto);
				} while (rs.next());
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("리스트출력 예외발생" + e.getMessage());
			e.printStackTrace();
		}
		return jourList;
	}
	//----------------리스트 출력 메서드 종료---------	
	//---------------편집 메서드 시작-----------------
	public static void updateJour(Connection con) {
		conn = con;
		String sql = "Update DIARY SET memo = ? where userid = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(0, sql);//이 부분 좀 더 생각해 보기.
			//문자열로 수정할 메모 저장, 하고싶은데...
			System.out.println(pstmt.executeUpdate() + "일기가 수정되었습니다.");
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//---------------편집 메서드 종료---------------
	//---------------삭제 메서드 시작---------------
	public static int deleteJour(String journal) {
		String sql = "Deleate From DIARY where memo = ?";
		Connection conn = getConnection();
		int result = 0;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, journal);
			result = pstmt.executeUpdate();
			pstmt.close();
//			conn.close();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	//---------------삭제 메서드 종료---------------
	
}//------------------JournalDTO class 종료----------