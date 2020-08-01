package com.portifolio.udemy.financas.domain.service;

import com.portifolio.udemy.financas.domain.model.User;

public interface UserService {

	User authenticate(String email, String password);
	
	User saveUser(User user);
	
	void emailValidate(String email);
	
}
