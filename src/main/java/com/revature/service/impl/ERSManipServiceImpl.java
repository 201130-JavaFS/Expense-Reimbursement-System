package com.revature.service.impl;

import org.apache.log4j.Logger;

import com.revature.dao.ERSManipDAO;
import com.revature.dao.ERSSearchDAO;
import com.revature.dao.impl.ERSManipDAOImpl;
import com.revature.dao.impl.ERSSearchDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.model.User;
import com.revature.service.ERSManipService;
import com.revature.service.ERSSearchService;
import com.revature.service.util.DBConversions;

public class ERSManipServiceImpl implements ERSManipService {
	
	private static Logger log = Logger.getLogger(ERSManipServiceImpl.class);
	private ERSSearchService ersSearchService = new ERSSearchServiceImpl();
	private ERSManipDAO ersManipDAO = new ERSManipDAOImpl();
	private ERSSearchDAO ersSearchDAO = new ERSSearchDAOImpl();

	@Override
	public boolean createReimbursementRequest(String username, float amount, String type, String description) throws BusinessException {
		Reimbursement reimbursement = new Reimbursement();
		int id = ersSearchDAO.getMaxId() + 1;
		User user = ersSearchDAO.getUserByUsername(username);
		reimbursement.setId(id);
		reimbursement.setAmount(amount);
		reimbursement.setDescription(description);
		reimbursement.setAuthor(user);
		reimbursement.setStatus(Status.PENDING);
		reimbursement.setType(DBConversions.stringToType(type));
		boolean isCreated = ersManipDAO.createNewReimbursementRequest(reimbursement);
		
		return isCreated;
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

	@Override
	public void encryptPasswordById(int reimb_id) throws BusinessException {
		ersManipDAO.encryptPasswordById(reimb_id);
	}

}
