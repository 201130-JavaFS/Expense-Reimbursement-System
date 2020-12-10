package com.revature.dao;

import java.util.List;

import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Status;

public interface ERSSearchDAO {
	
	public List<Reimbursement> getAllEmployeesTickets(int authorId) throws BusinessException;
	public int getMaxId() throws BusinessException;
	public List<Reimbursement> getAllTickets(int authorId) throws BusinessException;
	public Status checkStatusOfTicketById(int reimb_id) throws BusinessException;

}
