package com.revature.dao.impl;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
import com.revature.service.ERSSearchService;
import com.revature.service.impl.ERSSearchServiceImpl;
import com.revature.service.util.DBConversions;
import com.revature.service.util.Encoder;

public class ERSManipDAOImpl implements ERSManipDAO{
	
	private static Logger log = Logger.getLogger(ERSManipDAOImpl.class);
	private ERSSearchService ersSearchService = new ERSSearchServiceImpl();

	@Override
	public boolean createNewReimbursementRequest(Reimbursement reimbursement) throws BusinessException {
		boolean isCreated = false;
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.CREATE_NEW_REIMBURSEMENT_REQUEST;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reimbursement.getId());
			preparedStatement.setFloat(2, reimbursement.getAmount());
			preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			preparedStatement.setString(4, reimbursement.getDescription());
			preparedStatement.setInt(5, reimbursement.getAuthor().getId());
			preparedStatement.setInt(6, DBConversions.statusToDatabase(reimbursement.getStatus()));
			preparedStatement.setInt(7, DBConversions.typeToDatabase(reimbursement.getType()));
			
			int check = preparedStatement.executeUpdate();
			if (check > 0) isCreated = true;
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
			preparedStatement.setInt(2, reimb_resolver);
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

	@Override
	public void encryptPasswordById(int ers_users_id) throws BusinessException {
		Encoder encoder = null;
			try {
				encoder = new Encoder();
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				log.error(e);
			}
		String password = ersSearchService.getPasswordById(ers_users_id);
		String encryptedPassword = null;
		try {
			encryptedPassword = Encoder.encrypt(password, encoder.getKey());
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			log.error(e);
		}
		try (Connection connection = ERSPostgresSqlConnection.getConnection()) {
			String sql = ERSDbQueries.ENCRYPT_PASSWORD_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, encryptedPassword);
			preparedStatement.setInt(2, ers_users_id);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException(ERSDbUtilProps.ERROR_MESSAGE);
		}
	}

}
