package program;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entity.Contract;
import model.entity.Installment;
import model.services.ContractService;
import model.services.PayPalPayment;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		

		System.out.println("Enter contract data:");
		System.out.print("Number: ");
		int contractNumber = sc.nextInt();
		System.out.print("Date: (dd/mm/yyyy):");
		sc.nextLine();
		String data = sc.nextLine();
		Date date = null;
		try {
			date = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.print("Contract value: ");
		double contractValue = sc.nextDouble();
		
		Contract contract = new Contract(contractNumber, date, contractValue);
		
		System.out.print("Enter the number of installments: ");
		int numberInstallments = sc.nextInt();
		
		ContractService contractService = new ContractService(new PayPalPayment());
		
		contractService.processContract(contract, numberInstallments);
		
		for(Installment instal : contract.getInstallments()) {
			System.out.println(instal.toString());
		}
		sc.close();
	}

}
