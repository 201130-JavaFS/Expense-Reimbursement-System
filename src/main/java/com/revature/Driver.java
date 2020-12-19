package com.revature;

import com.revature.exception.BusinessException;
import com.revature.service.ERSManipService;
import com.revature.service.impl.ERSManipServiceImpl;

public class Driver {
	
	private static ERSManipService ersManipService = new ERSManipServiceImpl();

	public static void main(String[] args) throws BusinessException {
		
		// encrypt password after inserting user to database.
		// only run once per user!
		for (int i = 1; i <= 6; i++) {
			ersManipService.encryptPasswordById(i);
		}
		
	}

}
