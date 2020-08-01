package com.portifolio.udemy.financas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.udemy.financas.domain.model.launch;

@Repository
public interface LaunchRepository extends JpaRepository<launch, Long>{

}
