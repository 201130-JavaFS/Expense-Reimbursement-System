package com.revature.main;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exception.BusinessException;
import com.revature.model.Status;
import com.revature.service.ERSManipService;
import com.revature.service.ERSSearchService;
import com.revature.service.impl.ERSManipServiceImpl;
import com.revature.service.impl.ERSSearchServiceImpl;

public class ERSMain {
	
	private static Logger log = Logger.getLogger(ERSMain.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) throws BusinessException, GeneralSecurityException, IOException {
		Scanner scanner = new Scanner(System.in);
		ERSManipService ersManipService = new ERSManipServiceImpl();
		ERSSearchService ersSearchService = new ERSSearchServiceImpl();


/* 
WORKING:
	manip:
		createReimbursementRequest()
		resolveTicketStatus();
		encryptPasswordById()
	search:
		getAllEmpoyeesTickets()
		getNextReimbId
		getAllTickets()
		checkStatusOfTicketById()
		getPasswordById()
		getDecryptedPasswordById()
		getIdByUsername()
		getUserById()
		verifyLogin()
		getAllTicketsByStatus()
		getAllTicketsByRole()
		getAllTicketsByStatusRole()
		
TODO:

 */
	}

}
