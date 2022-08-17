package program;

import java.util.Scanner;

import entities.Account;
import exceptions.ValueLimitException;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter account data");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Holder: ");
		sc.next();
		String holder = sc.nextLine();
		System.out.print("Initial balance: ");
		double balance = sc.nextDouble();
		System.out.print("Withdraw limit: ");
		double withdrawLimit = sc.nextDouble();
		Account account = new Account(number, holder, balance, withdrawLimit);
		
		System.out.print("Enter amount for withdraw: ");
		double withdrawAmount = sc.nextDouble();
		
		try {
			account.withdraw(withdrawAmount);
			System.out.print("New balance: ");
			System.out.print(String.format("%.2f", account.getBalance()));
		} catch (ValueLimitException e) {
			System.out.println("Error message: " + e.getMessage());
		}
		

		
		sc.close();
	}

}
