package com.roy.simplecrud.service;

import com.roy.simplecrud.model.entity.BreedEntity;
import com.roy.simplecrud.model.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreedService {
    private final BreedRepository breedRepository;

    @Autowired
    public BreedService(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    public BreedEntity createBreed(String breedName) {
        BreedEntity breed = new BreedEntity(breedName);
        return breedRepository.save(breed);
    }

    public List<BreedEntity> getAllBreeds() {
        return breedRepository.findAll();
    }

    public BreedEntity updateBreed(String subbreed, String updatedBreed) {
        Optional<BreedEntity> breedOptional = Optional.ofNullable(breedRepository.findByName(subbreed));
        if (breedOptional.isPresent()) {
            BreedEntity breed = breedOptional.get();
            breed.setName(updatedBreed);
            return breedRepository.save(breed);
        }
        return null; // or throw an exception
    }


    public void deleteBreed(String subbreed) {
        breedRepository.deleteByName(subbreed);
    }

    public BreedEntity getBreedBySubbreed(String subbreed) {
        return breedRepository.findByName(subbreed);
    }
}
