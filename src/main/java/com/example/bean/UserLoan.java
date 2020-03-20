package com.example.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class UserLoan {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)      
    private int loanId;
    private String loanType;
    private Long amount;  
    private int  noOfyears; 
    private Double principal; 
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;	
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public int getNoOfears() {
		return noOfyears;
	}
	public UserLoan() {
		super();
	}
	public void setNoOfears(int noOfears) {
		this.noOfyears = noOfears;
	}
	public Double getPrincipal() {
		return principal;
	}
	public void setPrincipal(Double principal) {
		this.principal = principal;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "UserLoan [loanId=" + loanId + ", loanType=" + loanType + ", amount=" + amount + ", noOfYears" + noOfyears
				+ ", principal=" + principal + ", user=" + user + "]";
	}
    
    
    
    
    
    
    

}
