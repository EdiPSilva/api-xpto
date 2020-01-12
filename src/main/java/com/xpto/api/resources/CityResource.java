package com.xpto.api.resources;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.api.entities.City;
import com.xpto.api.responses.DefaultResponse;
import com.xpto.api.services.ICityService;

@RestController
@RequestMapping(value = "/city")
public class CityResource {
	
	@Autowired
	private ICityService cityService;
	
	//Realizar upload de arquivo .csv para alimentar a base de dados.
	@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		cityService.createFile(file);
		return new ResponseEntity<>("Foi realizado o upload do arquivo "+file.getOriginalFilename()+" e importado para a base de dados.", HttpStatus.OK);	
	}
	
	//Burcas as cidades capitais na base de dados.
	@GetMapping(value = "/cities")
	public ResponseEntity<DefaultResponse> getCapitals() {
		DefaultResponse response = cityService.findCapitals();
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}
	
	//Busca os estados com a contagem de cidades.
		@GetMapping(value = "/max-min-cities-state")
		public ResponseEntity<DefaultResponse> getMaxMinCidadeByState() {
			DefaultResponse response = cityService.getMaxMinCidadeByState();
			return new ResponseEntity<DefaultResponse>(response, response.getStatus());
		}
	
	//Busca os estados com a contagem de cidades.
	@GetMapping(value = "/quantity-cities-by-state")
	public ResponseEntity<DefaultResponse> getCountCityByState() {
		DefaultResponse response = cityService.getCountCityByState();
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}
	
	//Buscar cidade por cógido IBGE.
	@GetMapping(value = "/{ibge}")
	public ResponseEntity<DefaultResponse> getCityByIbge(@PathVariable("ibge") Long ibge) {
		DefaultResponse response = cityService.getCityByIbge(ibge);
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}
	
	//Buscar lista de cidades por UF.
	@GetMapping(value = "/cities-by-state/{uf}")
	public ResponseEntity<DefaultResponse> getCityByState(@PathVariable("uf") String uf) {	
		DefaultResponse response = cityService.getCityByState(uf);
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}
	
	//Buscar o total de registros da dabase de dados.
	@GetMapping(value = "/total")
	public ResponseEntity<DefaultResponse> getTotal() {		
		DefaultResponse response = cityService.getTotal();
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}
	
	//Deleta uma cidade por cógido IBGE.
	@DeleteMapping(value = "/{ibge}")
	public ResponseEntity<DefaultResponse> deleteCityByIbge(@PathVariable("ibge") Long ibge) {
		DefaultResponse response = cityService.deleteCityByIbge(ibge);
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}
	
	//Insere ou atualiza uma cidade.
	@PostMapping()
	public ResponseEntity<DefaultResponse> insertOrUpdateCity(@RequestBody City city) {
		DefaultResponse response = cityService.insertOrUpdateCity(city);
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}
	
	//Buscar a quantidade de registro a partir de uma coluna.
	@GetMapping(value = "/quantity-column/{column}")
	public ResponseEntity<DefaultResponse> getQuantityColumn(@PathVariable("column") String column) {	
		DefaultResponse response = cityService.getQuantityColumn(column);
		return new ResponseEntity<DefaultResponse>(response, response.getStatus());
	}
	
	//Busca objetos a partir de coluna (filtro)
	@GetMapping(value = "/filter-column")
	public Page<City> getFilterByColumn(
			@RequestParam(required = false) Map<String, String> filters,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pagesize)
	{	
		return cityService.getFilterByColumn(filters, page, pagesize);
	}
}