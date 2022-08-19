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
import model.daos.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		PreparedStatement st = null;
		try {
				st = conn.prepareStatement("INSERT INTO seller " 
						+ "(name, email, birthDate, baseSalary, departmentId) "
						+ " VALUES "
						+ " (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				
				st.setString(1, seller.getName());
				st.setString(2, seller.getEmail());
				st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
				st.setDouble(4, seller.getBaseSalary());
				st.setInt(5, seller.getDepartment().getId());
				
				int rowsAffected = st.executeUpdate();
				
				if(rowsAffected > 0) {
					ResultSet result = st.getGeneratedKeys();
					if(result.next()) {
						seller.setId(result.getInt(1));
					}
					result.close();
				}
				else {
					throw new DbException("No rows affected!");
				}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Seller seller) {
		PreparedStatement st = null;
		try {
				st = conn.prepareStatement("UPDATE seller SET " 
						+ " name = ?, email = ?, birthDate = ?, baseSalary = ?, departmentId = ? "
						+ " WHERE id = ?");
				
				st.setString(1, seller.getName());
				st.setString(2, seller.getEmail());
				st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
				st.setDouble(4, seller.getBaseSalary());
				st.setInt(5, seller.getDepartment().getId());
				st.setInt(6, seller.getId());
				
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
				st = conn.prepareStatement("DELETE FROM seller "
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
	public Seller findById(int id) {
		PreparedStatement st = null;
		ResultSet res = null;
		try {
				st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
						+ " FROM seller INNER JOIN department "
						+ " ON seller.DepartmentId = department.Id "
						+ " WHERE seller.id = ?");
				
				st.setInt(1, id);
				
				res = st.executeQuery();
				if(res.next())
				{
					int idSeller = res.getInt("id");
					String name = res.getString("name");
					String email = res.getString("email");
					Date date = res.getDate("birthDate");
					Double baseSalary = res.getDouble("baseSalary");
					
					String depName = res.getString("DepName");
					int departmentId = res.getInt("departmentId");
					Department dep = new Department(departmentId, depName);
					
					Seller vendedor = new Seller(idSeller, name, email, date, baseSalary, dep);
					
					return vendedor;
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
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet res = null;
		try {
				st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
						+ " FROM seller INNER JOIN department "
						+ " ON seller.DepartmentId = department.Id "
						+ " ORDER BY id");
				
				List<Seller> sellers = new ArrayList<>();
				
				res = st.executeQuery();
				Map<Integer,Department> departments = new HashMap<>();
				
				while(res.next())
				{
					String depName = res.getString("DepName");
					int departmentId = res.getInt("departmentId");
					
					Department dep = departments.get(departmentId);
					
					if(dep == null) {
						departments.put(departmentId, new Department(departmentId, depName));
						dep = departments.get(departmentId);
					}
					
					int idSeller = res.getInt("id");
					String name = res.getString("name");
					String email = res.getString("email");
					Date date = res.getDate("birthDate");
					Double baseSalary = res.getDouble("baseSalary");
					
					Seller vendedor = new Seller(idSeller, name, email, date, baseSalary, dep);
					sellers.add(vendedor);
				}
				return sellers;
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
