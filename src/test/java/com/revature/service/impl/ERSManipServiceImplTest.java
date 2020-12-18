package com.revature.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.exception.BusinessException;
import com.revature.model.Status;
import com.revature.service.ERSManipService;

class ERSManipServiceImplTest {

	private static ERSManipService ersManipService;
	
	@BeforeAll
	public static void setUp() {
		ersManipService = new ERSManipServiceImpl();
	}
	
	@Test
	void testCreateReimbursementRequestWrongUsername() throws BusinessException {
		boolean isCreated = ersManipService.createReimbursementRequest("topher", 10f, "Other", "");
		assertFalse(isCreated);
	}
	
	@Test
	void testCreateReimbursementRequestInvalidUsername() throws BusinessException {
		boolean isCreated = ersManipService.createReimbursementRequest("", 10f, "Other", "");
		assertFalse(isCreated);
	}
	
	@Test
	void testCreateReimbursementRequestInvalidAmount() throws BusinessException {
		boolean isCreated = ersManipService.createReimbursementRequest("tophe", -5, "Other", "");
		assertFalse(isCreated);
	}
	
	@Test
	void testCreateReimbursementRequestInvalidType() throws BusinessException {
		boolean isCreated = ersManipService.createReimbursementRequest("tophe", 10f, "RandomString", "");
		assertFalse(isCreated);
	}
	
	@Test
	void testResolveTicketStatusWrongId() throws BusinessException {
		boolean isResolved = ersManipService.resolveTicketStatus(Status.PENDING, -1, 2);
		assertFalse(isResolved);
	}
	
	@Test
	void testResolveTicketStatusWrongResolver() throws BusinessException {
		boolean isResolved = ersManipService.resolveTicketStatus(Status.PENDING, 1, -1);
		assertFalse(isResolved);
	}

}
