package com.revature.dao;

import java.util.List;

import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.model.Type;
import com.revature.model.User;

public interface ERSSearchDAO {
	
	public List<Reimbursement> getAllEmployeesTickets(int authorId) throws BusinessException;
	public int getMaxId() throws BusinessException;
	public List<Reimbursement> getAllTickets(int authorId) throws BusinessException;
	public Status checkStatusOfTicketById(int reimb_id) throws BusinessException;
	public String getPasswordById(int ers_users_id) throws BusinessException;
	public int getIdByUsername(String username) throws BusinessException;
	public boolean verifyLogin(String userName, String password) throws BusinessException;
	public User getUserById(int users_id) throws BusinessException;
	public List<Reimbursement> getAllTicketsByStatus(Status status) throws BusinessException;
	public List<Reimbursement> getAllTicketsByType(Type type) throws BusinessException;
	public List<Reimbursement> getAllTicketsByStatusType(Status status, Type type) throws BusinessException;
	public String getDecryptedPasswordByUsername(String userName) throws BusinessException;

}
