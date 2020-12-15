package com.revature.service;

import com.revature.exception.BusinessException;
import com.revature.model.Status;

public interface ERSManipService {
	
	public boolean createReimbursementRequest(String username, float amount, String type, String description) throws BusinessException;
	public boolean resolveTicketStatus(Status status, int reimb_id, int reimb_resolver) throws BusinessException;
	public void encryptPasswordById(int reimb_id) throws BusinessException;

}
