package com.portifolio.udemy.financas.api.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portifolio.udemy.financas.api.dto.UserDTO;
import com.portifolio.udemy.financas.domain.exception.AuthenticationException;
import com.portifolio.udemy.financas.domain.exception.BusinessException;
import com.portifolio.udemy.financas.domain.model.User;
import com.portifolio.udemy.financas.domain.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserEndpoint {
	
	
	private UserService userService;
	
	public UserEndpoint(UserService userService) {
		this.userService = userService;
	}
	@PostMapping("/authenticate")
	public ResponseEntity authenticate(@RequestBody UserDTO userDTO) {
		try {
			User authenticated = userService.authenticate(userDTO.getEmail(), userDTO.getPassword());			
			return ResponseEntity.ok(authenticated);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity save(@RequestBody UserDTO userDTO) {
		User user = userDTO.convert();
		
		try {
			User saveUser = userService.saveUser(user);
			return new ResponseEntity(saveUser, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
