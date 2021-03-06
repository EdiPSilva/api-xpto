package com.xpto.api.services;

import static com.xpto.api.specification.CitySpecification.filterWithOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.api.dto.LongesDistanceCityDTO;
import com.xpto.api.entities.City;
import com.xpto.api.exceptions.CitiesException;
import com.xpto.api.exceptions.DefaultException;
import com.xpto.api.repositories.CityRepository;
import com.xpto.api.responses.DefaultResponse;
import com.xpto.api.util.Util;

@Service
public class CityService implements ICityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public void createFile(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename().trim();
		
		if (fileName.contains("..")) {
			throw new CitiesException("O nome do arquivo é inválido. Por favor verifique e tente novamente!");
		}
		
		if (!file.getContentType().equals("text/csv")) {
			throw new CitiesException("Por favor envie somente arquivos do tipo csv.");
		}
		
		File convertFile = new File(fileName);
		convertFile.createNewFile();
		
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		
		if (convertFile.exists()) {
			importCity(fileName);
		} else {
			throw new CitiesException("Me desculpe. Houve um erro ao criar o arquivo. Por favor tente novamente.");
		}
	}
	
	private void importCity(String fileName) {
		
		try {
			String line = null;
			int index = 0;
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ISO-8859-1"));
			
			while ((line = bufferedReader.readLine()) != null) {
				if (index == 0) {
					index++;
				} else {
					String[] columns = line.split(",");
					
					City city = new City();
					
					if (columns[0] != null && !columns[0].trim().isEmpty()) {
						city.setIbgeId(Long.parseLong(columns[0].trim()));
					}
					
					if (columns[1] != null && !columns[1].trim().isEmpty()) {
						city.setUf(columns[1].trim());
					}
					
					if (columns[2] != null && !columns[2].trim().isEmpty()) {
						city.setName(columns[2].trim());
					}
					
					if (columns[3] != null && !columns[3].trim().isEmpty()) {
						city.setCapital(columns[3].trim());
					}
					
					if (columns[4] != null && !columns[4].trim().isEmpty()) {
						city.setLon(Double.parseDouble(columns[4].trim()));
					}
					
					if (columns[5] != null && !columns[5].trim().isEmpty()) {
						city.setLat(Double.parseDouble(columns[5].trim()));
					}
					
					if (columns[6] != null && !columns[6].trim().isEmpty()) {
						city.setNoAccents(columns[6].trim());
					}
					
					if (columns[7] != null && !columns[7].trim().isEmpty()) {
						city.setAlternativeNames(columns[7].trim());
					}
					
					if (columns[8] != null && !columns[8].trim().isEmpty()) {
						city.setMicroregion(columns[8].trim());
					}
					
					if (columns[9] != null && !columns[9].trim().isEmpty()) {
						city.setMesoregio(columns[9].trim());
					}
					
					cityRepository.save(city);
				}	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new CitiesException("Me desculpe. Houve um erro ao criar o arquivo. Por favor tente novamente.");
		}
	}
	
	@Override
	public DefaultResponse findCapitals() {
		try {
			List<Object> listCity = Util.castObjectList(cityRepository.getCapitals(), Object.class);
			HttpStatus status = HttpStatus.OK;
			if (listCity.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			return new DefaultResponse(status, "application/json", Long.valueOf(listCity.size()), listCity);
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse getCountCityByState() {
		try {
			List<Object> listObject = cityRepository.getCountCityByState();
			HttpStatus status = HttpStatus.OK;
			if (listObject.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			return new DefaultResponse(status, "application/json", Long.valueOf(listObject.size()), listObject);
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse getMaxMinCidadeByState() {
		try {
			List<Object> listMaxState = cityRepository.getMaxCityByState();
			List<Object> listMinState = cityRepository.getMinCityByState();
			
			List<Object> listObject = Util.castObjectList(Arrays.asList(listMaxState, listMinState), Object.class);
			HttpStatus status = HttpStatus.OK;
			if (listObject.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			return new DefaultResponse(status, "application/json", Long.valueOf(listObject.size()), listObject);
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse getCityByIbge(Long ibge) {
		try {
			if (ibge == null) {
				throw new DefaultException("O parâmetro uf está inválido.", HttpStatus.BAD_REQUEST);
			}
			
			Optional<City> city = cityRepository.findById(ibge);
			List<Object> listObject = Util.castObjectList(Arrays.asList(city), Object.class);
			
			HttpStatus status = HttpStatus.OK;
			if (listObject.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			return new DefaultResponse(status, "application/json", Long.valueOf(listObject.size()), listObject);
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	private boolean checkValidState(String uf) {
		boolean result = false;
		
		if (uf == null || uf.isEmpty() || uf.length() != 2) {
			result = true;
		}
		
		switch (uf.toUpperCase()) {
			case "AC":
			case "AL":
			case "AP":
			case "AM":
			case "BA":
			case "CE":
			case "DF":
			case "ES":
			case "GO":
			case "MA":
			case "MT":
			case "MS":
			case "MG":
			case "PA":
			case "PB":
			case "PR":
			case "PE":
			case "PI":
			case "RJ":
			case "RN":
			case "RS":
			case "RO":
			case "RR":
			case "SC":
			case "SP":
			case "SE":
			case "TO":
				result = false;
			break;
			default:
				result = true;
				break;
		}
		
		return result;
	}
	
	@Override
	public DefaultResponse getCityByState(String uf) {
		try {
			if (checkValidState(uf)) {
				throw new DefaultException("O parâmetro uf está inválido.", HttpStatus.BAD_REQUEST);
			}
			
			List<Object> list = cityRepository.getCityByState(uf.toUpperCase());
			
			HttpStatus status = HttpStatus.OK;
			if (list.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			DefaultResponse response = new DefaultResponse(status, "application/json", Long.valueOf(list.size()), list);
			return response;
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse getTotal() {
		try {
			List<Object> listObject = Util.castObjectList(Arrays.asList(cityRepository.getTotal()), Object.class);
			
			HttpStatus status = HttpStatus.OK;
			if (listObject.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			return new DefaultResponse(status, "application/json", Long.valueOf(listObject.size()), listObject);
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse deleteCityByIbge(Long ibge) {
		try {
			Optional<City> city = cityRepository.findById(ibge);
			
			if (city == null || city.isEmpty()) {
				throw new DefaultException("Cidade não localizada na base de dados.", HttpStatus.BAD_REQUEST);
			}
			
			List<Object> listObject = Util.castObjectList(Arrays.asList(city), Object.class);
			cityRepository.deleteById(ibge);
			
			HttpStatus status = HttpStatus.OK;
			if (listObject.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			return new DefaultResponse(status, "application/json", Long.valueOf(listObject.size()), listObject, "Cidade removida com sucesso.");
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse insertOrUpdateCity(City city) {
		try {
			if (city == null) {
				throw new DefaultException("Cidade inválida.", HttpStatus.BAD_REQUEST);
			}
			
			cityRepository.save(city);
			List<Object> listObject = Util.castObjectList(Arrays.asList(city), Object.class);
			
			HttpStatus status = HttpStatus.OK;
			if (listObject.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			return new DefaultResponse(status, "application/json", Long.valueOf(listObject.size()), listObject, "Cidade cadastrada ou atualizada com sucesso.");
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public DefaultResponse getQuantityColumn(String column) {
		try {
			if (column == null || column.isEmpty()) {
				throw new DefaultException("O parâmetro coluna está inválido.", HttpStatus.BAD_REQUEST);
			}
			
			Long quantity  = (long) cityRepository.findAll().stream().map(c -> {return checkColumnExist(c, column);}).distinct().collect(Collectors.toList()).size();
			
			Map<String, Long> map = new HashMap<String, Long>();
			map.put(column, quantity);
			
			List<Object> listObject = Util.castObjectList(Arrays.asList(map), Object.class);
			
			HttpStatus status = HttpStatus.OK;
			if (listObject.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			
			return new DefaultResponse(status, "application/json", Long.valueOf(listObject.size()), listObject);
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	//O switch faz a identificação e retorna o nome do campo corretamente pertinente a entidade City
	private Object checkColumnExist(City city, String column) {
		
		switch (column.toLowerCase()) {
			case "ibge_id":
			case "ibgeid":
				column = "ibgeId";
				break;
			case "uf":
				column = "uf";
				break;
			case "name":
				column = "name";
				break;
			case "capital":
				column = "capital";
				break;
			case "lon":
				column = "lon";
				break;
			case "lat":
				column = "lat";
				break;
			case "noaccents":
			case "no_accents":
				column = "noAccents";
				break;
			case "alternativenames":
			case "alternative_names":
				column = "alternativeNames";
				break;
			case "microregion":
				column = "microregion";
				break;
			case "mesoregio":
				column = "mesoregio";
				break;
			default:
				column = null;
				break;
		}
		
		if (column == null) {
			throw new DefaultException("A coluna enviada não existe na base de dados.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			Object bjField = null;
			Field field = city.getClass().getDeclaredField(column);
			field.setAccessible(true);
			return field.get(city);
			
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException | NoSuchFieldException e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public Page<City> getFilterByColumn(Map<String, String> filters, Integer page, Integer pageSize) {
		try {
			return cityRepository.findAll(filterWithOptions(filters), PageRequest.of(page, pageSize, Direction.ASC, "ibgeId"));	        
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	private double calculateDistance(double lat1, double lat2, double lon1, double lon2) {
		
		return (((Math.acos(
					Math.sin(lat1 * Math.PI / 180.0) * 
					Math.sin(lat2 * Math.PI / 180.0) +
					Math.cos(lat1 * Math.PI / 180.0) * 
					Math.cos(lat2 * Math.PI / 180.0) * 
					Math.cos((lon1 - lon2) * Math.PI / 180.0))
				) * 180 / Math.PI
				) * 60 * 1.1515
				) * 1.609344; //Converte para KM;
	}

	@Override
	public DefaultResponse longestDistanceBetweenCities() {
		try {			
			
			List<City> listCity = cityRepository.findAll();
			
			double longestDistance = 0;
			
			City city1 = null;
			City city2 = null;
			
			for (City c1 : listCity) {
				for (City c2 : listCity) {
					if (!c1.equals(c2)) {
						
						double auxLongestDistance = calculateDistance(
								c1.getLat(), 
								c2.getLat(), 
								c1.getLon(), 
								c2.getLon());
						
						if (auxLongestDistance > longestDistance) {
							longestDistance = auxLongestDistance;
							city1 = c1;
							city2 = c2;
						}
					}
				}
			}
			
			LongesDistanceCityDTO dto = new LongesDistanceCityDTO(city1, city2, longestDistance);
			
			List<Object> listObject = Util.castObjectList(Arrays.asList(dto), Object.class);
			
			HttpStatus status = HttpStatus.OK;
			if (listObject.isEmpty()) {
				status = HttpStatus.NO_CONTENT;
			}
			
			return new DefaultResponse(status, "application/json", Long.valueOf(listObject.size()), listObject);
		} catch (Exception e) {
			throw new DefaultException(e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}