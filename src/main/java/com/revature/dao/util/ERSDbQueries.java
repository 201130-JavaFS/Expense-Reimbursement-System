package com.revature.dao.util;

public class ERSDbQueries {
	
	public static final String VERIFY_LOGIN = 
			"select ers_password, user_role_id "
			+ "from ers.users "
			+ "where ers_username=?";
	
	public static final String GET_MAX_ID = 
			"select max(reimb_id) as maxId "
			+ "from ers.reimbursement";
	
	public static final String CREATE_NEW_REIMBURSEMENT_REQUEST = 
			"insert into ers.reimbursement(reimb_id, reimb_amount, reimb_submitted, reimb_description, "
			+ "reimb_author, reimb_status_id, reimb_type_id, reimb_receipt) "
			+ "Values(?,?,?,?,?,?,?,?)";
	
	public static final String GET_ALL_EMPLOYEES_TICKETS = 
			"select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description"
			+ ", reimb_author, reimb_resolver, reimb_status_id, reimb_type_id"
			+ " from ers.reimbursement where reimb_author=?";
	
	public static final String GET_ALL_TICKETS = 
			"select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description"
			+ ", reimb_author, reimb_resolver, reimb_status_id, reimb_type_id"
			+ " from ers.reimbursement";
	
	public static final String RESOLVE_TICKET_STATUS = 
			"update ers.reimbursement "
			+ "set reimb_resolved=?, reimb_resolver=?, reimb_status_id=? "
			+ "where reimb_id=?";
	
	public static final String CHECK_STATUS_OF_REIMBURSEMENT_BY_ID = 
			"select reimb_status_id "
			+ "from ers.reimbursement "
			+ "where reimb_id=?";
	
	public static final String GET_PASSWORD_BY_ID = 
			"select ers_password "
			+ "from ers.users "
			+ "where ers_users_id=?";
	
	public static final String ENCRYPT_PASSWORD_BY_ID = 
			"update ers.users "
			+ "set ers_password=? "
			+ "where ers_users_id=?";
	
	public static final String GET_ID_BY_USERNAME = 
			"select ers_users_id "
			+ "from ers.users "
			+ "where ers_username=?";
	
	public static final String GET_USER_BY_ID = 
			"select ers_username, user_first_name, user_last_name, user_email, user_role_id "
			+ "from ers.users "
			+ "where ers_users_id=?";
	
	public static final String GET_ALL_TICKETS_BY_STATUS = 
			"select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, "
			+ "reimb_author, reimb_resolver, reimb_type_id "
			+ "from ers.reimbursement "
			+ "where reimb_status_id=?";
	
	public static final String GET_ALL_TICKETS_BY_TYPE = 
			"select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, "
			+ "reimb_author, reimb_resolver, reimb_status_id"
			+ "from ers.reimbursement "
			+ "where reimb_type_id=?";
	
	public static final String GET_ALL_TICKETS_BY_STATUS_TYPE = 
			"select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, "
			+ "reimb_author, reimb_resolver"
			+ "from ers.reimbursement "
			+ "where reimb_status_id=?, reimb_type_id=?";
	
	public static final String GET_PASSWORD_BY_USERNAME = 
			"select ers_password "
			+ "from ers.users "
			+ "where ers_username=?";
	
	public static final String GET_USER_BY_USERNAME = 
			"select ers_users_id, user_first_name, user_last_name, user_email, user_role_id "
			+ "from ers.users "
			+ "where ers_username=?";
	
	public static final String GET_ROLE_BY_USERNAME = 
			"select user_role_id "
			+ "from ers.users "
			+ "where ers_username=?";

}
