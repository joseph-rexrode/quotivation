package com.josephrexrode.quotivationproject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.josephrexrode.quotivationproject.models.LoginUser;
import com.josephrexrode.quotivationproject.models.User;
import com.josephrexrode.quotivationproject.services.UserService;

@Controller
public class UserController {

	@Autowired
	UserService uServ;
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		
		return "/users/index.jsp";
	}
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result,
			Model model,
			HttpSession session) {
		
		User user = uServ.register(newUser, result);
		
		if (result.hasErrors() || user == null) {
			model.addAttribute("newLogin", new LoginUser());
			return "/users/index.jsp";
		}
		
		session.setAttribute("loggedUser", user);
		
		return "redirect:/home";
	}
	
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
	
	@GetMapping("/home")
	public String home(
			Model model,
			HttpSession session) {
		
		if (session.getAttribute("loggedUser") == null) {
			return "redirect:/";
		}
		
		model.addAttribute("loggedUser", session.getAttribute("loggedUser"));
		
		return "/users/home.jsp";
	}
	
	@GetMapping("/friends")
	public String friends(
			Model model,
			HttpSession session) {
		
		if (session.getAttribute("loggedUser") == null) {
			return "redirect:/";
		}
		
		return "/users/friends.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
}
