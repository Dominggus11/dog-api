package com.roy.simplecrud.service;

import com.roy.simplecrud.dto.SubBreedApiResponse;
import com.roy.simplecrud.model.entity.BreedEntity;
import com.roy.simplecrud.model.entity.SubBreedEntity;
import com.roy.simplecrud.model.repository.BreedRepository;
import com.roy.simplecrud.model.repository.SubBreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SubBreedServiceClone {

    private final SubBreedRepository subBreedRepository;
    private final RestTemplate restTemplate;
    private final BreedRepository breedRepository;


    @Autowired
    public SubBreedServiceClone(SubBreedRepository subBreedRepository, RestTemplate restTemplate, BreedRepository breedRepository) {
        this.subBreedRepository = subBreedRepository;
        this.restTemplate = restTemplate;
        this.breedRepository = breedRepository;
    }


    public void fetchAndSaveSubbreedsFromDatabase() {
        List<BreedEntity> breeds = breedRepository.findAll();

        for (BreedEntity breed : breeds) {
            fetchAndSaveSubbreeds(breed.getName());
            System.out.println(breed.getName());
        }
    }

    public void fetchAndSaveSubbreeds(String breedName) {
        String subbreedUrl = "https://dog.ceo/api/breed/" + breedName + "/list";
        SubBreedApiResponse subbreedApiResponse = restTemplate.getForObject(subbreedUrl, SubBreedApiResponse.class);

        if (subbreedApiResponse != null && subbreedApiResponse.getMessage() != null) {
            for (String subbreedName : subbreedApiResponse.getMessage()) {
                BreedEntity breed = breedRepository.findByName(breedName); // Ganti breedRepository dengan repository yang sesuai untuk mencari objek BreedEntity berdasarkan nama ras
                SubBreedEntity subbreed = new SubBreedEntity(subbreedName, breedName);
                subBreedRepository.save(subbreed);
            }
        }
    }

}
