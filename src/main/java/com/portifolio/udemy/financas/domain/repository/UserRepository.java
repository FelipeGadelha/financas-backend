package com.portifolio.udemy.financas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.udemy.financas.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

//	Optional<User> findByEmailAndName(String email, String name);
	boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
	
	
}
