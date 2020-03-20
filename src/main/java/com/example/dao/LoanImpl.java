package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.bean.LoanApplianceDto;
import com.example.bean.User;
import com.example.bean.UserLoan;

@Repository 

public class LoanImpl {  
	
	
	@Autowired
	LoanRepository loanrepo;
	
	
	public void createLoan(LoanApplianceDto userloan) {
		loanrepo.save(userloan);
		
	}

}
