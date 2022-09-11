package com.josephrexrode.quotivationproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.josephrexrode.quotivationproject.models.LoginUser;
import com.josephrexrode.quotivationproject.models.Quote;
import com.josephrexrode.quotivationproject.models.User;
import com.josephrexrode.quotivationproject.services.QuoteService;
import com.josephrexrode.quotivationproject.services.RestService;
import com.josephrexrode.quotivationproject.services.UserService;

@Controller
public class UserController {

	@Autowired
	UserService uServ;
	
	@Autowired
	RestService rServ;
	
	@Autowired
	QuoteService qServ;
	
	//// LOGIN PAGE ////
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		
		return "/users/index.jsp";
	}
	
	//// REGISTER ////
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result,
			Model model,
			HttpSession session) {
		
		if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "/users/index.jsp";
		}
		
		User user = uServ.register(newUser, result);
		
		if (user == null) {
			model.addAttribute("newLogin", new LoginUser());
			return "/users/index.jsp";
		}
		
		session.setAttribute("loggedUser", user);
		
		return "redirect:/home";
	}
	
	//// LOGIN ////
	
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("newLogin") LoginUser newLogin,
			BindingResult result,
			Model model,
			HttpSession session) {
		
		User user = uServ.login(newLogin, result);
		
		if (result.hasErrors() || user == null) {
			model.addAttribute("newUser", new User());
			return "/users/index.jsp";
		}
		
		session.setAttribute("loggedUser", user);
		
		return "redirect:/home";
	}
	
	//// HOME PAGE ////
	
	@GetMapping("/home")
	public String home(
			Model model,
			HttpSession session) {
		
		// for every get request--> if loggedUser object isn't in session,
		// redirects back to login page
		if (session.getAttribute("loggedUser") == null) {
			return "redirect:/";
		}
		
		
		User u = (User) session.getAttribute("loggedUser");
		
		// if a random quote exists in the system already
		if (session.getAttribute("randomQuote") != null) {
			
			Quote randomQuote = (Quote) session.getAttribute("randomQuote");
			model.addAttribute("randomQuote", randomQuote);				
			// add an attribute to determine whether the add button displays
			if (qServ.quoteInUserCollection(u, randomQuote.getId())) {
				model.addAttribute("addable", "no");
			}
			else {
				model.addAttribute("addable", "yes");
			}
		}
		// if it doesn't exist, calls random function to make it exist
		else {
			return "redirect:/inspiration/random";
		}
		
		if (!qServ.checkDaily()) {
			return "redirect:/callDaily";
		}
		
		Quote dailyQuote = qServ.dailyQuote();
		
		if (qServ.quoteInUserCollection(u, dailyQuote.getId())) {
			model.addAttribute("dailyAddable", "no");
		}
		else {
			model.addAttribute("dailyAddable", "yes");
		}
		
		model.addAttribute("dailyQuote", dailyQuote);
		model.addAttribute("loggedUser", session.getAttribute("loggedUser"));
		
		return "/users/home.jsp";
	}
	
	//// CALLS API ////
	
	@GetMapping("/callapi")
	public String callApi() throws JsonMappingException, JsonProcessingException {
		// returns 50 random quotes from the API's database
		// and converts them to my Quote class
		rServ.retrieveQuotes(rServ.getQuotesPlainJSON("https://zenquotes.io/api/quotes/"));
		
		return "redirect:/home";
	}
	
	@GetMapping("/callDaily")
	public String callDaily() throws JsonMappingException, JsonProcessingException {
		rServ.retrieveDailyQuote(rServ.getQuotesPlainJSON("https://zenquotes.io/api/today"));
		
		return "redirect:/home";
	}
	
	//// OTHERS SECTION ////
	
	@GetMapping("/others")
	public String others(
			Model model,
			HttpSession session) {
		
		if (session.getAttribute("loggedUser") == null) {
			return "redirect:/";
		}
		
		User u = (User) session.getAttribute("loggedUser");
		
		List<User> otherUsers = uServ.allOtherUsers(u.getId());
		
		model.addAttribute("otherUsers", otherUsers);
		
		return "/users/others.jsp";
	}
	
	//// LOGOUT ////
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
}
