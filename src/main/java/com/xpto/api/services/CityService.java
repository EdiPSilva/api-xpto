package com.xpto.api.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xpto.api.entities.City;
import com.xpto.api.exceptions.CitiesExceptions;
import com.xpto.api.repositories.CityDTO;
import com.xpto.api.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository crep;
	
	@Autowired
	private CityDTO cdto;
	
	public void createFile(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename().trim();
		
		if (fileName.contains("..")) {
			throw new CitiesExceptions("O nome do arquivo é inválido. Por favor verifique e tente novamente!");
		}
		
		if (!file.getContentType().equals("text/csv")) {
			throw new CitiesExceptions("Por favor envie somente arquivos do tipo csv.");
		}
		
		File convertFile = new File(fileName);
		convertFile.createNewFile();
		
		FileOutputStream fout = new FileOutputStream(convertFile);
		fout.write(file.getBytes());
		fout.close();
		
		if (convertFile.exists()) {
			importCity(fileName);
		} else {
			throw new CitiesExceptions("Me desculpe. Houve um erro ao criar o arquivo. Por favor tente novamente.");
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
						//throw new CitiesExceptions("Lon value "+Double.parseDouble(columns[4].trim()));
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
					
					crep.save(city);
				}	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new CitiesExceptions("Me desculpe. Houve um erro ao criar o arquivo. Por favor tente novamente.");
		}
	}
}