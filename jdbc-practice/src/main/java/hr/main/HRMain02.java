package hr.main;

import java.util.List;
import java.util.Scanner;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;

public class HRMain02 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); 

		while (true) {
			String keyword1 = scanner.nextLine();
			String keyword2 = scanner.nextLine();
		

			if("quit".equals(keyword1)) {
				break;
			}
			List<EmployeeVo> list = new EmployeeDao().findByName(keyword1, keyword2);
		
			
			for (EmployeeVo vo : list) {
				System.out.println(
						vo.getNo() + ":" + vo.getSalary());

			}
		}
		scanner.close();
	}

}
