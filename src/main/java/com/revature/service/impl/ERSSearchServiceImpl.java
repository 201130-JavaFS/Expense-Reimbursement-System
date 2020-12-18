package com.revature.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ERSSearchDAO;
import com.revature.dao.impl.ERSSearchDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Role;
import com.revature.model.Status;
import com.revature.service.ERSSearchService;

public class ERSSearchServiceImpl implements ERSSearchService {
	
	private static Logger log = Logger.getLogger(ERSSearchServiceImpl.class);
	private ERSSearchDAO ersSearchDAO = new ERSSearchDAOImpl();

	@Override
	public List<Reimbursement> getAllEmployeesTickets(int authorId) throws BusinessException {
		if (authorId <= 0) {
			log.warn("Invalid user.");
		}
		else {
			List<Reimbursement> employeesTicketsList = ersSearchDAO.getAllEmployeesTickets(authorId);
			if (employeesTicketsList == null) {
				log.warn("No reimbursement tickets found.");
			}
			return employeesTicketsList;
		}
		return null;
	}
	
	@Override
	public List<Reimbursement> getAllTickets() throws BusinessException {
		List<Reimbursement> ticketsList = ersSearchDAO.getAllTickets();
		if (ticketsList == null) {
			log.warn("No reimbursement tickets found.");
		}
		return ticketsList;
	}

	@Override
	public int getIdByUsername(String username) throws BusinessException {
		int users_id = 0;
		if (username == "" || username == null) {
			log.warn("Invalid username input.");
		}
		else {
			users_id = ersSearchDAO.getIdByUsername(username);
			if (users_id <= 0) {
				log.warn("Invalid user id has been retreived.");
			}
		}
		return users_id;
	}
	
	public boolean verifyLogin(String userName, String password) throws BusinessException {
		boolean checkEmployeeLogin = false;
		if (userName != "" && password != "" && userName != null && password != null) {
			String dbPassword = ersSearchDAO.getDecryptedPasswordByUsername(userName);
			if (dbPassword == null) {
				log.warn("Invalid username.");
			}
			if (password.equals(dbPassword))
				checkEmployeeLogin = true;
		}
		else if (userName == "" || userName == null) {
			throw new BusinessException("Invalid user name.");
		}
		else if (password == "" || userName == null) {
			throw new BusinessException("Invalid password.");
		}
		return checkEmployeeLogin;
	}

	@Override
	public List<Reimbursement> getAllTicketsByStatus(Status status) throws BusinessException {
		List<Reimbursement> reimbList = ersSearchDAO.getAllTicketsByStatus(status);
		if (reimbList == null) {
			log.warn("Could not retreive Tickets by " + status + " status.");
		}
		return reimbList;
	}

	@Override
	public Role getRoleByUsername(String username) throws BusinessException {
		Role role = ersSearchDAO.getRoleByUsername(username);
		if (role == null) {
			log.warn("Could not find Role.");
		}
		return role;
	}

}
