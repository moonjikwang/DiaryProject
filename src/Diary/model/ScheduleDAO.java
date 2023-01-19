package Diary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ScheduleDAO {
	private static Connection conn; // 그냥 멤필이어도 되지만 스태틱을 붙여서 클래스 로드 할 때 한번만 초기화 되게 한다

	private static ScheduleDAO dao = new ScheduleDAO();

	private ScheduleDAO() {

	}
	
//------------------------<커넥션 얻기>----------------------
	private Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@jikwang.net:15210/xe", "green", "1234");
			System.out.println("커넥션 생성 성공 : " + conn);
		} catch (Exception e) {
			System.out.println("커넥션 생성시 예외 발생 : " + e.getMessage());
			e.printStackTrace();
		}
		return conn;

	}

//------------------------<인스턴스 얻기>----------------------
	public static ScheduleDAO getInstance() {
		return dao;
	}

	private void closer(Connection con) { // 중복코드 방지용 닫기 메서드
		if (this.conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("커넥션 종료시 예외발생 : " + e.getMessage());
			}
		}
	}

//------------------------<일정 추가>----------------------
	public int insert(ScheduleDTO dto) {
		int result = 0; // 가입이 안될경우 리턴 초기화

		PreparedStatement pstmt = null;
		String sql = "insert into Schedule (USERID, SDATE, TITLE, MEMO, ATTENTION," + " ALERT_TIME, REG_DATE, NUM) "
				+ "values (?,?,?,?,?,?, sysdate, SCHEDULE_SEQ.NEXTVAL)";

		Connection con = getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getSdate()); // 할일: 로컬데이트로 받으면 안됨...ㅜㅜ
//			pstmt.setDate(2, dto.getSdate());	//할일: 로컬데이트로 받으면 안됨...ㅜㅜ
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getMemo());
			pstmt.setBoolean(5, dto.isAttention());
			pstmt.setDate(6, dto.getAlert_time());

			result = pstmt.executeUpdate();
			System.out.println("일정추가 성공");

		} catch (Exception e) {
			System.out.println("인서트 실패 : " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					closer(con); // 지금 닫으면 다른 작업 못함
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

//------------------------<일정 조회>----------------------
	public ArrayList<ScheduleDTO> select(String id) {

		ArrayList<ScheduleDTO> schedules = null; // null로 초기화 해 버리면 호출한 쪽에서 예외가 뜨므로 빈 배열을 넘겨준다
		Connection con = getConnection();
		String sql = "select * from schedule where userid = ? order by SDATE";

		ScheduleDTO dto;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {

				schedules = new ArrayList<ScheduleDTO>(); // 리스트 생성

				
				do{
					dto = new ScheduleDTO();
					dto.setSdate(rs.getString("SDATE"));
					dto.setTitle(rs.getString("TITLE"));
					dto.setMemo(rs.getString("MEMO"));
					dto.setAttention(rs.getBoolean("ATTENTION"));
					dto.setAlert_time(rs.getDate("ALERT_TIME"));

					schedules.add(dto);

				}while (rs.next()) ;
			}

		} catch (Exception e) {
			System.out.println("조회 실패 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					closer(con); // 지금 닫으면 다른 작업 못함
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

		//콘솔에서 조회내용을 확인하기 위한 메서드
//		for (ScheduleDTO pdto : schedules) {
//			System.out.println("["+pdto.getSdate()+"]"+pdto.getTitle()+" : "+pdto.getMemo());
//		}
		
		return schedules;
	}
}
