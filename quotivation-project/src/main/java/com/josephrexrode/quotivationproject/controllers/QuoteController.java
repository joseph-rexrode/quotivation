package com.josephrexrode.quotivationproject.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuoteController {

	
	@GetMapping("/inspiration")
	public String myInspiration(
			HttpSession session,
			Model model) {
		
		if (session.getAttribute("loggedUser") == null) {
			return "redirect:/";
		}
		
		return "/quotes/inspiration.jsp";
	}
	
}
