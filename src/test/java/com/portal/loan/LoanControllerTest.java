package com.portal.loan;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.loan.controller.LoanController;
import com.portal.loan.dao.LoanRepository;
import com.portal.loan.model.Loan;

public class LoanControllerTest extends LoanAbstractTest {

	@Mock
	private LoanRepository repo;

	@InjectMocks
	private LoanController controller;

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void storeApr() throws Exception {
		String uri = "/store";
		Loan loan = new Loan();
		loan.setDays(370);
		loan.setPrincipal(400000);
		loan.setName("test");
		loan.setRate(8.5);
		loan.setSsn(1234141234);
		loan.setType("student");

		ObjectMapper objectMapper = new ObjectMapper();
		String output = objectMapper.writeValueAsString(loan);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(output))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void getApr() throws Exception {
		when(repo.findById(1l)).thenReturn(new Loan());
		ResponseEntity<Loan> obj = controller.fetchLoan(1l);

		assertEquals(200, obj.getStatusCodeValue());
	}

}