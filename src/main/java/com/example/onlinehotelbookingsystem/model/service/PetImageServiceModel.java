package com.example.onlinehotelbookingsystem.model.service;

public class PetImageServiceModel {


    private String title;
    private String url;
    private String publicId;

    public String getTitle() {
        return title;
    }

    public PetImageServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PetImageServiceModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PetImageServiceModel setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
