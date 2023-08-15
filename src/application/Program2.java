package application;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== Test 1 : findById ===");
	    Department dep = departmentDao.findById(1);
		System.out.println(dep);
		System.out.println();
		
		System.out.println("=== Test 2 : findAll ===");
		Map<Integer, Department> map = departmentDao.findAll();
        
		for(Integer d : map.keySet()) {
			System.out.println(map.get(d));
		}
		System.out.println();
		
		System.out.print("Enter value id: ");
		int value = sc.nextInt();
		System.out.println("=== Test 3 : findByDepartment ===");
		Department department = new Department(value, null);
		List<Department> list = departmentDao.findByDepartment(department);
		list.forEach(System.out::println);
		
		System.out.println("Insertion successful!");
		System.out.println("=== Test 4 : findByDepartment ===");
		Department insertDepartment = new Department(null, "Library"); 
		departmentDao.insert(insertDepartment);
		System.out.println("Inserted! new id = "+ insertDepartment.getId());
		
	}

}
