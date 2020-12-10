package com.revature.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exception.BusinessException;
import com.revature.model.Status;
import com.revature.service.ERSManipService;
import com.revature.service.ERSSearchService;
import com.revature.service.ERSValidationService;
import com.revature.service.impl.ERSManipServiceImpl;
import com.revature.service.impl.ERSSearchServiceImpl;
import com.revature.service.impl.ERSValidationServiceImpl;

public class ERSMain {
	
	private static Logger log = Logger.getLogger(ERSMain.class);

	@SuppressWarnings("resource")
	public static void main(String[] args) throws BusinessException {
		Scanner scanner = new Scanner(System.in);
		ERSValidationService ersValidationService = new ERSValidationServiceImpl();
		ERSManipService ersManipService = new ERSManipServiceImpl();
		ERSSearchService ersSearchService = new ERSSearchServiceImpl();
	
		boolean isResolved = ersManipService.resolveTicketStatus(Status.APPROVED, 3, 2);
		if (isResolved) log.info("ticket approved");
		else log.info("ticket approval failed");
		
//		List<Reimbursement> rList = ersSearchService.getAllTickets();
//		for (Reimbursement r : rList) {
//			log.info(r.toString());
//		}

/* 
WORKING:
	manip:
		createReimbursementRequest()
		*resolveTicketStatus();
	search:
		getAllEmpoyeesTickets()
		getAllTickets()
	validation:
		verifyLogin()
TODO:
getReimbursementById() // maybe?
 */
	}

}
