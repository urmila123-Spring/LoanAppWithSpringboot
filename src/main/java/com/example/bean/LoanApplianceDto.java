package com.example.bean;

import javax.persistence.Entity;

@Entity
public class LoanApplianceDto {
	
	private double loanamount;
	private int noofyears;
	public double getLoanamount() {
		return loanamount;
	}
	public void setLoanamount(double loanamount) {
		this.loanamount = loanamount;
	}
	public int getNoofyears() {
		return noofyears;
	}
	public void setNoofyears(int noofyears) {
		this.noofyears = noofyears;
	}
	@Override
	public String toString() {
		return "LoanApplianceDto [loanamount=" + loanamount + ", noofyears=" + noofyears + "]";
	}
	

	
	
}
