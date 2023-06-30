package com.roy.simplecrud.model.repository;

import com.roy.simplecrud.model.entity.BreedEntity;
import com.roy.simplecrud.model.entity.SubBreedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubBreedRepository extends JpaRepository<SubBreedEntity, Long> {
//    SubBreedEntity findByNameAndBreed(String subbreedName, BreedEntity breed);
}
