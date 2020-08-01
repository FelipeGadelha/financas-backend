package com.portifolio.udemy.financas.api.dto;

import com.portifolio.udemy.financas.domain.model.User;

public class UserDTO {
	
	private String name;
	
	private String email;
	
	private String password;
	
	@Deprecated
	public UserDTO() {}
	
public static class UserDTOBuilder {
		
		private String name;
		
		private String email;
			
		private String password;

        public UserDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDTOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDTO build() {
            var userDTO = new UserDTO();
            userDTO.setName(name);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            return userDTO;
        }

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User convert() {
		return new User.UserBuilder().name(name).email(email).password(password).build();
	}


}
