package com.xpto.api.responses;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.xpto.api.entities.City;

@JsonInclude(Include.NON_NULL)
public class DefaultResponse {

	private HttpStatus status;
	private String type;
	private Long records;
	private List<City> data;
	
	public DefaultResponse(HttpStatus status, String type, Long records, List<City> data) {
		super();
		this.status = status;
		this.type = type;
		this.records = records;
		this.data = data;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public List<City> getData() {
		return data;
	}

	public void setData(List<City> data) {
		this.data = data;
	}
}