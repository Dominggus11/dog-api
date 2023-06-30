package com.roy.simplecrud.dto;

import java.util.List;

import java.util.List;

public class ImageApiResponse {
    private List<String> message;
    private String status;

    public ImageApiResponse() {
    }

    public ImageApiResponse(List<String> message, String status) {
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

