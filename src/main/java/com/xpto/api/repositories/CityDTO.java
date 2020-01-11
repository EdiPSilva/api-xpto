package com.xpto.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xpto.api.entities.City;

@Repository
public interface CityDTO extends CrudRepository<City, Long> {

}
