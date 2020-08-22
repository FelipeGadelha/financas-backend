package com.portifolio.udemy.financas.domain.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.udemy.financas.domain.enums.StatusLaunch;
import com.portifolio.udemy.financas.domain.exception.BusinessException;
import com.portifolio.udemy.financas.domain.model.Launch;
import com.portifolio.udemy.financas.domain.repository.LaunchRepository;
import com.portifolio.udemy.financas.domain.service.LaunchService;

@Service
public class LaunchServiceImpl implements LaunchService {

	private LaunchRepository launchRepository;

	public LaunchServiceImpl(LaunchRepository launchRepository) {
		this.launchRepository = launchRepository;
	}

	@Override
	@Transactional
	public Launch save(Launch launch) {
		validate(launch);
		launch.setStatus(StatusLaunch.PENDING);
		return launchRepository.save(launch);
	}

	@Override
	@Transactional
	public Launch update(Launch launch) {
		Objects.requireNonNull(launch.getId());
		validate(launch);
		return launchRepository.save(launch);
	}

	@Override
	@Transactional
	public void delete(Launch launch) {
		Objects.requireNonNull(launch.getId());
		launchRepository.delete(launch);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Launch> find(Launch launchFilter) {
		Example<Launch> example = Example.of( launchFilter, 
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING) );
		return launchRepository.findAll(example);
	}

	@Override
	public void updateStatus(Launch launch, StatusLaunch status) {
		launch.setStatus(status);
		update(launch);
	}
	
	@Override
	public void validate(Launch launch) {
		if (launch.getDescription() == null || launch.getDescription().trim().equals("")) {
			throw new BusinessException("Provide a valid Description.");
		}
		if (launch.getMonth() == null || launch.getMonth() < 1 || launch.getMonth() > 12) {
			throw new BusinessException("Provide a valid Month");
		}
		if (launch.getYear() == null || launch.getYear().toString().length() != 4) {
			throw new BusinessException("Provide a valid Year");
		}
		if (launch.getUser() == null || launch.getUser().getId() == null) {
			throw new BusinessException("Provide a valid User");
		}
		if (launch.getValue() == null || launch.getValue().compareTo(BigDecimal.ZERO) < 1) {
			throw new BusinessException("Provide a valid Value");
		}
		if (launch.getType() == null) {
			throw new BusinessException("Provide a Type of Launch");
		}
		
	}

}
