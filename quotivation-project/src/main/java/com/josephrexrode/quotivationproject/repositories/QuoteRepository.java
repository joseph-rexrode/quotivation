package com.josephrexrode.quotivationproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.josephrexrode.quotivationproject.models.Quote;
import com.josephrexrode.quotivationproject.models.User;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long>{

	List<Quote> findAll();
	
	// finds all quotes collected by a specified user
	List<Quote> findAllByUsers(User user);
	
	// returns null if user doesn't have quote
	Quote findByUsersAndId(User user, Long id);
	
	Quote findByDailyQuoteIs(boolean b);
}
