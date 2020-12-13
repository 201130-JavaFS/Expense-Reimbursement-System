package com.revature.service.util;

import com.revature.model.Role;
import com.revature.model.Status;
import com.revature.model.Type;

public class DBConversions {
	
	public static int statusToDatabase(Status status) {
		if (status == Status.PENDING) return 0;
		else if (status == Status.APPROVED) return 1;
		else return 2;
	}
	
	public static int typeToDatabase(Type type) {
		if (type == Type.LODGING) return 1;
		else if (type == Type.TRAVEL) return 2;
		else if (type == Type.FOOD) return 3;
		else return 4;
	}
	
	public static Status databaseToStatus(int reimb_status_id) {
		if (reimb_status_id == 0) return Status.PENDING;
		else if (reimb_status_id == 1) return Status.APPROVED;
		else return Status.DENIED;
	}
	
	public static Type databaseToType(int reimb_type_id) {
		if (reimb_type_id == 1) return Type.LODGING;
		else if (reimb_type_id == 2) return Type.TRAVEL;
		else if (reimb_type_id == 3) return Type.FOOD;
		else return Type.OTHER;
	}
	
	public static int roleToDatabase(Role role) {
		if (role == Role.EMPLOYEE) return 1;
		else return 2;
	}
	
	public static Role databaseToRole(int user_role_id) {
		if (user_role_id == 1) return Role.EMPLOYEE;
		else return Role.FINANCEMANAGER;
	}

}
