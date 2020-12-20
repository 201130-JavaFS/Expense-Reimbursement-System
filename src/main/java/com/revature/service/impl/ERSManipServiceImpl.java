package com.revature.service.impl;

import java.sql.Blob;

import org.apache.log4j.Logger;

import com.revature.dao.ERSManipDAO;
import com.revature.dao.ERSSearchDAO;
import com.revature.dao.impl.ERSManipDAOImpl;
import com.revature.dao.impl.ERSSearchDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.model.Type;
import com.revature.model.User;
import com.revature.service.ERSManipService;
import com.revature.service.util.DBConversions;

public class ERSManipServiceImpl implements ERSManipService {
	
	private static Logger log = Logger.getLogger(ERSManipServiceImpl.class);
	private ERSManipDAO ersManipDAO = new ERSManipDAOImpl();
	private ERSSearchDAO ersSearchDAO = new ERSSearchDAOImpl();

	@Override
	public boolean createReimbursementRequest(String username, float amount, String type, String description, Blob receipt) throws BusinessException {
		boolean isCreated = false;
		Reimbursement reimbursement = new Reimbursement();
		int id = ersSearchDAO.getMaxId() + 1;
		Type enumType = DBConversions.stringToType(type);
		if (username == "" || username == null) {
			log.warn("Invalid username.");
		}
		else if (amount <= 0) {
			log.warn("Invalid amount.");
		}
		else if (enumType == null) {
			log.warn("Invalid type.");
		}
		else {
			User user = ersSearchDAO.getUserByUsername(username);
			if (user != null) {
				reimbursement.setId(id);
				reimbursement.setAmount(amount);
				reimbursement.setDescription(description);
				reimbursement.setAuthor(user);
				reimbursement.setStatus(Status.PENDING);
				reimbursement.setType(enumType);
				reimbursement.setReceipt(receipt);
				isCreated = ersManipDAO.createNewReimbursementRequest(reimbursement);
			}
			else {
				log.warn("Cannot find user with username: " + username + ".");
			}
		}
		if (!isCreated) {
			log.warn("Failed to create reimbursement.");
		}
		return isCreated;
	}

	@Override
	public boolean resolveTicketStatus(Status status, int reimb_id, int reimb_resolver) throws BusinessException {
		boolean isResolved = false;
		Status currentStatus = ersSearchDAO.checkStatusOfTicketById(reimb_id);
		if (currentStatus == null) {
			log.warn("Reimbursement does not exist.");
		}
		if (currentStatus != Status.PENDING) {
			log.warn("Ticket was already resolved.");
		}
		else {
			if (ersSearchDAO.getUserById(reimb_resolver) != null) {
				isResolved = ersManipDAO.resolveTicketStatus(status, reimb_id, reimb_resolver);
			}
			else {
				log.warn("Invalid user id.");
			}
			if (!isResolved) {
				log.warn("Could not resolve reimbursement ticket.");
			}
		}
		return isResolved;
	}

	@Override
	public void encryptPasswordById(int reimb_id) throws BusinessException {
		// for manual use when registering users
		ersManipDAO.encryptPasswordById(reimb_id);
	}

}
