package model.services;

public interface Payment {

	public double paymentFee(double amount);
	public double interest(double amount, int months);
}
