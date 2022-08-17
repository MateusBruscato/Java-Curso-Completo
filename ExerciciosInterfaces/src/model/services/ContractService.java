package model.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.entity.Contract;
import model.entity.Installment;

public class ContractService {

	private Payment paymentService;
	
	public ContractService() {		
	}

	public ContractService(Payment paymentService) {
		this.paymentService = paymentService;
	}
	
	public void processContract (Contract contract, int months) {

		double basicInstallment = contract.getContractValue()/months;
		List<Installment> installments = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(contract.getContractDate());
		for(int i = 1; i <= months; i ++)
		{
			double basicInterest = paymentService.interest(basicInstallment, i);
			double paymentFee = paymentService.paymentFee(basicInterest);
			double totalValue = paymentFee;
			calendar.add(Calendar.MONTH, 1);
			installments.add(new Installment(calendar.getTime(), totalValue));
		}
		
		contract.setInstallments(installments);
	}

}
