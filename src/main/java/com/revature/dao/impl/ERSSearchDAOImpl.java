package com.revature.dao.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ERSSearchDAO;
import com.revature.dao.util.ERSDbQueries;
import com.revature.dao.util.ERSDbUtilProps;
import com.revature.dao.util.ERSPostgresSqlConnection;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Role;
import com.revature.model.Status;
import com.revature.model.User;
import com.revature.service.util.DBConversions;
import com.revature.service.util.Encoder;

public class ERSSearchDAOImpl implements ERSSearchDAO {
	
	private static Logger log = Logger.getLogger(ERSSearchDAOImpl.class);

	public List<Reimbursement> getAllEmployeesTickets(int authorId) throws BusinessException {
		List<Reimbursement> employeesTicketsList = new ArrayList<>();
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_ALL_EMPLOYEES_TICKETS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, authorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				LocalDateTime localDateTime = resultSet.getTimestamp("reimb_submitted").toLocalDateTime();
				String submittedLocalDateTime = DBConversions.getDateFromLocalDateTime(localDateTime);
				Reimbursement reimbursement = new Reimbursement(
					resultSet.getInt("reimb_id"),
					resultSet.getFloat("reimb_amount"),
					submittedLocalDateTime,
					resultSet.getString("reimb_description"),
					getUserById(resultSet.getInt("reimb_author")),
					DBConversions.databaseToStatus(resultSet.getInt("reimb_status_id")),
					DBConversions.databaseToType(resultSet.getInt("reimb_type_id"))
				);
				if(resultSet.getTimestamp("reimb_resolved") != null) {
					localDateTime = resultSet.getTimestamp("reimb_resolved").toLocalDateTime();
					String resolvedLocalDateTime = DBConversions.getDateFromLocalDateTime(localDateTime);
					reimbursement.setResolved(resolvedLocalDateTime);
					reimbursement.setResolver(getUserById(resultSet.getInt("reimb_resolver")));
				}
				employeesTicketsList.add(reimbursement);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		if (employeesTicketsList.size() == 0) return null;
		else return employeesTicketsList;
	}

	@Override
	public int getMaxId() throws BusinessException {
		int maxId = 0;
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_MAX_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				maxId = resultSet.getInt("maxId");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return maxId;
	}
	
	@Override
	public List<Reimbursement> getAllTickets() throws BusinessException {
		List<Reimbursement> ticketsList = new ArrayList<>();
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_ALL_TICKETS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				LocalDateTime localDateTime = resultSet.getTimestamp("reimb_submitted").toLocalDateTime();
				String submittedLocalDateTime = DBConversions.getDateFromLocalDateTime(localDateTime);
				Reimbursement reimbursement = new Reimbursement(
					resultSet.getInt("reimb_id"),
					resultSet.getFloat("reimb_amount"),
					submittedLocalDateTime,
					resultSet.getString("reimb_description"),
					getUserById(resultSet.getInt("reimb_author")),
					DBConversions.databaseToStatus(resultSet.getInt("reimb_status_id")),
					DBConversions.databaseToType(resultSet.getInt("reimb_type_id"))
				);
				if(resultSet.getTimestamp("reimb_resolved") != null) {
					localDateTime = resultSet.getTimestamp("reimb_resolved").toLocalDateTime();
					String resolvedLocalDateTime = DBConversions.getDateFromLocalDateTime(localDateTime);
					reimbursement.setResolved(resolvedLocalDateTime);
					reimbursement.setResolver(getUserById(resultSet.getInt("reimb_resolver")));
				}
				ticketsList.add(reimbursement);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		if (ticketsList.size() == 0) return null;
		else return ticketsList;
	}

	@Override
	public Status checkStatusOfTicketById(int reimb_id) throws BusinessException {
		Status status = null;
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.CHECK_STATUS_OF_REIMBURSEMENT_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reimb_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				status = DBConversions.databaseToStatus(resultSet.getInt("reimb_status_id"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return status;
	}

	@Override
	public int getIdByUsername(String username) throws BusinessException {
		int users_id = 0;
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_ID_BY_USERNAME;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				users_id = resultSet.getInt("ers_users_id");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return users_id;
	}
	
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

	@Override
	public List<Reimbursement> getAllTicketsByStatus(Status status) throws BusinessException {
		List<Reimbursement> reimbList = new ArrayList<>();
		if (status == null) return null;
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_ALL_TICKETS_BY_STATUS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, DBConversions.statusToDatabase(status));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setId(resultSet.getInt("reimb_id"));
				reimbursement.setAmount(resultSet.getFloat("reimb_amount"));
				LocalDateTime localDateTime = resultSet.getTimestamp("reimb_submitted").toLocalDateTime();
				String submittedLocalDateTime = DBConversions.getDateFromLocalDateTime(localDateTime);
				reimbursement.setSubmitted(submittedLocalDateTime);
				reimbursement.setDescription(resultSet.getString("reimb_description"));
				reimbursement.setAuthor(getUserById(resultSet.getInt("reimb_author")));
				reimbursement.setStatus(status);
				reimbursement.setType(DBConversions.databaseToType(resultSet.getInt("reimb_type_id")));
				if(resultSet.getTimestamp("reimb_resolved") != null) {
					localDateTime = resultSet.getTimestamp("reimb_resolved").toLocalDateTime();
					String resolvedLocalDateTime = DBConversions.getDateFromLocalDateTime(localDateTime);
					reimbursement.setResolved(resolvedLocalDateTime);
					reimbursement.setResolver(getUserById(resultSet.getInt("reimb_resolver")));
				}
				reimbList.add(reimbursement);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		if (reimbList.size() == 0) return null;
		else return reimbList;
	}

	@Override
	public String getDecryptedPasswordByUsername(String userName) throws BusinessException {
		String password = "";
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_PASSWORD_BY_USERNAME;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				password = resultSet.getString("ers_password");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		Encoder encoder = null;
		String decryptedPassword = null;
		if (password != "" && password != null) {
			try {
				encoder = new Encoder();
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				log.error(e);
			}
			try {
				decryptedPassword = Encoder.decrypt(password, encoder.getKey());
			} catch (GeneralSecurityException | IOException e) {
				log.error(e);
			}
		}
		return decryptedPassword;
	}

	@Override
	public User getUserByUsername(String username) throws BusinessException {
		User user = null;
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_USER_BY_USERNAME;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User tempUser = new User(
						resultSet.getInt("ers_users_id"),
						username,
						resultSet.getString("user_first_name"),
						resultSet.getString("user_last_name"),
						resultSet.getString("user_email"),
						DBConversions.databaseToRole(resultSet.getInt("user_role_id"))
						);
				user = tempUser;
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return user;
	}

	@Override
	public Role getRoleByUsername(String username) throws BusinessException {
		int user_role_id = 0;
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_ROLE_BY_USERNAME;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user_role_id = resultSet.getInt("user_role_id");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		if (user_role_id == 1) return Role.EMPLOYEE;
		else if (user_role_id == 2) return Role.FINANCEMANAGER;
		else return null;
	}
	
	@Override
	public User getUserById(int users_id) throws BusinessException {
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_USER_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, users_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				User user = new User(
					users_id,
					resultSet.getString("ers_username"),
					resultSet.getString("user_first_name"),
					resultSet.getString("user_last_name"),
					resultSet.getString("user_email"),
					DBConversions.databaseToRole(resultSet.getInt("user_role_id"))
					);
				return user;
			}
			else {
				log.warn("Could not find User.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return null;
	}
	
	@Override
	public String getPasswordById(int ers_users_id) throws BusinessException {
		String password = "";
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_PASSWORD_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, ers_users_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				password = resultSet.getString("ers_password");
			}
			else {
				log.warn("Error: Ticket does not exist.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return password;
	}

}
