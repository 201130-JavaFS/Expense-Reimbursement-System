package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.BusinessException;
import com.revature.model.LoginDTO;
import com.revature.model.User;
import com.revature.service.ERSSearchService;
import com.revature.service.impl.ERSSearchServiceImpl;

public class LoginController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private ERSSearchService ersSearchService = new ERSSearchServiceImpl();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException, BusinessException {
		if(req.getMethod().equals("POST")) {
			BufferedReader reader = req.getReader();
			StringBuilder stringBuilder = new StringBuilder();
			String line = reader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				line = reader.readLine();
			}
			String body = new String(stringBuilder);
			LoginDTO loginDTO = objectMapper.readValue(body, LoginDTO.class);
			if (ersSearchService.verifyLogin(
					loginDTO.username, loginDTO.password)) {
				HttpSession httpSession = req.getSession();
				httpSession.setAttribute("user", loginDTO);
				httpSession.setAttribute("loggedIn", true);
				res.setStatus(200);
				res.getWriter().print("Login Successful.");
			} else {
				HttpSession httpSession = req.getSession(false);
				if (httpSession != null) {
					httpSession.invalidate();
				}
				res.setStatus(401);
				res.getWriter().print("Login failed.");
			}
		}
		
	}
	
}
