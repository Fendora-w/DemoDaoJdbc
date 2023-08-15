package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

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
			st = conn.prepareStatement("SELECT Id, Name FROM department WHERE Id = ? ",Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id);
			rs = st.executeQuery();
		    
			while(rs.next()){
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				return dep;
			}
			
		}catch(SQLException e) {
			e.getMessage();
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
		
		return null;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

}
