package com.josephrexrode.quotivationproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.josephrexrode.quotivationproject.models.Quote;
import com.josephrexrode.quotivationproject.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findByEmail(String email);
	
	// finds all users that have a specified quote in their collection
	List<User> findAllByQuotes(Quote q);
	
	// finds all users that arent't the one specified
	List<User> findAllByIdNot(Long id);
}
