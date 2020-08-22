package com.portifolio.udemy.financas.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.portifolio.udemy.financas.domain.enums.StatusLaunch;
import com.portifolio.udemy.financas.domain.enums.TypeLaunch;

@Entity
public class Launch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	private Integer month;
	
	private Integer year;
	
	@ManyToOne
	private User user;
	
	private BigDecimal value;
	
	private OffsetDateTime dateRegister;
	
	@Enumerated(EnumType.STRING)
	private TypeLaunch type;
	
	@Enumerated(EnumType.STRING)
	private StatusLaunch status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public OffsetDateTime getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(OffsetDateTime dateRegister) {
		this.dateRegister = dateRegister;
	}

	public TypeLaunch getType() {
		return type;
	}

	public void setType(TypeLaunch type) {
		this.type = type;
	}

	public StatusLaunch getStatus() {
		return status;
	}

	public void setStatus(StatusLaunch status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Launch other = (Launch) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
