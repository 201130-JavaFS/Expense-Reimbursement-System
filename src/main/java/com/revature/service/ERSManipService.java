package com.revature.service;

import com.revature.exception.BusinessException;
import com.revature.model.Status;

public interface ERSManipService {
	
	public boolean createReimbursementRequest() throws BusinessException;
	public boolean resolveTicketStatus(Status status, int reimb_id, int reimb_resolver) throws BusinessException;

}
