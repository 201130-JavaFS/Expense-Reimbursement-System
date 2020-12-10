package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import com.revature.dao.ERSManipDAO;
import com.revature.dao.util.ERSDbQueries;
import com.revature.dao.util.ERSDbUtilProps;
import com.revature.dao.util.ERSPostgresSqlConnection;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.service.util.DBConversions;

public class ERSManipDAOImpl implements ERSManipDAO {
	
	private static Logger log = Logger.getLogger(ERSManipDAOImpl.class);

	@Override
	public boolean createNewReimbursementRequest(Reimbursement reimbursement) throws BusinessException {
		boolean isCreated = false;
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.CREATE_NEW_REIMBURSEMENT_REQUEST;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reimbursement.getId());
			preparedStatement.setFloat(2, reimbursement.getAmount());
			preparedStatement.setTimestamp(3, Timestamp.valueOf(reimbursement.getSubmitted()));
			preparedStatement.setString(4, reimbursement.getDescription());
			preparedStatement.setInt(5, reimbursement.getAuthor());
			preparedStatement.setInt(6, DBConversions.statusToDatabase(reimbursement.getStatus()));
			preparedStatement.setInt(7, DBConversions.typeToDatabase(reimbursement.getType()));
			
			preparedStatement.executeUpdate();
			isCreated = true;
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return isCreated;
	}

	@Override
	public boolean resolveTicketStatus(Status status, int reimb_id, int reimb_resolver) throws BusinessException {
		boolean isResolved = false;
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.RESOLVE_TICKET_STATUS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			preparedStatement.setFloat(2, reimb_resolver);
			preparedStatement.setInt(3, DBConversions.statusToDatabase(status));
			
			preparedStatement.setInt(4, reimb_id);
			preparedStatement.executeUpdate();
			isResolved = true;
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
		return isResolved;
	}

}
