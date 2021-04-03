package com.portal.loan.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.portal.loan.model.Loan;

@Mapper
public interface LoanRepository {

	@Insert("insert into loan(name,ssn,  dob, principal, rate, type, days, apr) "
			+ " values (#{name}, #{ssn}, #{dob}, #{principal}, #{rate}, #{type}, #{days}, #{apr})")
	public int insert(Loan en);

	@Select("select * from loan where loanid = #{loanId}")
	public Loan findById(Long loanId);

}
