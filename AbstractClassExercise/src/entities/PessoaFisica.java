package entities;

public class PessoaFisica extends Pessoa {

	private double healthCareSpends;

	public PessoaFisica() {
		super();
	}

	public PessoaFisica(String name, double annualIncome, double healthCareSpends) {
		super(name, annualIncome);
		this.healthCareSpends = healthCareSpends;
	}

	public double getHealthCareSpends() {
		return healthCareSpends;
	}

	public void setHealthCareSpends(double healthCareSpends) {
		this.healthCareSpends = healthCareSpends;
	}

	@Override
	public double taxesCalculation() {
		
		double tax = (getAnnualIncome() > 20000) ? getAnnualIncome() * 0.25 - healthCareSpends * 0.5 : getAnnualIncome() * 0.15 - healthCareSpends * 0.5;
		
		if(tax < 0) {
			tax = 0;
		}
		
		return tax;
	}
}
