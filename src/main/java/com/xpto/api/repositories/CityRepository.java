package com.xpto.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xpto.api.entities.City;

public interface CityRepository extends JpaRepository<City, Long>  {

	@Query(value = "select * from tb_city where capital = 'true'", nativeQuery = true)
    List<City> getCapitals();
	
	@Query(value = "select c.uf, count(c.ibge_id) from tb_city c group by c.uf", nativeQuery = true)
	List<Object> getCountCityByState();
	
	@Query(value = "select count(uf) count, uf from TB_CITY group by uf order by count(uf) desc limit 1", nativeQuery = true)
	List<Object> getMaxCityByState();
	
	@Query(value = "select count(uf) count, uf from TB_CITY  where uf != 'DF' group by uf order by count(uf) limit 1", nativeQuery = true)
	List<Object> getMinCityByState();
}