package model.entity;

import java.util.Date;
import java.util.List;

public class Contract {

	private int contractNumber;
	private Date contractDate;
	private double contractValue;
	private List<Installment> installments;
	
	public Contract() {
		
	}
	
	public Contract (int contractNumber, Date contractDate, double contractValue) {
		this.contractNumber = contractNumber;
		this.contractDate = contractDate;
		this.contractValue = contractValue;
	}

	public int getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(int contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public double getContractValue() {
		return contractValue;
	}

	public void setContractValue(double contractValue) {
		this.contractValue = contractValue;
	}

	public List<Installment> getInstallments() {
		return installments;
	}

	public void setInstallments(List<Installment> installments) {
		this.installments = installments;
	}
	
	
}
