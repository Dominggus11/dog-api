package com.roy.simplecrud.dto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BreedResponseDto {
    private Map<String, List<String>> message;
    private String status;

    public BreedResponseDto() {
        message = new LinkedHashMap<>();
    }

    public BreedResponseDto(Map<String, List<String>> message, String status) {
        this.message = message;
        this.status = status;
    }

    public BreedResponseDto(String error, String resourceNotFound) {
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
}


