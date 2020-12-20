package com.revature.model;

import java.sql.Blob;

public class ReimbRequestDTO {
	
	public float amount;
	public String type;
	public String description;
	public Blob receipt;
	
	public ReimbRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReimbRequestDTO(float amount, String type, String description, Blob receipt) {
		super();
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.receipt = receipt;
	}

}
