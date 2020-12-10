package com.revature.service;

import com.revature.exception.BusinessException;

public interface ERSValidationService {
	
	public boolean verifyLogin(String userName, String password) throws BusinessException;	

}
