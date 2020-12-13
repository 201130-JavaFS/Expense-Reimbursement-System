package com.revature.dao;

import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Status;

public interface ERSManipDAO {
	
	public boolean createNewReimbursementRequest(Reimbursement reimbursement) throws BusinessException;
	public boolean resolveTicketStatus(Status status, int reimb_id, int reimb_resolver) throws BusinessException;
	public void encryptPasswordById(int ers_users_id) throws BusinessException;

}
