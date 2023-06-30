package com.roy.simplecrud.dto;

import com.roy.simplecrud.model.entity.SubBreedEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SubBreedResponseDto {
    private Map<String, List<String>> message;
    private String status;
    private SubBreedEntity subBreed;

    public SubBreedResponseDto() {
        message = new LinkedHashMap<>();
    }

    public SubBreedResponseDto(Map<String, List<String>> message, String status, SubBreedEntity subBreed) {
        this.message = message;
        this.status = status;
        this.subBreed = subBreed;
    }

    public Map<String, List<String>> getMessage() {
        return message;
    }

    public void setMessage(Map<String, List<String>> message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SubBreedEntity getSubBreed() {
        return subBreed;
    }

    public void setSubBreed(SubBreedEntity subBreed) {
        this.subBreed = subBreed;
    }
}
