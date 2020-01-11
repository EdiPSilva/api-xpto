package com.xpto.api.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

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
}