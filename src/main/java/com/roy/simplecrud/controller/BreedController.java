package com.roy.simplecrud.controller;

import com.roy.simplecrud.dto.BreedResponseDto;
import com.roy.simplecrud.dto.Request.BreedRequest;
import com.roy.simplecrud.model.entity.BreedEntity;
import com.roy.simplecrud.model.entity.SubBreedEntity;
import com.roy.simplecrud.service.BreedService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BreedController {

    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping("/breeds/list/all")
    public ResponseEntity<BreedResponseDto> getAllBreeds() {
        List<BreedEntity> breeds = breedService.getAllBreeds();
        Map<String, List<String>> breedMap = new HashMap<>();

        for (BreedEntity breed : breeds) {
            List<String> subBreeds = new ArrayList<>();
            for (SubBreedEntity subBreed : breed.getSubBreeds()) {
                subBreeds.add(subBreed.getName());
            }
            breedMap.put(breed.getName(), subBreeds);
        }

        BreedResponseDto responseDto = new BreedResponseDto();
        responseDto.setMessage(breedMap);
        responseDto.setStatus("success");

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/breed/{subbreed}/list")
    public ResponseEntity<BreedResponseDto> getSubBreedById(@PathVariable String subbreed) {
        try {
            BreedEntity breed = breedService.getBreedBySubbreed(subbreed);

            Map<String, List<String>> breedMap = new HashMap<>();
            List<String> subBreeds = new ArrayList<>();
            for (SubBreedEntity subBreed : breed.getSubBreeds()) {
                subBreeds.add(subBreed.getName());
            }
            breedMap.put(breed.getName(), subBreeds);

            BreedResponseDto responseDto = new BreedResponseDto();
            responseDto.setMessage(breedMap);
            responseDto.setStatus("success");

            return ResponseEntity.ok(responseDto);
        } catch (HttpClientErrorException.NotFound e) {
            // Tangani kesalahan 404 Not Found di sini
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BreedResponseDto("error", "Resource not found"));
        } catch (HttpClientErrorException e) {
            // Tangani kesalahan HttpClientErrorException lainnya di sini
            return ResponseEntity.status(e.getStatusCode())
                    .body(new BreedResponseDto("error", "HTTP error: " + e.getStatusCode()));
        } catch (Exception e) {
            // Tangani kesalahan umum di sini
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BreedResponseDto("error", "Error occurred: " + e.getMessage()));
        }
    }

    @PostMapping("/breed")
    public BreedEntity createBreed(@RequestBody BreedRequest breedRequest) {
        String breedName = breedRequest.getBreedName();
        return breedService.createBreed(breedName);
    }


    @DeleteMapping("/breed/{subbreed}")
    @Transactional
    public ResponseEntity<String> deleteBreed(@PathVariable("subbreed") BreedRequest breedRequest) {
        breedService.deleteBreed(breedRequest.getBreedName());
        return ResponseEntity.ok("Breed with subbreed '" + breedRequest.getBreedName() + "' has been deleted");
    }

    @PutMapping("/breed/{subbreed}")
    @Transactional
    public ResponseEntity<BreedEntity> updateBreed(@PathVariable String subbreed, @RequestBody BreedRequest breedRequest) {
        String updatedBreedName = breedRequest.getBreedName();
        BreedEntity updatedBreed = breedService.updateBreed(subbreed, updatedBreedName);

        if (updatedBreed != null) {
            return ResponseEntity.ok(updatedBreed);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
