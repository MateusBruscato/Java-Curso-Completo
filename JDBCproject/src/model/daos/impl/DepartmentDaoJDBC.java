package model.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.daos.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao {

	Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department dep) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO department " 
					+ " (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, dep.getName());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet result = st.getGeneratedKeys();
				if (result.next()) {
					dep.setId(result.getInt(1));
				}
				result.close();
			} else {
				throw new DbException("No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Department dep) {
		PreparedStatement st = null;
		try {
				st = conn.prepareStatement("UPDATE department SET " 
						+ " name = ? WHERE id = ?");
				
				st.setString(1, dep.getName());
				st.setInt(2, dep.getId());
				
				int rowsAffected = st.executeUpdate();
				System.out.println("Number of rows affected: " + rowsAffected);

		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	
	}

	@Override
	public void deleteById(int id) {
		PreparedStatement st = null;
		try {
				st = conn.prepareStatement("DELETE FROM department "
						+ " WHERE id = ?");
				
				st.setInt(1, id);
				
				int rowsAffected = st.executeUpdate();
				if(rowsAffected > 1)
				{
					conn.rollback();
					throw new DbException("More than one row affected");
				}
				System.out.println("Number of rows affected: " + rowsAffected);
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(int id) {
		PreparedStatement st = null;
		ResultSet res = null;
		try {
				st = conn.prepareStatement("SELECT * "
						+ " FROM department "
						+ " WHERE id = ?");
				
				st.setInt(1, id);
				
				res = st.executeQuery();
				if(res.next())
				{
					String depName = res.getString("Name");
					int departmentId = res.getInt("Id");
					Department dep = new Department(departmentId, depName);
					return dep;
				}
				return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(res);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet res = null;
		try {
				st = conn.prepareStatement("SELECT * "
						+ " FROM department "
						+ " ORDER BY id");
				
				List<Department> departments = new ArrayList<>();
				
				res = st.executeQuery();
				
				while(res.next())
				{
					String depName = res.getString("Name");
					int departmentId = res.getInt("Id");
					Department dep = new Department(departmentId, depName);
					
					departments.add(dep);
				}
				return departments;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(res);
			DB.closeStatement(st);
		}
	}

}
