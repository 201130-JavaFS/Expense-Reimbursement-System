package com.revature.service.impl;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import com.revature.dao.ERSManipDAO;
import com.revature.dao.impl.ERSManipDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.model.Type;
import com.revature.service.ERSManipService;
import com.revature.service.ERSSearchService;

public class ERSManipServiceImpl implements ERSManipService {
	
	private static Logger log = Logger.getLogger(ERSManipServiceImpl.class);
	ERSSearchService ersSearchService = new ERSSearchServiceImpl();
	ERSManipDAO ersManipDAO = new ERSManipDAOImpl();

	@Override
	public boolean createReimbursementRequest() throws BusinessException {
		boolean requestCreated = false;
//		int reimb_id = ersSearchService.getNextReimbId();
		// retrieve author id??
		Reimbursement reimbursement = new Reimbursement(3, 200f, LocalDateTime.now(), "Ate the best sushi."
				, 4 /*test employee*/, Status.PENDING, Type.FOOD);
		ersManipDAO.createNewReimbursementRequest(reimbursement);
		
		return requestCreated;
	}

	@Override
	public boolean resolveTicketStatus(Status status, int reimb_id, int reimb_resolver) throws BusinessException {
		boolean isResolved = false;
		if (ersSearchService.checkStatusOfTicketById(reimb_id) != Status.PENDING) {
			log.warn("Ticket was already resolved.");
		}
		else {
			isResolved = ersManipDAO.resolveTicketStatus(status, reimb_id, reimb_resolver);
			if (!isResolved) {
				log.warn("Could not resolve reimbursement ticket.");
			}
		}
		return isResolved;
	}

}
