package com.revature.dao.impl;

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
import com.revature.model.Status;
import com.revature.service.util.DBConversions;

public class ERSSearchDAOImpl implements ERSSearchDAO {
	
	private static Logger log = Logger.getLogger(ERSSearchDAOImpl.class);

	public List<Reimbursement> getAllEmployeesTickets(int authorId) throws BusinessException {
		List<Reimbursement> employeesTicketsList = new ArrayList<>();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_ALL_EMPLOYEES_TICKETS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, authorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				LocalDateTime submittedLocalDateTime = resultSet.getTimestamp("reimb_submitted").toLocalDateTime();
//				String formattedDateTime = localDateTime.format(formatter);
				Reimbursement reimbursement = new Reimbursement(
					resultSet.getInt("reimb_id"),
					resultSet.getFloat("reimb_amount"),
					submittedLocalDateTime,
					resultSet.getString("reimb_description"),
					resultSet.getInt("reimb_author"),
					DBConversions.databaseToStatus(resultSet.getInt("reimb_status_id")),
					DBConversions.databaseToType(resultSet.getInt("reimb_type_id"))
				);
				if(resultSet.getTimestamp("reimb_resolved") != null) {
					LocalDateTime resolvedLocalDateTime = resultSet.getTimestamp("reimb_resolved").toLocalDateTime();
					reimbursement.setResolved(resolvedLocalDateTime);
					reimbursement.setResolver(resultSet.getInt("reimb_resolver"));
				}
				employeesTicketsList.add(reimbursement);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return employeesTicketsList;
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
			else {
				log.warn("Error: No records exist.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return maxId;
	}
	
	@Override
	public List<Reimbursement> getAllTickets(int authorId) throws BusinessException {
		List<Reimbursement> ticketsList = new ArrayList<>();
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.GET_ALL_TICKETS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				LocalDateTime submittedLocalDateTime = resultSet.getTimestamp("reimb_submitted").toLocalDateTime();
				Reimbursement reimbursement = new Reimbursement(
					resultSet.getInt("reimb_id"),
					resultSet.getFloat("reimb_amount"),
					submittedLocalDateTime,
					resultSet.getString("reimb_description"),
					resultSet.getInt("reimb_author"),
					DBConversions.databaseToStatus(resultSet.getInt("reimb_status_id")),
					DBConversions.databaseToType(resultSet.getInt("reimb_type_id"))
				);
				if(resultSet.getTimestamp("reimb_resolved") != null) {
					LocalDateTime resolvedLocalDateTime = resultSet.getTimestamp("reimb_resolved").toLocalDateTime();
					reimbursement.setResolved(resolvedLocalDateTime);
					reimbursement.setResolver(resultSet.getInt("reimb_resolver"));
				}
				ticketsList.add(reimbursement);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return ticketsList;
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
			else {
				log.warn("Error: Ticket does not exist.");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return status;
	}

}
