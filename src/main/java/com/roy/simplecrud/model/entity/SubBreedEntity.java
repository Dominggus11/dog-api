package com.roy.simplecrud.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subbreed")
public class SubBreedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id")
    private BreedEntity breed;
    @Column(name = "breed")
    private String breedName;

    public SubBreedEntity() {
    }

    public SubBreedEntity(String name) {
        this.name = name;

    }
    public SubBreedEntity(String name, String breedName) {
        this.name = name;
        this.breedName = breedName;
    }

    // Getter and Setter methods

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

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public void setBreed(BreedEntity breed) {
        this.breed = breed;
    }
    @Override
    public String toString() {
        return "SubBreedEntity{" +
                "name='" + name + '\'' +
                ", breedName='" + breedName + '\'' +
                '}';
    }
}
