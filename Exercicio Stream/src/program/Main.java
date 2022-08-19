package program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		List<Employee> employees = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(path)))
		{
			String line = br.readLine();
			while(line != null)
			{
				String[] employeesInfo = line.split(",");
				employees.add(new Employee(employeesInfo[0], employeesInfo[1], Double.parseDouble(employeesInfo[2])));
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.print("Enter salary: ");
		Double salary = sc.nextDouble();
		
		Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
		
		List<String> emails = employees.stream().filter(x -> x.getSalary() > salary).map(x -> x.getEmail()).sorted(comp).toList();
		
		emails.forEach(System.out::println);
		
		Double sum = employees.stream().filter(x -> x.getName().charAt(0) == 'M').map(x -> x.getSalary()).reduce(0.0, (x,y) -> x + y);
	
		System.out.println(sum);
		sc.close();
	}

}
