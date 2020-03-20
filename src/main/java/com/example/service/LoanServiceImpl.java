package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.LoanApplianceDto;
import com.example.bean.UserLoan;
import com.example.dao.LoanImpl;

@Service

public class LoanServiceImpl implements LoanServiceI {

	@Autowired
	LoanImpl loanimpl;

	@Override
	public void createLoan(LoanApplianceDto userloan) {
		
		calicualteIntrest();
     loanimpl.createLoan(userloan);

	}
	
	
	
	public static void  calicualteIntrest() {
		
		UserLoan user=new UserLoan();
		int time= user.getNoOfears();
		Double rate =user.getPrincipal();
		
		
		
		
		
		
		
		
		
		
		
	}

}
