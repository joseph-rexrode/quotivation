package com.josephrexrode.quotivationproject.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.josephrexrode.quotivationproject.models.LoginUser;
import com.josephrexrode.quotivationproject.models.User;
import com.josephrexrode.quotivationproject.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository uRepo;
	
	public User register(User newUser, BindingResult result) {
		Optional<User> potentialUser = uRepo.findByEmail(newUser.getEmail());
		
		if (potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email is already in use!");
			return null;
		}
		
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password", "Matches", "Password must match confirm password!");
			return null;
		}
		
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		
		newUser.setPassword(hashed);
		
		return uRepo.save(newUser);
	}
	
	public User login(LoginUser newLogin, BindingResult result) {
		Optional<User> potentialUser = uRepo.findByEmail(newLogin.getEmail());
		
		if (potentialUser.isEmpty()) {
			result.rejectValue("email", "Matches", "Email is not recognized!");
			return null;
		}
		
		User user = potentialUser.get();
		
		if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid password!");
			return null;
		}
		
		return user;
	}
	
	//// CRUD METHODS ////
	
	public User findById(Long id) {
		return uRepo.findById(id).orElse(null);
	}
}
