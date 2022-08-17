package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import enumerate.OrderStatus;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter client data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		
		System.out.print("Email: ");
		String email = sc.next();
		
		System.out.print("Birth date (DD/MM/YYYY): ");
		String birthDate = sc.next();
		
		Client newCostumer = null;
		try {
			newCostumer = new Client(name, email, sdf.parse(birthDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		OrderStatus status = OrderStatus.valueOf(sc.next());
		
		System.out.print("How many items to this order? ");
		int numberOfItems = sc.nextInt();
		
		Order newOrder = new Order(status, newCostumer);
		
		for(int i = 0; i < numberOfItems; i++)
		{
			System.out.println("Enter #" + (i+1) + " item data:");
			System.out.print("Product name: ");
			String productName = sc.next();
			System.out.print("Product price: ");
			double productPrice = sc.nextDouble();
			System.out.print("Quantity: ");
			int quantity = sc.nextInt();
			
			OrderItem orderItem = new OrderItem(quantity, new Product(productName, productPrice));
			newOrder.addItem(orderItem);
		}
		
		System.out.println(newOrder.toString());
	
		sc.close();
	}

}
