package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			
			st = conn.prepareStatement("SELECT Id, Name FROM department WHERE Id = ? ",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id);
			rs = st.executeQuery();

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				return dep;
			}

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

		return null;
	}

	@Override
	public Map<Integer, Department> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("select Id, Name from department " + "order by Name ",
					Statement.NO_GENERATED_KEYS);

			Map<Integer, Department> map = new HashMap<>();
			rs = st.executeQuery();
   
            
			while (rs.next()) {

				if(!map.containsKey(rs.getInt("Id"))) {
					Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
					map.put(rs.getInt("Id"), dep);
				}
				
			}
			
			
			return map;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}

	}

	@Override
	public List<Department> findByDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

}
