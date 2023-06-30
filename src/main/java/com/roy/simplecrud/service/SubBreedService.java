package com.roy.simplecrud.service;

import com.roy.simplecrud.model.entity.BreedEntity;
import com.roy.simplecrud.model.entity.SubBreedEntity;
import com.roy.simplecrud.model.repository.BreedRepository;
import com.roy.simplecrud.model.repository.SubBreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubBreedService {

    private final SubBreedRepository subBreedRepository;

    @Autowired
    public SubBreedService(SubBreedRepository subBreedRepository) {
        this.subBreedRepository = subBreedRepository;
    }
    @Autowired
    private BreedRepository breedRepository;
    public SubBreedEntity createSubBreed(String subbreed, String breed) {
        BreedEntity existingBreed = breedRepository.findByName(breed);

        if (existingBreed != null) {
            SubBreedEntity subBreed = new SubBreedEntity(subbreed);
            subBreed.setBreed(existingBreed);
            return subBreedRepository.save(subBreed);
        } else {
            throw new IllegalArgumentException("Breed not found");
        }
    }



    public List<SubBreedEntity> getAllSubBreeds() {
        return subBreedRepository.findAll();
    }

    public Optional<SubBreedEntity> getSubBreedById(Long id) {
        return subBreedRepository.findById(id);
    }

    public SubBreedEntity updateSubBreed(SubBreedEntity subBreed) {
        return subBreedRepository.save(subBreed);
    }

    public void deleteSubBreed(Long id) {
        subBreedRepository.deleteById(id);
    }
}

