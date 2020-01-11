package com.xpto.api.dto;

public class StateDTO {

	private String uf;
	private Long numberCity;
	
	public StateDTO(String uf, Long numberCity) {
		super();
		this.uf = uf;
		this.numberCity = numberCity;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getNumberCity() {
		return numberCity;
	}

	public void setNumberCity(Long numberCity) {
		this.numberCity = numberCity;
	}
}