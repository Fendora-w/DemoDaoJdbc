package model.dao;

import java.util.List;
import java.util.Map;

import model.entities.Department;

public interface DepartmentDao {
	
	void insert(Department obj);
	void update(Department obj);
	void deleteById(Integer id);
	Department findById(Integer id);
	Map<Integer, Department> findAll();
	List<Department> findByDepartment(Department department);
}
