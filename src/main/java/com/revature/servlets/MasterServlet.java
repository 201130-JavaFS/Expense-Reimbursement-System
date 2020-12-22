package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.controllers.LoginController;
import com.revature.controllers.TicketController;
import com.revature.controllers.TicketRequestController;
import com.revature.controllers.TicketStatusController;
import com.revature.exception.BusinessException;

@SuppressWarnings("serial")
public class MasterServlet extends HttpServlet {
	
	private static Logger log = Logger.getLogger(MasterServlet.class);
	private LoginController loginController = new LoginController();
	private TicketController ticketController = new TicketController();
	private TicketRequestController ticketRequestController = new TicketRequestController();
	private TicketStatusController ticketStatusController = new TicketStatusController();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);
		res.getWriter().write("<h1>testing</h1>");

		final String URI = req.getRequestURI().replace("/project1/", "");
		
		switch (URI) {
		case "login":
			try {
				loginController.login(req, res);
			} catch (IOException | BusinessException e) {
				log.error(e);
			}
			break;
		case "employeetickets":
			if(req.getSession(false) != null) {
				try {
					ticketController.getAllEmployeesTickets(req, res);
				} catch (IOException | BusinessException e) {
					log.error(e);
				}
			}
			else res.setStatus(403);
			break;
		case "reimbrequest":
			if(req.getSession(false) != null) {
				try {
					ticketRequestController.createReimbursementRequest(req, res);
				} catch (IOException | BusinessException e) {
					log.error(e);
				}
			}
			else res.setStatus(403);
			break;
		case "alltickets":
			if(req.getSession(false) != null) {
				try {
					ticketController.getAllTickets(req, res);
				} catch (IOException | BusinessException e) {
					log.error(e);
				}
			}
			else res.setStatus(403);
			break;
		case "filteredtickets":
			if(req.getSession(false) != null) {
				try {
					ticketStatusController.getAllFilteredTickets(req, res);
				} catch (IOException | BusinessException e) {
					log.error(e);
				}
			}
			else res.setStatus(403);
			break;
		case "approveticket":
			if(req.getSession(false) != null) {
				try {
					ticketStatusController.approveTicket(req, res);
				} catch (IOException | BusinessException e) {
					log.error(e);
				}
			}
			else res.setStatus(403);
			break;
		case "denyticket":
			if(req.getSession(false) != null) {
				try {
					ticketStatusController.denyTicket(req, res);
				} catch (IOException | BusinessException e) {
					log.error(e);
				}
			}
			else res.setStatus(403);
			break;
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		res.getWriter().print("testing doPost");
		doGet(req, res);
	}

}
