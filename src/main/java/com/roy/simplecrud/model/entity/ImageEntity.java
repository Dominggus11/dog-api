package com.roy.simplecrud.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subbreed_id")
    private SubBreedEntity subbreed;

    public ImageEntity(String url) {
        this.url = url;
    }
}