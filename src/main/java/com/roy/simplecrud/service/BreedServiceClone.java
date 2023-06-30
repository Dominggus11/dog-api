package com.roy.simplecrud.service;

import com.roy.simplecrud.dto.BreedApiResponse;
import com.roy.simplecrud.dto.ImageApiResponse;
import com.roy.simplecrud.dto.SubBreedApiResponse;
import com.roy.simplecrud.model.entity.BreedEntity;
import com.roy.simplecrud.model.entity.SubBreedEntity;
import com.roy.simplecrud.model.repository.BreedRepository;
import com.roy.simplecrud.model.repository.SubBreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BreedServiceClone {
    private final BreedRepository breedRepository;
    private final RestTemplate restTemplate;
    private final SubBreedRepository subBreedRepository;

    @Autowired
    public BreedServiceClone(BreedRepository breedRepository, RestTemplate restTemplate, SubBreedRepository subBreedRepository) {
        this.breedRepository = breedRepository;
        this.restTemplate = restTemplate;
        this.subBreedRepository = subBreedRepository;
    }
    public void fetchAndSaveBreeds() {
        String breedUrl = "https://dog.ceo/api/breeds/list";
        BreedApiResponse breedApiResponse = restTemplate.getForObject(breedUrl, BreedApiResponse.class);

        for (String breedName : breedApiResponse.getMessage()) {
            String subbreedUrl = "https://dog.ceo/api/breed/" + breedName + "/list";
            SubBreedApiResponse subbreedApiResponse = restTemplate.getForObject(subbreedUrl, SubBreedApiResponse.class);
                if(breedName.equals("sheepdog")) {
                    for (String subbreedName : subbreedApiResponse.getMessage()) {
                        BreedEntity breed = new BreedEntity(breedName.concat("-").concat(subbreedName));
                        breedRepository.save(breed); // Simpan BreedEntity terlebih dahulu
//                        System.out.println(breedName.concat("-").concat(subbreedName));
//                        String imageUrl = "https://dog.ceo/api/breed/" + breedName +"/" + subbreedName + "/images";
//                        System.out.println(imageUrl);
//                        ImageApiResponse imageApiResponse = restTemplate.getForObject(imageUrl, ImageApiResponse.class);
//                        System.out.println(imageApiResponse);
//                        for (String imageLink : imageApiResponse.getMessage()) {
//                            SubBreedEntity subBreed = new SubBreedEntity(imageLink);
//                            subBreed.setBreed(breed); // Set BreedEntity yang sudah disimpan
//                            subBreedRepository.save(subBreed);
//                        }
                    }

                }
                else if(breedName.equals("terrier")) {
                    for (String subbreedName : subbreedApiResponse.getMessage()) {
                        BreedEntity breed = new BreedEntity(breedName.concat("-").concat(subbreedName));
                        breedRepository.save(breed); // Simpan BreedEntity terlebih dahulu
                        System.out.println(breedName.concat("-").concat(subbreedName));
                        String imageUrl = "https://dog.ceo/api/breed/" + breedName +"/" + subbreedName + "/images";
                        System.out.println(imageUrl);
                        ImageApiResponse imageApiResponse = restTemplate.getForObject(imageUrl, ImageApiResponse.class);
                        System.out.println(imageApiResponse);
                        for (String imageLink : imageApiResponse.getMessage()) {
                            SubBreedEntity subBreed = new SubBreedEntity(imageLink);
                            subBreed.setBreed(breed); // Set BreedEntity yang sudah disimpan
                            subBreedRepository.save(subBreed);
                        }
                    }
                }
                else if (breedName.equals("shiba")) {
                    BreedEntity breed = new BreedEntity(breedName);
                    breedRepository.save(breed); // Simpan BreedEntity terlebih dahulu

                    String imageUrl = "https://dog.ceo/api/breed/" + breedName + "/images";
                    ImageApiResponse imageApiResponse = restTemplate.getForObject(imageUrl, ImageApiResponse.class);

                    List<String> filteredImages = getOddImages(imageApiResponse.getMessage());
                    for (String imageLink : filteredImages) {
                        SubBreedEntity subBreed = new SubBreedEntity(imageLink);
                        subBreed.setBreed(breed); // Set BreedEntity yang sudah disimpan
                        subBreedRepository.save(subBreed);
                    }
                }
                else if (subbreedApiResponse != null && subbreedApiResponse.getMessage() != null) {
                    BreedEntity breed = new BreedEntity(breedName);
                    breedRepository.save(breed); // Simpan BreedEntity terlebih dahulu

                    for (String subbreedName : subbreedApiResponse.getMessage()) {
                        SubBreedEntity subBreed = new SubBreedEntity(subbreedName);
                        subBreed.setBreed(breed); // Set BreedEntity yang sudah disimpan
                        subBreedRepository.save(subBreed);
                    }
                } else {
                    BreedEntity breed = new BreedEntity(breedName);
                    breedRepository.save(breed); // Simpan BreedEntity tanpa sub-ras
                }
            }
        }
    private List<String> getOddImages(List<String> images) {
        List<String> oddImages = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            if (i % 2 != 0) {
                oddImages.add(images.get(i));
            }
        }
        return oddImages;
    }
}
