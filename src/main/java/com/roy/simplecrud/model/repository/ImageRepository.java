package com.roy.simplecrud.model.repository;

import com.roy.simplecrud.model.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
