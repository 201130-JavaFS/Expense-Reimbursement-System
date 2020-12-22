//package com.revature.service.impl;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import com.revature.exception.BusinessException;
//import com.revature.model.Reimbursement;
//import com.revature.model.Role;
//import com.revature.model.Status;
//import com.revature.service.ERSSearchService;
//
//class ERSSearchServiceImplTest {
//
//	private static ERSSearchService ersSearchService;
//	
//	@BeforeAll
//	public static void setUp() {
//		ersSearchService = new ERSSearchServiceImpl();
//	}
//	
//	@Test
//	void testGetAllEmployeesTicketsInvalidId() throws BusinessException {
//		List<Reimbursement> reimbList = ersSearchService.getAllEmployeesTickets(-1);
//		assertNull(reimbList);
//	}
//	
//	@Test
//	void testGetAllEmployeesTickets() throws BusinessException {
//		List<Reimbursement> reimbList = ersSearchService.getAllEmployeesTickets(1);
//		assertNotEquals(0, reimbList.size());
//	}
//	
//	@Test
//	void testGetAllEmployeesTicketsNonExistantUser() throws BusinessException {
//		List<Reimbursement> reimbList = ersSearchService.getAllEmployeesTickets(999);
//		assertNull(reimbList);
//	}
//	
//	@Test
//	void testGetAllTickets() throws BusinessException {
//		List<Reimbursement> reimbList = ersSearchService.getAllTickets();
//		assertNotEquals(0,reimbList.size());
//	}
//	
//	@Test
//	void testGetIdByUsernameNullUsername() throws BusinessException {
//		int userId = ersSearchService.getIdByUsername(null);
//		assertEquals(0, userId);
//	}
//	
//	@Test
//	void testGetIdByUsername() throws BusinessException {
//		int userId = ersSearchService.getIdByUsername("tophe");
//		assertEquals(1, userId);
//	}
//	
//	@Test
//	void testVerifyLogin() throws BusinessException {
//		boolean checkLogin = ersSearchService.verifyLogin("tophe", "pass");
//		assertTrue(checkLogin);
//	}
//	
//	@Test
//	void testVerifyLoginInvalidUsername() throws BusinessException {
//		boolean checkLogin = ersSearchService.verifyLogin(null, "pass");
//		assertFalse(checkLogin);
//	}
//	
//	@Test
//	void testVerifyLoginInvalidPassword() throws BusinessException {
//		boolean checkLogin = ersSearchService.verifyLogin("tophe", null);
//		assertFalse(checkLogin);
//	}
//	
//	@Test
//	void testVerifyLoginWrongUsername() throws BusinessException {
//		boolean checkLogin = ersSearchService.verifyLogin("topher", "pass");
//		assertFalse(checkLogin);
//	}
//	
//	@Test
//	void testGetAllTicketsByStatusPending() throws BusinessException {
//		List<Reimbursement> reimbList = ersSearchService.getAllTicketsByStatus(Status.PENDING);
//		assertNotEquals(0, reimbList.size());
//	}
//	
//	@Test
//	void testGetAllTicketsByStatusNull() throws BusinessException {
//		List<Reimbursement> reimbList = ersSearchService.getAllTicketsByStatus(null);
//		assertNull(reimbList);
//	}
//	
//	@Test
//	void testGetRoleByUsernameEmployee() throws BusinessException {
//		Role role = ersSearchService.getRoleByUsername("tophe");
//		boolean checkRole = false;
//		if (role == Role.EMPLOYEE) checkRole = true;
//		assertTrue(checkRole);
//	}
//	
//	@Test
//	void testGetRoleByUsernameWrongUsername() throws BusinessException {
//		Role role = ersSearchService.getRoleByUsername("topher");
//		assertNull(role);
//	}
//
//}
