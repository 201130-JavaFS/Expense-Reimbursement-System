package com.revature.service.impl;

import com.revature.dao.ERSValidationDAO;
import com.revature.dao.impl.ERSValidationDAOImpl;
import com.revature.exception.BusinessException;
import com.revature.service.ERSValidationService;

public class ERSValidationServiceImpl implements ERSValidationService {

	ERSValidationDAO ersValidationDAO = new ERSValidationDAOImpl();
	
	public boolean verifyLogin(String userName, String password) throws BusinessException {
		boolean checkEmployeeLogin = false;
		if (userName != "" && password != "") {
			checkEmployeeLogin = ersValidationDAO.verifyLogin(userName, password);
		}
		else if (userName == "" || userName == null) {
			throw new BusinessException("Invalid user name.");
		}
		else if (password == "" || userName == null) {
			throw new BusinessException("Invalid password.");
		}
		return checkEmployeeLogin;
	}

}
