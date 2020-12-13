package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.controllers.LoginController;
import com.revature.exception.BusinessException;

public class MasterServlet extends HttpServlet {
	
	private static Logger log = Logger.getLogger(MasterServlet.class);
	private LoginController loginController = new LoginController();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/project1/", "");
		
		System.out.println("doGet");
		switch (URI) {
//		case "avengers":
//			if(req.getSession(false) != null) ac.getAllAvengers(res);
//			else res.setStatus(403);
//			break;
		case "login":
			System.out.println("case login");
			try {
				loginController.login(req, res);
			} catch (IOException | BusinessException e) {
				log.error(e);
			}
			break;
		case "test":
			System.out.println("test case");
			break;
		}
		
		System.out.println("end of doGet");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}

}
