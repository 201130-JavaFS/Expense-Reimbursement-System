package com.revature.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ERSSearchDAO;
import com.revature.dao.impl.ERSSearchDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.service.ERSSearchService;

public class ERSSearchServiceImpl implements ERSSearchService {
	
	private static Logger log = Logger.getLogger(ERSSearchServiceImpl.class);
	ERSSearchDAO ersSearchDAO = new ERSSearchDAOImpl();

	@Override
	public List<Reimbursement> getAllEmployeesTickets(int authorId) throws BusinessException {
		List<Reimbursement> employeesTicketsList = new ArrayList<>();
		
		employeesTicketsList = ersSearchDAO.getAllEmployeesTickets(authorId);
		if (employeesTicketsList.size() == 0) {
			log.warn("No Reimbursement Tickets Found.");
		}
		return employeesTicketsList;
	}

	@Override
	public int getNextReimbId() throws BusinessException {
		int maxId = ersSearchDAO.getMaxId();
		
		return maxId + 1;
	}
	
	@Override
	public List<Reimbursement> getAllTickets() throws BusinessException {
		List<Reimbursement> ticketsList = new ArrayList<>();
		int authorId = 1;
		
		ticketsList = ersSearchDAO.getAllTickets(authorId);
		if (ticketsList.size() == 0) {
			log.warn("No Reimbursement Tickets Found.");
		}
		return ticketsList;
	}

	@Override
	public Status checkStatusOfTicketById(int reimb_id) throws BusinessException {
		Status status = ersSearchDAO.checkStatusOfTicketById(reimb_id);
		if (status == null) {
			log.warn("Could not find status.");
		}
		return status;
	}

}
