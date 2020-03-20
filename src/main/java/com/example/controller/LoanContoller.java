package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.LoanApplianceDto;
import com.example.bean.User;
import com.example.service.LoanServiceImpl;

@RestController
@RequestMapping("/loanController")
public class LoanContoller {

	@Autowired
	LoanServiceImpl loanserviceimpl;

	@RequestMapping("/createloan")
	public void createLoan(@RequestBody LoanApplianceDto userloan) {
		loanserviceimpl.createLoan(userloan);
        System.out.println("loan created");

	}

}
