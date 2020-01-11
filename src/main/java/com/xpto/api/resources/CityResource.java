package com.xpto.api.resources;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpto.api.entities.City;

@RestController
@RequestMapping(value = "/city")
public class CityResource {

	@GetMapping
	public ResponseEntity<City> findAll() {
		City city = new City(1100015L, "RO", "Alta Floresta D'Oeste", null, null, null, "Alta Floresta D'Oeste", null, "Cacoal", "Leste Rondoniense");
		return ResponseEntity.ok().body(city);
	}
}