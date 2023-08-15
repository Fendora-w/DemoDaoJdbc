package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO  department(Name)values(?) ", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
		        if (rs.next()) {
		            int generatedId = rs.getInt(1); // Recupera o valor do ID gerado
		            obj.setId(generatedId); // Define o ID gerado no objeto
		            System.out.println("Insertion successful!");
		        } else {
		            System.out.println("Failed to get generated ID.");
		        }
		        DB.closeResultSet(rs);

			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch(SQLException e) {
			e.getMessage();
		}
		finally {
			DB.closeStatement(st);
		}

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
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT Id, Name FROM department WHERE Id > ? "
	                                 + "ORDER BY Name");
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while(rs.next()) {
				
				if(!list.contains(rs.getInt("Id"))) {
					Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
					list.add(dep);
				}
			}
			
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	}

}
