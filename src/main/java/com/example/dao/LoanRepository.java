package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bean.LoanApplianceDto;


public interface LoanRepository extends JpaRepository<LoanApplianceDto, Integer> {

}
