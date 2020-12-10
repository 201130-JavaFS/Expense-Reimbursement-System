package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.dao.ERSValidationDAO;
import com.revature.dao.util.ERSDbQueries;
import com.revature.dao.util.ERSDbUtilProps;
import com.revature.dao.util.ERSPostgresSqlConnection;
import com.revature.exception.BusinessException;

public class ERSValidationDAOImpl implements ERSValidationDAO {
	
	private static Logger log = Logger.getLogger(ERSValidationDAOImpl.class);

	public boolean verifyLogin(String userName, String password) throws BusinessException {
		boolean checkEmployeeLogin = false;
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.VERIFY_LOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getString("ers_password").equals(password)) {
					checkEmployeeLogin = true;
				}
				else {
					log.warn("Wrong password.");
				}
			}
			else {
				log.warn("Could not find ID.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return checkEmployeeLogin;
	}

}
