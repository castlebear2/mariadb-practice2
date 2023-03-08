package hr.dao.test;

import java.util.List;

import hr.dao.EmployeeDao;
import hr.vo.EmployeeVo;


public class EmployeeDaoTest {

	public static void main(String[] args) {
		List<EmployeeVo> list = new EmployeeDao().findByName("pat");
		
		for(EmployeeVo vo : list) {
			System.out.println(vo);
		} 
	}
}
