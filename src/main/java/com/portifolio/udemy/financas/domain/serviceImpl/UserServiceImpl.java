package com.portifolio.udemy.financas.domain.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.udemy.financas.domain.exception.AuthenticationException;
import com.portifolio.udemy.financas.domain.exception.BusinessException;
import com.portifolio.udemy.financas.domain.model.User;
import com.portifolio.udemy.financas.domain.repository.UserRepository;
import com.portifolio.udemy.financas.domain.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository; 
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User authenticate(String email, String password) {
		Optional<User> user = userRepository.findByEmail(email);
		if (!user.isPresent()) throw new AuthenticationException("User not found for the given email");
		if (!user.get().getPassword().equals(password)) throw new AuthenticationException("invalid password");
		return user.get();
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		emailValidate(user.getEmail());
		return userRepository.save(user);
		
	}

	@Override
	public void emailValidate(String email) {
		boolean exists = userRepository.existsByEmail(email);
		if (exists) throw new BusinessException("There is already a registered user with this email");
		
	}

}
