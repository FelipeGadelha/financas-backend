package com.portifolio.udemy.financas.domain.service;

import java.util.List;

import com.portifolio.udemy.financas.domain.enums.StatusLaunch;
import com.portifolio.udemy.financas.domain.model.Launch;

public interface LaunchService {
	
	Launch save(Launch launch);
	Launch update(Launch launch);
	void delete(Launch launch);
	List <Launch> find(Launch launchFilter);
	void updateStatus(Launch launch, StatusLaunch status);
	void validate(Launch launch);

}
