package com.revature.service;

import java.util.List;

import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Role;
import com.revature.model.Status;

public interface ERSSearchService{
	
	public List<Reimbursement> getAllEmployeesTickets(int authorId) throws BusinessException;
	public List<Reimbursement> getAllTickets() throws BusinessException;
	public int getIdByUsername(String username) throws BusinessException;
	public boolean verifyLogin(String userName, String password) throws BusinessException;
	public List<Reimbursement> getAllTicketsByStatus(Status status) throws BusinessException;
	public Role getRoleByUsername(String username) throws BusinessException;
	
}
