package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.BusinessException;
import com.revature.model.ReimbRequestDTO;
import com.revature.service.ERSManipService;
import com.revature.service.impl.ERSManipServiceImpl;

public class TicketRequestController {
	
	private static Logger log = Logger.getLogger(TicketRequestController.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	ERSManipService ersManipService = new ERSManipServiceImpl();
	
	public void createReimbursementRequest(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, BusinessException {
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		ReimbRequestDTO reimbRequestDTO = objectMapper.readValue(body, ReimbRequestDTO.class);
		HttpSession httpSession = req.getSession(false);
		String username = null;
		if (httpSession != null) {
			username = (String) httpSession.getAttribute("username");
		}
		if (ersManipService.createReimbursementRequest(
				username,
				reimbRequestDTO.amount,
				reimbRequestDTO.type,
				reimbRequestDTO.description
				)) {
			log.info(username + " submitted a reimbursement ticket request.");
			res.setStatus(200);
			res.getWriter().print("Ticket Submitted.");
		} else {
			if (httpSession != null) {
				httpSession.invalidate();
			}
			res.setStatus(401);
			res.getWriter().print("Ticket Submission failed.");
		}
	}

}
