package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.BusinessException;
import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.model.StatusDTO;
import com.revature.model.TicketDTO;
import com.revature.service.ERSManipService;
import com.revature.service.ERSSearchService;
import com.revature.service.impl.ERSManipServiceImpl;
import com.revature.service.impl.ERSSearchServiceImpl;
import com.revature.service.util.DBConversions;

public class TicketStatusController {
	
	private static Logger log = Logger.getLogger(TicketStatusController.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	private ERSSearchService ersSearchService = new ERSSearchServiceImpl();
	private ERSManipService ersManipService = new ERSManipServiceImpl();
	
	public void approveTicket(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, BusinessException {
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		TicketDTO ticketDTO = objectMapper.readValue(body, TicketDTO.class);
		HttpSession httpSession = req.getSession(false);
		String username = null;
		if (httpSession != null) {
			username = (String) httpSession.getAttribute("username");
		}
		int userId = ersSearchService.getIdByUsername(username);
		if (ersManipService.resolveTicketStatus(Status.APPROVED, ticketDTO.id, userId)) {
			res.setStatus(200);
			log.info(username + " has approved ticket id: " + ticketDTO.id);
		}
	}
	
	public void denyTicket(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, BusinessException {
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		TicketDTO ticketDTO = objectMapper.readValue(body, TicketDTO.class);
		HttpSession httpSession = req.getSession(false);
		String username = null;
		if (httpSession != null) {
			username = (String) httpSession.getAttribute("username");
		}
		int userId = ersSearchService.getIdByUsername(username);
		if (ersManipService.resolveTicketStatus(Status.DENIED, ticketDTO.id, userId)) {
			res.setStatus(200);
			log.info(username + " has denied ticket id: " + ticketDTO.id);
		}
	}
	
	public void getAllFilteredTickets(HttpServletRequest req, HttpServletResponse res) 
			throws IOException, BusinessException {
		BufferedReader reader = req.getReader();
		StringBuilder stringBuilder = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stringBuilder);
		StatusDTO statusDTO = objectMapper.readValue(body, StatusDTO.class);
		HttpSession httpSession = req.getSession(false);
		String username = null;
		if (httpSession != null) {
			username = (String) httpSession.getAttribute("username");
		}
		Status status = DBConversions.stringToStatus(statusDTO.status);
		List<Reimbursement> reimbList = ersSearchService.getAllTicketsByStatus(status);
		if (reimbList != null) {
			String json = objectMapper.writeValueAsString(reimbList);
			res.getWriter().print(json);
			res.setStatus(200);
			
			log.info(username + " has viewed all tickets by " + statusDTO.status + ".");
		}
		
	}

}
