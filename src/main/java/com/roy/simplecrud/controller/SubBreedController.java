package com.roy.simplecrud.controller;

import com.roy.simplecrud.dto.BreedResponseDto;
import com.roy.simplecrud.dto.Request.SubBreedRequestDto;
import com.roy.simplecrud.dto.SubBreedResponseDto;
import com.roy.simplecrud.model.entity.BreedEntity;
import com.roy.simplecrud.model.entity.SubBreedEntity;
import com.roy.simplecrud.service.SubBreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class SubBreedController {

    private final SubBreedService subBreedService;

    public SubBreedController(SubBreedService subBreedService) {
        this.subBreedService = subBreedService;
    }

    @GetMapping("/subbreeds")

    public ResponseEntity<SubBreedResponseDto> GetAllSubBreeds() {
        List<SubBreedEntity> breeds = subBreedService.getAllSubBreeds();
        Map<String, List<String>> breedMap = new HashMap<>();

        for (SubBreedEntity breed : breeds) {
            List<String> subBreeds = new ArrayList<>();
            breedMap.put(breed.getName(), subBreeds);
        }

        SubBreedResponseDto responseDto = new SubBreedResponseDto();
        responseDto.setMessage(breedMap);
        responseDto.setStatus("success");

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/subbreeds/{id}")
    public ResponseEntity<BreedResponseDto> getSubBreedById(@PathVariable Long id) {
        Optional<SubBreedEntity> subBreedOptional = subBreedService.getSubBreedById(id);

        if (subBreedOptional.isPresent()) {
            SubBreedEntity subBreed = subBreedOptional.get();
            Map<String, List<String>> breedMap = new HashMap<>();
            List<String> subBreeds = new ArrayList<>();
            breedMap.put(subBreed.getName(), subBreeds);
            BreedResponseDto responseDto = new BreedResponseDto();
            responseDto.setMessage(breedMap);
            responseDto.setStatus("success");

            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Autowired
    @PostMapping("/subbreeds")
    public ResponseEntity<SubBreedResponseDto> createSubBreed(@RequestBody SubBreedRequestDto requestDto) {
        try {
            SubBreedEntity subBreed = subBreedService.createSubBreed(requestDto.getSubBreedName(), requestDto.getBreedName());
            SubBreedResponseDto responseDto = new SubBreedResponseDto();
            responseDto.setMessage(Collections.singletonMap("success", Collections.singletonList("Subbreed created successfully")));
            responseDto.setStatus("success");
            responseDto.setSubBreed(subBreed);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            SubBreedResponseDto responseDto = new SubBreedResponseDto();
            responseDto.setMessage(Collections.singletonMap("error", Collections.singletonList("Failed to create subbreed:" + e.getMessage())));
            responseDto.setStatus("error");
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @PutMapping("/subbreeds/{id}")
    @ResponseBody
    public ResponseEntity<SubBreedEntity> updateSubBreed(@PathVariable Long id, @RequestBody SubBreedEntity subBreed) {
        subBreed.setId(id);
        SubBreedEntity updatedSubBreed = subBreedService.updateSubBreed(subBreed);
        return ResponseEntity.ok(updatedSubBreed);
    }
    @DeleteMapping("/subbreeds/{id}")
    @ResponseBody
    public ResponseEntity<SubBreedResponseDto> deleteSubBreed(@PathVariable Long id) {
        try {
            subBreedService.deleteSubBreed(id);
            SubBreedResponseDto responseDto = new SubBreedResponseDto();
            responseDto.setStatus("success");
            responseDto.setMessage(Collections.singletonMap("success", Collections.singletonList("Subbreed deleted successfully")));
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            SubBreedResponseDto responseDto = new SubBreedResponseDto();
            responseDto.setStatus("error");
            responseDto.setMessage(Collections.singletonMap("error", Collections.singletonList("Failed to delete subbreed:" + e.getMessage())));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
    }

}

