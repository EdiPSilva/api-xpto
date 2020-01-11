package com.xpto.api.resources;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.api.responses.DefaultResponse;
import com.xpto.api.services.CityService;
import com.xpto.api.services.ICityService;

@RestController
@RequestMapping(value = "/city")
public class CityResource {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private ICityService iCityService;

	//Burcas as cidades capitais na base de dados.
	@GetMapping(value = "/capitais")
	public ResponseEntity<DefaultResponse> getCapitals() {
		DefaultResponse response = iCityService.findCapitals();
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}
	
	//Realizar upload de arquivo .csv para alimentar a base de dados.
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		cityService.createFile(file);
		return new ResponseEntity<>("Foi realizado o upload do arquivo "+file.getOriginalFilename()+" e importado ", HttpStatus.OK);	
	}
}