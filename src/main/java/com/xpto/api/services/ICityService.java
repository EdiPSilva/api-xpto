package com.xpto.api.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.api.entities.City;
import com.xpto.api.responses.DefaultResponse;

public interface ICityService {

	void createFile(MultipartFile file) throws IOException;
	
	DefaultResponse findCapitals();
	
	DefaultResponse getCountCityByState();

	DefaultResponse getMaxMinCidadeByState();

	DefaultResponse getCityByIbge(Long ibge);

	DefaultResponse getCityByState(String uf);

	DefaultResponse getTotal();

	DefaultResponse deleteCityByIbge(Long ibge);

	DefaultResponse insertOrUpdateCity(City city);

	DefaultResponse getQuantityColumn(String column);

	Page<City> getFilterByColumn(Map<String, String> filters, Integer page, Integer pageSize);
}