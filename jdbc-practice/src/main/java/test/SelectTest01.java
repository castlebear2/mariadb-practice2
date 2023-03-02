package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest01 {
	public static void main(String[] args) {
		search("pat");
	}
	public static void search(String keyword) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			// employees가 DB이름인지 커넥션이름인지 모르것네
			String url = "jdbc:mariadb://192.168.0.88:3307/employees?charset=utf8"; 
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			//3. Statement 생성 : 쿼리를 전송을 위한 객체
			stmt = conn.createStatement();
			
			//4. SQL 실행 : 그럼 쿼리가 있어야겠지?
			String sql = 
				"select emp_no, last_name" +
				" from employees" +
				" where first_name like '%"+ keyword + "%'" +
				" order by emp_no";
			
			// 쿼리를 실행하는 코드. rs는 결과값을 받아주는 역할.
			rs= stmt.executeQuery(sql);
			
			//5. 결과 처리(결과값 보여주기)
			// rs가 테이블이라고 생각하면 될 거 같다.
			while(rs.next()) {
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
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close(); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
