package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest02 {

	public static void main(String[] args) {
		search("pat");
	}

	public static void search(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.0.88:3307/employees?charset=utf8";
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 3. Statement 준비
			String sql = 
				"select emp_no, first_name, last_name" + 
			    " from employees" + 
			    " where first_name like ?";
			pstmt = conn.prepareStatement(sql);
			// 4. binding(그냥 넣는다.라고 생각.)
			pstmt.setString(1, "%" + keyword + "%");
			
			// 여기까지는, 굳이 완성된 쿼리가 여기 없다. 코드 안에 없잖어!!!
			
			
			// 4. 쿼리를 실행하는 코드. rs는 결과값을 받아주는 역할.
			rs = pstmt.executeQuery();

			// 5. 결과 처리(결과값 보여주기)
			// rs가 테이블이라고 생각하면 될 거 같다.
			while (rs.next()) {
				Long empNo = rs.getLong(1);
				String lastName = rs.getString(2);

				System.out.println(empNo + ":" + lastName);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
