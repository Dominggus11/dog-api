package com.roy.simplecrud.service;

import com.roy.simplecrud.model.entity.BreedEntity;
import com.roy.simplecrud.model.repository.BreedRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final BreedServiceClone breedServiceClone;
    private final SubBreedServiceClone subBreedServiceClone;
    private final BreedRepository breedRepository;

    @Autowired
    public DataInitializer(BreedServiceClone breedServiceClone, SubBreedServiceClone subBreedServiceClone, BreedRepository breedRepository) {
        this.breedServiceClone = breedServiceClone;
        this.subBreedServiceClone = subBreedServiceClone;
        this.breedRepository = breedRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {
        breedServiceClone.fetchAndSaveBreeds();
//        List<BreedEntity> breeds = breedRepository.findAll();
//        for (BreedEntity breed : breeds) {
//            subBreedServiceClone.fetchAndSaveSubbreeds(breed.getName());
//        }
    }
}

