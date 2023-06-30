package com.roy.simplecrud.service;

import com.roy.simplecrud.dto.ImageApiResponse;
import com.roy.simplecrud.model.entity.ImageEntity;
import com.roy.simplecrud.model.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageServiceClone {
    private final ImageRepository imageRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ImageServiceClone(ImageRepository imageRepository, RestTemplate restTemplate) {
        this.imageRepository = imageRepository;
        this.restTemplate = restTemplate;
    }

    public void fetchAndSaveImages(String breedName, String subbreedName) {
        String imageUrl = "https://dog.ceo/api/breed/" + breedName + "/" + subbreedName + "/images";
        ImageApiResponse imageApiResponse = restTemplate.getForObject(imageUrl, ImageApiResponse.class);

        for (String url : imageApiResponse.getMessage()) {
            ImageEntity image = new ImageEntity(url);
            imageRepository.save(image);
        }
    }
}

