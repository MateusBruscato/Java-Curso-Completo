package application;

import java.text.ParseException;
import java.util.List;

import db.DB;
import model.daos.DaoFactory;
import model.daos.DepartmentDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) throws ParseException {
//
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date data = sdf.parse("10/10/1990");
//		Department departamento = new Department(1, "Computers");
//		Seller vendedor = new Seller("Edson Arantes","edson@gmail.com", data, 2350.00, departamento );
//		
		DepartmentDao dd = DaoFactory.createDepartmentDao();
		
		Department novoDep = new Department(5, "Drinks");
		dd.update(novoDep);
		
		List<Department> list = dd.findAll();
		
		list.forEach(System.out::println);
		System.out.println();
		
		System.out.println(dd.findById(2));
		
		DB.closeConnection();
	}

}
