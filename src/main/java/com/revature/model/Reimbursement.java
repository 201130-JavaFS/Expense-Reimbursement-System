package com.revature.model;

import java.sql.Blob;
import java.time.LocalDateTime;

public class Reimbursement {
	
	private int id;
	private float amount;
	private LocalDateTime submitted;
	private LocalDateTime resolved;
	private String description;
	private Blob receipt;
	private int author;
	private int resolver;
	private Status status;
	private Type type;

}
