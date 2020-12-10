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
			+ "reimb_author, reimb_status_id, reimb_type_id) "
			+ "Values(?,?,?,?,?,?,?)";
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
	
}
