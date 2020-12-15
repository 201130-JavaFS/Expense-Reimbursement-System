package com.revature.model;

public class ReimbRequestDTO {
	
	public float amount;
	public String type;
	public String description;
	
	public ReimbRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReimbRequestDTO(float amount, String type, String description) {
		super();
		this.amount = amount;
		this.type = type;
		this.description = description;
	}

}
