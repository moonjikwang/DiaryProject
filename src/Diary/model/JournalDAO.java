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

import javax.swing.JOptionPane;

public class JournalDAO {
//--------------------필드선언 및 초기화 -------------
	private static Connection conn;
	private static JournalDAO dao = new JournalDAO();
//--------------------필드선언 및 초기화 끝-----------

	// -----------------싱글톤 작업 --------------------
	private JournalDAO() {
	}

	public static JournalDAO getInstance() {
		return dao;
	}

	private static Connection getConnection() {
		try {// DB연결
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@jikwang.net:15210/xe", "green", "1234");
		} catch (Exception e) {
			System.out.println("Connection 생성시 예외 발생함 . : " + e.getMessage());
		}
		return conn;
	}
	// ---------------싱글톤 작업 끝--------------

	// --------------일기 저장 메서드 시작--------
	public int registerJournal(JournalDTO Jouranl) {
		int result = 0;
		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into diary (num,userid,memo,regdate) values (numbering.nextval,?,?,sysdate)");// ,
			// SCHEDULE_SEQ.NEXTVAL
			pstmt.setString(1, Jouranl.getUserId());
			pstmt.setString(2, Jouranl.getjournal());

			result = pstmt.executeUpdate();
			System.out.println("일기 저장 완료");
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("일기 저장 예외 발생 : " + e.getMessage());
		}
		return result;// 성공시 1리턴, 문제시 0 리턴
	}
	// ----------------일기 저장 종료-----------

	// ----------------리스트 출력---------
	public ArrayList<JournalDTO> selectJour(JournalDTO jDTO) {
		
		String id = jDTO.getUserId();
		ArrayList<JournalDTO> jours = null;
		Connection con = getConnection();
		String sql = "select * from DIARY where userid = ? order by REGDATE desc";

		JournalDTO dto;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				jours = new ArrayList<JournalDTO>();

				do {
					dto = new JournalDTO();
					dto.setUserId(rs.getString("USERID"));
					dto.setjournal(rs.getString("MEMO"));
					dto.setRegdate(rs.getDate("REGDATE"));
					dto.setNum(rs.getInt("NUM"));

					jours.add(dto);

				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("조회 실패 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return jours;
	}

	// ----------------리스트 출력 메서드 종료---------
	// ---------------편집 메서드 시작-----------------
	public void updateJour(JournalDTO dto) {
		PreparedStatement pstmt = null;
		String sql = "Update DIARY SET memo = ? where num  = ? ";

		int result = JOptionPane.showConfirmDialog(null, "일기를 수정할까요?");

		if (result == JOptionPane.YES_OPTION) {

			Connection conn = getConnection();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getjournal());
				result = pstmt.executeUpdate();
				System.out.println("일기가 수정되었습니다.");

			} catch (SQLException e) {
				System.out.println("일기 수정이 실패했습니다." + e.getMessage());
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// ---------------편집 메서드 종료---------------
	// ---------------삭제 메서드 시작---------------
	public void deleteJour(int num) {
		PreparedStatement pstmt = null;
		String sql = "delete from DIARY WHERE NUM =? ";
		Connection conn = getConnection();

		int result = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?");

		if (result == JOptionPane.YES_OPTION) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				System.out.println("일기 삭제 완료!");
				
			} catch (Exception e) {
				System.out.println("삭제 실패");
				e.printStackTrace();
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("문제 발생");
		}

	}
	// ---------------삭제 메서드 종료---------------

}// ------------------JournalDTO class 종료----------