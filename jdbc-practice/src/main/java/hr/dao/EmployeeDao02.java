package hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hr.vo.EmployeeVo;

public class EmployeeDao02 {
	public List<EmployeeVo> findSalary(String keyword1, String keyword2) {
		List<EmployeeVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.0.88:3307/employees?charset=utf8";
			conn = DriverManager.getConnection(url, "hr", "hr");

			String sql = 
					"select emp_no, salary" +
					" from salaries"+
					" where salary >= ? and salary <= ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, keyword1);
			pstmt.setString(2, keyword2);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long no = rs.getLong(1);
				Long salary = rs.getLong(2);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setSalary(salary);
				
				
				result.add(vo);
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
		return result;
	}
}
