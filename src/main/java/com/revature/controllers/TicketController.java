package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.UsernameDTO;
import com.revature.service.ERSSearchService;
import com.revature.service.impl.ERSSearchServiceImpl;

public class TicketController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private ERSSearchService ersSearchService = new ERSSearchServiceImpl();
	
	public void getAllEmployeesTickets(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, BusinessException{
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		UsernameDTO usernameDTO = objectMapper.readValue(body, UsernameDTO.class);
		
		System.out.println("UsernameDTO.username: " +usernameDTO.username);
		int authorId = ersSearchService.getIdByUsername(usernameDTO.username);
		System.out.println("AuthorId: " + authorId);
		List<Reimbursement> reimbList = ersSearchService.getAllEmployeesTickets(authorId);
		String json = objectMapper.writeValueAsString(reimbList);
		res.getWriter().print(json);
		res.setStatus(200);
	}

}
