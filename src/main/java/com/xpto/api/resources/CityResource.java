package com.xpto.api.resources;

import java.io.File;
import java.io.FileOutputStream;
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

import com.xpto.api.entities.City;
import com.xpto.api.services.CityService;

@RestController
@RequestMapping(value = "/city")
public class CityResource {
	
	@Autowired
	private CityService cs;

	@GetMapping
	public ResponseEntity<City> findAll() {
		City city = new City(1100015L, "RO", "Alta Floresta D'Oeste", null, null, null, "Alta Floresta D'Oeste", null, "Cacoal", "Leste Rondoniense");
		
		return ResponseEntity.ok().body(city);
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		
		cs.createFile(file);
		return new ResponseEntity<>("Foi realizado o upload do arquivo "+file.getOriginalFilename()+" e importado ", HttpStatus.OK);	
	}
}