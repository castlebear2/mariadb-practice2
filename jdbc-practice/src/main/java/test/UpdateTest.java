package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {
	public static void main(String[] args) {
		DeptVo vo = new DeptVo();
		vo.setNo(1L);
		vo.setName("경영지원");

		Boolean result = update(vo);
		System.out.println(result ? "성공" : "실패");
		update(vo);
	}

	private static boolean update(DeptVo vo) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.0.88:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			stmt = conn.createStatement();

			String sql = 
					"update dept" + 
					" set name='" + vo.getName() + "'" + 
					" where no = " + vo.getNo();
			
			int count = stmt.executeUpdate(sql);

			result = count == 1;

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
}
