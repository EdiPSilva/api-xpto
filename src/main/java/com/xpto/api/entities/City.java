package com.xpto.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_city")
public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long ibgeId;
	
	@NotBlank
	private String uf;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String capital;
	
	@NotNull
	@Digits(integer = 10, fraction = 10)
	private BigDecimal lon;
	
	@NotNull
	@Digits(integer = 10, fraction = 10)
	private BigDecimal lat;
	
	@NotBlank
	@Column(name = "no_accents")
	private String noAccents;
	private String alternativeNames;
	
	@NotBlank
	private String microregion;
	
	@NotBlank
	private String mesoregio;
	
	public City() {
	}

	

	public City(Long ibgeId, @NotBlank String uf, @NotBlank String name, @NotBlank String capital,
			@NotNull @Digits(integer = 10, fraction = 10) BigDecimal lon,
			@NotNull @Digits(integer = 10, fraction = 10) BigDecimal lat, @NotBlank String noAccents,
			String alternativeNames, @NotBlank String microregion, @NotBlank String mesoregio) {
		super();
		this.ibgeId = ibgeId;
		this.uf = uf;
		this.name = name;
		this.capital = capital;
		this.lon = lon;
		this.lat = lat;
		this.noAccents = noAccents;
		this.alternativeNames = alternativeNames;
		this.microregion = microregion;
		this.mesoregio = mesoregio;
	}



	public Long getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Long ibgeId) {
		this.ibgeId = ibgeId;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregio() {
		return mesoregio;
	}

	public void setMesoregio(String mesoregio) {
		this.mesoregio = mesoregio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ibgeId == null) ? 0 : ibgeId.hashCode());
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
		City other = (City) obj;
		if (ibgeId == null) {
			if (other.ibgeId != null)
				return false;
		} else if (!ibgeId.equals(other.ibgeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [ibgeId=" + ibgeId + ", uf=" + uf + ", name=" + name + ", capital=" + capital + ", lon=" + lon
				+ ", lat=" + lat + ", noAccents=" + noAccents + ", alternativeNames=" + alternativeNames
				+ ", microregion=" + microregion + ", mesoregio=" + mesoregio + "]";
	}
}