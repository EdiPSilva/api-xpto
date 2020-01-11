package com.xpto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpto.api.entities.City;

public interface CityRepository extends JpaRepository<City, Long>  {

}
