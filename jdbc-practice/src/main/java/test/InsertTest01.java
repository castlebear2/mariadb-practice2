package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest01 {

	public static void main(String[] args) {
		insert("기획2");
	}

	private static boolean insert(String deptName) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			// employees가 DB이름인지 커넥션이름인지 모르것네
			String url = "jdbc:mariadb://192.168.0.88:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. Statement 생성 : 쿼리를 떄리기 위한 객체
			stmt = conn.createStatement();

			// 4. SQL 실행 : 그럼 쿼리가 있어야겠지?
			String sql = "insert into dept values(null, '" + deptName + "')";

			// 쿼리를 실행하는 코드. rs는 결과값을 받아주는 역할.
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