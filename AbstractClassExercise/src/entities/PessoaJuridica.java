package entities;

public class PessoaJuridica extends Pessoa {

	private int numberOfEmployees;
	
	public PessoaJuridica() {
		super();
	}
	
	public PessoaJuridica(String name, double annualIncome, int numberOfEmployees) {
		super(name, annualIncome);
		this.numberOfEmployees = numberOfEmployees;
	}
	
	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	@Override
	public double taxesCalculation() {
		
		double tax = (numberOfEmployees > 10) ? getAnnualIncome() * 0.14 : getAnnualIncome() * 0.16;
		return tax;
	}

}
