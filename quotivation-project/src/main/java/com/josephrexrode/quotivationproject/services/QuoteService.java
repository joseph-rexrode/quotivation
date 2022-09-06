package com.josephrexrode.quotivationproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josephrexrode.quotivationproject.models.Quote;
import com.josephrexrode.quotivationproject.models.User;
import com.josephrexrode.quotivationproject.repositories.QuoteRepository;
import com.josephrexrode.quotivationproject.repositories.UserRepository;

@Service
public class QuoteService {

	@Autowired
	QuoteRepository qRepo;
	
	@Autowired
	UserRepository uRepo;
	
	//// CRUD METHODS ////
	
	public Quote create(String text, String author) {
		Quote quote = new Quote(text, author);
		return qRepo.save(quote);
	}
	
	public Quote create(Quote q, User u) {
		q.setUsers(new ArrayList<User>());
		q.getUsers().add(u);
		q.setCreator(u);
		return qRepo.save(q);
	}
	
	public List<Quote> findByUser(User u) {
		return qRepo.findAllByUsers(u);
	}
	
	public List<Quote> findAll() {
		return qRepo.findAll();
	}
	
	public Quote findById(Long id) {
		return qRepo.findById(id).orElse(null);
	}
	
	
	// different update functions
	
	public Quote update(Quote q, Quote qInfo) {
		
		q.setAuthor(qInfo.getAuthor());
		q.setText(qInfo.getText());
		return qRepo.save(q);
	}
	
	public Quote addToCollection(Quote q, User u) {
		List<User> usersWithQuote = uRepo.findAllByQuotes(q);
		usersWithQuote.add(u);
		
		q.setUsers(usersWithQuote);
		
		return qRepo.save(q);
	}
	
	public String destroy(Long id) {
		qRepo.deleteById(id);
		return "This quote has been deleted";
	}
	
	//// OTHER METHODS ////
	
	// returns a random quote
	public Quote randomQuote() {
		List<Quote> allQuotes = findAll();
		
		int randIndex = (int) Math.round(Math.random()*allQuotes.size());
		
		return allQuotes.get(randIndex);
	}
	
	// returns true if the quote is in the user's collection
	public boolean quoteInUserCollection(User u, Long id) {
		if (qRepo.findByUsersAndId(u, id) != null) {
			return true;
		}
		return false;
	}
	
	// daily quote method 
	
	public Quote dailyQuote() {
		return qRepo.findByDailyQuoteIs(true);
	}
}
