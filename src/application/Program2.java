package application;

import java.util.Map;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== Test 1 : findById ===");
	    Department dep = departmentDao.findById(1);
		System.out.println(dep);
		
		System.out.println("=== Test 2 : findAll ===");
		Map<Integer, Department> map = departmentDao.findAll();
        
		for(Integer d : map.keySet()) {
			System.out.println(map.get(d));
		}
		
	}

}
