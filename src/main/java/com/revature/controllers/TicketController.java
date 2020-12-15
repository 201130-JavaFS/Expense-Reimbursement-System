package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.service.ERSSearchService;
import com.revature.service.impl.ERSSearchServiceImpl;

public class TicketController {
	
	private static Logger log = Logger.getLogger(TicketController.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	private ERSSearchService ersSearchService = new ERSSearchServiceImpl();
	
	public void getAllEmployeesTickets(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, BusinessException{
		String username = null;
		HttpSession httpSession = req.getSession(false);
		if (httpSession != null) {
			username = (String) httpSession.getAttribute("username");
		}
		
		int authorId = ersSearchService.getIdByUsername(username);
		List<Reimbursement> reimbList = ersSearchService.getAllEmployeesTickets(authorId);
		String json = objectMapper.writeValueAsString(reimbList);
		res.getWriter().print(json);
		res.setStatus(200);
		
		log.info(username + " has viewed all tickets.");
	}

}
