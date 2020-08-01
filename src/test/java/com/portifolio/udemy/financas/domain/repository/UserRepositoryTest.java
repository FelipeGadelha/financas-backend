package com.portifolio.udemy.financas.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.portifolio.udemy.financas.domain.model.User;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void shouldVerifyIfExistsAEmail() {
		User user = createdUser();
		entityManager.persist(user);
		boolean result = userRepository.existsByEmail("user@email.com");
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldReturnFalseWhenThereIsNoUserRegisteredWithTheEmail() {
		boolean result = userRepository.existsByEmail("user@email.com");
		assertThat(result).isFalse();
		
	}
	
	@Test
	public void shouldPersistAUserInTheDatabase() {
		var user = createdUser();
		User userSaved = userRepository.save(user);
		assertThat(userSaved.getId()).isNotNull();
	}
	
	@Test
	public void shouldFindUserByEmail() {
		var user = createdUser();
		entityManager.persist(user);
		
		Optional<User> result = userRepository.findByEmail("user@email.com");
		assertThat(result.isPresent()).isTrue();
	}
	
	public static User createdUser() {
		return new User.UserBuilder()
				.name("user")
				.email("user@email.com")
				.password("password")
				.build();
	}
	
	public void shouldReturnEmptyWhenSearchingForUserByEmailWhenItDoesNotExistInTheDatabase() {
		Optional<User> result = userRepository.findByEmail("user@email.com");
		assertThat(result.isPresent()).isFalse();
	}

}
