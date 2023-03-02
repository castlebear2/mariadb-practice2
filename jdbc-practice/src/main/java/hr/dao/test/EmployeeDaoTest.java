package hr.dao.test;

import java.util.List;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;


public class EmployeeDaoTest {

	public static void main(String[] args) {
		testFindByName("38000", "39000");

		
	}

	private static void testFindByName(String keyword1, String keyword2) {
		List<EmployeeVo> list = new EmployeeDao().findByName(keyword1, keyword2);
		for(EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}

}
