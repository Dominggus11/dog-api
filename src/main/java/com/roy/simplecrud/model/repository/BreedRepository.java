package com.roy.simplecrud.model.repository;

import com.roy.simplecrud.model.entity.BreedEntity;
import com.roy.simplecrud.model.entity.SubBreedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository extends JpaRepository<BreedEntity, Long> {
    BreedEntity findByName(String name);

    void deleteByName(String subbreed);

}
