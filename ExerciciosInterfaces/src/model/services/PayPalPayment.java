package model.services;

public class PayPalPayment implements Payment{

	@Override
	public double paymentFee(double amount) {
		return amount *= (1 + 0.02);
		
	}

	@Override
	public double interest(double amount, int months) {
		double feeMonth = 0.01;
		return amount *= (1 + (feeMonth * months));
	}

}
