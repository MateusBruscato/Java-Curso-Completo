package program;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Pessoa;
import entities.PessoaFisica;
import entities.PessoaJuridica;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of tax payers: ");
		int taxPayers = sc.nextInt();
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for(int i = 0; i < taxPayers; i++)
		{
			System.out.println("Tax payer #" + (i + 1) + " data: " );
			System.out.print("Individual or company (i/c)? ");
			char personType = sc.next().charAt(0);
			System.out.print("Name: ");
			String name = sc.next();
			System.out.print("Annual income: ");
			double annualIncome = sc.nextDouble();
			if(personType == 'i') {
				System.out.print("Health expenditures: ");
				double healthCareSpends = sc.nextDouble();
				pessoas.add(new PessoaFisica(name, annualIncome, healthCareSpends));
			} else {
				System.out.print("Number of employees: ");
				int numberOfEmployees = sc.nextInt();
				pessoas.add(new PessoaJuridica(name, annualIncome, numberOfEmployees));
			}
		}
		
		System.out.println("TAXES PAID: ");
		double taxSum = 0;
		for(Pessoa pessoa : pessoas)
		{
			System.out.print(pessoa.getName() + ": " + String.format("%.2f", pessoa.taxesCalculation()));
			System.out.println();
			taxSum += pessoa.taxesCalculation();
		}
		System.out.println("TOTAL TAXES: " + String.format("%.2f", taxSum));

		sc.close();
	}

}
