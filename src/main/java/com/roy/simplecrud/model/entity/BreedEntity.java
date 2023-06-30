package com.roy.simplecrud.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "breed")
public class BreedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "breed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubBreedEntity> subBreeds;

    public BreedEntity() {
    }

    public BreedEntity(String name, List<SubBreedEntity> subBreeds) {
        this.name = name;
        this.subBreeds = subBreeds;
    }

    public BreedEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubBreedEntity> getSubBreeds() {
        return subBreeds;
    }

    public void setSubBreeds(List<SubBreedEntity> subBreeds) {
        this.subBreeds = subBreeds;
    }

    public void addSubBreed(SubBreedEntity subBreed) {
        if (subBreeds == null) {
            subBreeds = new ArrayList<>();
        }
        subBreeds.add(subBreed);
    }

    @Override
    public String toString() {
        return "BreedEntity{" +
                "name='" + name + '\'' +
                ", subBreeds=" + subBreeds +
                '}';
    }
}



