package com.roy.simplecrud.dto.Request;

public class BreedRequest {
    // Definisikan field dan properti yang sesuai dengan permintaan Anda
    private String breedName;

    // Buat constructor default dan constructor dengan parameter (jika diperlukan)
    public BreedRequest() {
    }

    public BreedRequest(String breedName) {
        this.breedName = breedName;
    }

    // Definisikan getter dan setter untuk mengakses dan mengubah nilai properti breedName
    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
}

