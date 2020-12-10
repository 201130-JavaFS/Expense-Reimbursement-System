package com.revature.dao;

import com.revature.exception.BusinessException;

public interface ERSValidationDAO {
	
	public boolean verifyLogin(String userName, String password) throws BusinessException;

}
