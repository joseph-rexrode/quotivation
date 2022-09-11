package com.josephrexrode.quotivationproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.josephrexrode.quotivationproject.models.Quote;
import com.josephrexrode.quotivationproject.models.User;
import com.josephrexrode.quotivationproject.services.QuoteService;
import com.josephrexrode.quotivationproject.services.RestService;
import com.josephrexrode.quotivationproject.services.UserService;

@Controller
@RequestMapping("/inspiration")
public class QuoteController {
	
	@Autowired
	QuoteService qServ;
	
	@Autowired
	RestService rServ;
	
	@Autowired
	UserService uServ;

	//// MAIN INSPIRATION PAGE ////
	
	@GetMapping("")
	public String myInspiration(
			HttpSession session,
			Model model) {
		
		if (session.getAttribute("loggedUser") == null) {
			return "redirect:/";
		}
		
		User u = (User) session.getAttribute("loggedUser");
		
		// finds all quotes in user collection
		List<Quote> quotes = qServ.findByUser(u);
				
		model.addAttribute("quotes", quotes);
		model.addAttribute("user", u);
		
		return "/quotes/inspiration.jsp";
	}
	
	//// CREATE A NEW QUOTE ////
	
	@GetMapping("/new")
	public String newQuote(
			HttpSession session,
			@ModelAttribute("quote") Quote quote) {
		
		if (session.getAttribute("loggedUser") == null) {
			return "redirect:/";
		}
		
		return "/quotes/new.jsp";
	}
	
	@PostMapping("/new")
	public String createQuote(
			@Valid @ModelAttribute("quote") Quote quote,
			BindingResult result,
			HttpSession session,
			Model model) {
		
		if (result.hasErrors()) {
			return "/quotes/new.jsp";
		}
		
		User user = (User) session.getAttribute("loggedUser");
		
		// specifies to service that user should be added to "creator" and "users"
		qServ.create(quote, user);
		
		return "redirect:/inspiration";
	}
	
	//// EDIT A QUOTE ////
	
	@GetMapping("/edit/{quote_id}")
	public String edit(
			@PathVariable("quote_id") Long id,
			Model model,
			HttpSession session) {
		
		if (session.getAttribute("loggedUser") == null) {
			return "redirect:/";
		}
		
		Quote q = qServ.findById(id);
		User u = (User) session.getAttribute("loggedUser");
		
		// prevents manual GET requests from people who didn't create the quote
		if (q.getCreator().getId() != u.getId()) {
			return "redirect:/inspiration";
		}
		
		model.addAttribute("quoteId", id);
		model.addAttribute("quote", q);
		
		return "/quotes/edit.jsp";
	}
	
	@PutMapping("/edit/{quote_id}")
	public String update(
			@Valid @ModelAttribute("quote") Quote quote,
			BindingResult result,
			Model model,
			@PathVariable("quote_id") Long id) {
		
		if (result.hasErrors()) {
			model.addAttribute("quoteId", id);
			return "/quotes/edit.jsp";
		}
		
		Quote q = qServ.findById(id);
		
		qServ.update(q, quote);
		
		return "redirect:/inspiration";
	}
	
	//// GENERATES A NEW RANDOM QUOTE FROM MODEL ////
	
	@GetMapping("/random")
	public String randomQuote(
			HttpSession session) {
		
		if (session.getAttribute("loggedUser") == null) {
			return "redirect:/";
		}
		
		
		if (qServ.randomQuote() == null) {
			return "redirect:/callapi";
		}
		
		else {			
			Quote randQ = qServ.randomQuote();
			
			session.setAttribute("randomQuote", randQ);
			return "redirect:/home";
		}
		
	}
	
	//// ADDS RANDOM QUOTE TO USER COLLECTION ////
	
	@PutMapping("/addRandom")
	public String addRandomQuote(HttpSession session) {
		
		Quote quote = (Quote) session.getAttribute("randomQuote");
		User u = (User) session.getAttribute("loggedUser");
		
		qServ.addToCollection(quote, u);
		
		return "redirect:/home";
	}
	
	@PutMapping("/remove/{quoteId}")
	public String removeQuote(
			@PathVariable("quoteId") Long id,
			HttpSession session) {
		
		Quote quote = qServ.findById(id);
		User u = (User) session.getAttribute("loggedUser");
		
		qServ.removeFromCollection(quote, u);
		
		return "redirect:/inspiration";
	}
	
	//// GO TO SPECIFIC OTHER PERSON'S INSPIRATION PAGE ////
	
	@GetMapping("/others/{user_id}")
	public String otherInspiration(
			@PathVariable("user_id") Long id,
			Model model,
			HttpSession session) {
		
		if (session.getAttribute("loggedUser") == null) {
			return "redirect:/";
		}
		
		User logged = (User) session.getAttribute("loggedUser");
		User u = uServ.findById(id);
		List<Quote> quotes = qServ.findByUser(u);
		model.addAttribute("quotes", quotes);
		model.addAttribute("loggedUser", logged);
		model.addAttribute("otherUser", u);
		
		
		return "/quotes/show.jsp";
	}
	
	//// ADDS SPECIFIC QUOTE TO INSPIRATION ////
	
	@PutMapping("/add")
	public String add(
			@RequestParam("id") Long id,
			HttpSession session) {
		
		Quote quote = qServ.findById(id);
		User u = (User) session.getAttribute("loggedUser");
		
		qServ.addToCollection(quote, u);
		
		return "redirect:/inspiration";
	}
	
	//// DELETES QUOTE IF USER IS CREATOR ////
	
	@DeleteMapping("/delete/{quote_id}")
	public String destroy(
			@PathVariable("quote_id") Long id) {
				
		qServ.destroy(id);
		
		return "redirect:/inspiration";
	}
}
