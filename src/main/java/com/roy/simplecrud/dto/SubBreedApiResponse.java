package com.roy.simplecrud.dto;

import java.util.List;

public class SubBreedApiResponse {
    private List<String> message;
    private String status;

    public SubBreedApiResponse() {
    }

    public SubBreedApiResponse(List<String> message, String status) {
        this.message = message;
        this.status = status;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
