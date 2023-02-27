package hr.dao.test;

import java.util.List;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;


public class EmployeeDaoTest {

	public static void main(String[] args) {
		testFindByName("pat");

	}

	private static void testFindByName(String keyword) {
		List<EmployeeVo> list = new EmployeeDao().findByName(keyword);
		for(EmployeeVo vo : list) {
			System.out.println(vo);
		}
	}

}
