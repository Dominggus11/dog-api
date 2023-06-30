package com.roy.simplecrud.dto.Request;

public class SubBreedRequestDto {
    private String breedName;
    private String subBreedName;

    public SubBreedRequestDto() {
        // Constructor kosong
    }

    public SubBreedRequestDto(String breedName, String subBreedName) {
        this.breedName = breedName;
        this.subBreedName = subBreedName;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getSubBreedName() {
        return subBreedName;
    }

    public void setSubBreedName(String subBreedName) {
        this.subBreedName = subBreedName;
    }
}
