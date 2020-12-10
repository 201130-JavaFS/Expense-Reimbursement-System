package com.revature.service;

import java.util.List;

import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Status;

public interface ERSSearchService{
	
	public List<Reimbursement> getAllEmployeesTickets(int authorId) throws BusinessException;
	public int getNextReimbId() throws BusinessException;
	public List<Reimbursement> getAllTickets() throws BusinessException;
	public Status checkStatusOfTicketById(int reimb_id) throws BusinessException;

}
