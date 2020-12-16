package com.revature.service.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ERSSearchDAO;
import com.revature.dao.impl.ERSSearchDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Role;
import com.revature.model.Status;
import com.revature.model.Type;
import com.revature.service.ERSSearchService;
import com.revature.service.util.Encoder;

public class ERSSearchServiceImpl implements ERSSearchService {
	
	private static Logger log = Logger.getLogger(ERSSearchServiceImpl.class);
	private ERSSearchDAO ersSearchDAO = new ERSSearchDAOImpl();

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
		ticketsList = ersSearchDAO.getAllTickets();
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

	@Override
	public String getPasswordById(int ers_users_id) throws BusinessException {
		String password = ersSearchDAO.getPasswordById(ers_users_id);
		if (password.equals("")) {
			log.warn("Could not retreive password.");
		}
		return password;
	}

	@Override
	public String getDecryptedPasswordById(int ers_users_id) throws BusinessException {
		String password = ersSearchDAO.getPasswordById(ers_users_id);
		if (password == null) {
			log.warn("Could not retreive password.");
		}
		Encoder encoder = null;
		try {
			encoder = new Encoder();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			log.error(e);
		}
		String decryptedPassword = null;
		try {
			decryptedPassword = Encoder.decrypt(password, encoder.getKey());
		} catch (GeneralSecurityException | IOException e) {
			log.error(e);
		}
		return decryptedPassword;
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
		if (userName != "" && password != "") {
			String dbPassword = ersSearchDAO.getDecryptedPasswordByUsername(userName);
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
			log.warn("Could not retreive Tickets by " + status);
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> getAllTicketsByType(Type type) throws BusinessException {
		List<Reimbursement> reimbList = ersSearchDAO.getAllTicketsByType(type);
		if (reimbList == null) {
			log.warn("Could not retreive Tickets by " + type);
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> getAllTicketsByStatusType(Status status, Type type) throws BusinessException {
		List<Reimbursement> reimbList = ersSearchDAO.getAllTicketsByStatusType(status, type);
		if (reimbList == null) {
			log.warn("Could not retreive Tickets by " + status + " and " + type);
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
