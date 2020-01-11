package com.xpto.api.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.xpto.api.responses.DefaultResponse;

public interface ICityService {

	void createFile(MultipartFile file) throws IOException;
	
	DefaultResponse findCapitals();
	
	DefaultResponse getCountCityByState();
}