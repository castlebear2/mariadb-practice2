package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			/**
			  1. JDBC Driver Class 로딩
			  " "안 내용은 다 정해져 있는거다. 오라클은 오라클한테 물어보기.
			  이거 Driver를 클래스패스 맹키로 JRE 환경에서 찾는다. 빌드 환경등이나.
			  
			  클래스가 로딩되면서, "Driver클래스"안에서 뭐가 실행된다.
			  
			  그건 나중에, 어떻게 되는지 실습하심.(static 영역이 실행됨.)
			*/
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			// Connection 객체 안에 실제 DB와 연결된 뭐가 있겠지
			// url도 정의되어있다. 매개변수도 아이디, 비밀번호 순으로 정해져 있다.
			// url에는 DB가 있는 주소!!!
			String url = "jdbc:mariadb://192.168.0.88:3307/webdb?charset=utf8"; 
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			System.out.println("연결 성공!!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// conn이 null이라서 위에서 오류 처리했는데, 여기서 또 null일 경우
				// 가 있기 때문에, try-catch 해줘야한다.
				if(conn != null) {
					conn.close(); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}
