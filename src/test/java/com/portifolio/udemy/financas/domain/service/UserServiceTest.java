package com.portifolio.udemy.financas.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.portifolio.udemy.financas.domain.exception.AuthenticationException;
import com.portifolio.udemy.financas.domain.exception.BusinessException;
import com.portifolio.udemy.financas.domain.model.User;
import com.portifolio.udemy.financas.domain.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@SpyBean
	UserServiceImpl userService;
	@MockBean
	UserRepository userRepository;
	
//	@BeforeEach
//	public void setUp() {
////		userRepository = Mockito.mock(UserRepository.class);
//		userService = new UserServiceImpl(userRepository);
//	}
	
	@Test
	public void shouldAuthenticateAUserSuccessfully() {
		String email = "email@email.com";
		String password = "password";
		User user = new User.UserBuilder()
				.email(email)
				.password(password)
				.id(1l)
				.build();
		Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
		User result = userService.authenticate(email, password);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void shouldThrowAnErrorWhenItNotFindARegisteredUserWithTheInformedEmail() {
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		Throwable exception = catchThrowable(()-> userService.authenticate("email@email.com", "password"));
		assertThat(exception)
			.isInstanceOf(AuthenticationException.class)
			.hasMessage("User not found for the given email");
	}
	
	@Test
	public void shouldSaveAUser() {
		Mockito.doNothing().when(userService).emailValidate(Mockito.anyString());
		User user = new User.UserBuilder()
				.id(1l)
				.name("name")
				.email("email@email.com")
				.password("password")
				.build();
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		User saveUser = userService.saveUser(new User.UserBuilder().build());
		
		assertThat(saveUser).isNotNull();
		assertThat(saveUser.getId()).isEqualTo(1l);
		assertThat(saveUser.getName()).isEqualTo("name");
		assertThat(saveUser.getEmail()).isEqualTo("email@email.com");
		assertThat(saveUser.getPassword()).isEqualTo("password");
		
	}
	
	@Test
	public void shouldNotSaveAUserWithAnAlreadyRegisteredEmail() {
		String email = "email@email.com";
		User user = new User.UserBuilder()
				.email(email)
				.build();
		Mockito.doThrow(BusinessException.class).when(userService).emailValidate(email);
		
		assertThrows(BusinessException.class, ()-> userService.saveUser(user));
		
		Mockito.verify(userRepository, Mockito.never()).save(user);
	}
	
	@Test
	public void shouldGenerateAnErrorWhenThePasswordIsIncorrect() {
		String password = "password";
		User user = new User.UserBuilder()
				.email("email@email.com")
				.password(password)
				.build();
		Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
		Throwable exception = catchThrowable(()-> userService.authenticate("email@email.com", "123"));
		assertThat(exception).isInstanceOf(AuthenticationException.class).hasMessage("invalid password");
	}
	
	@Test
	public void shouldValidateEmail() {
		Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
		userService.emailValidate("email@email.com");
	}
	
	@Test
	public void shouldThrowErrorWhenThereIsRegisteredEmail() {
		Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
		assertThrows(BusinessException.class, ()-> userService.emailValidate("email@email.com"));
		
	}
	
}
