package com.example.onlinehotelbookingsystem.model.service;

import org.springframework.web.multipart.MultipartFile;

public class AddPhotoServiceModel {

    private String title;

    private MultipartFile file;

    public String getTitle() {
        return title;
    }

    public AddPhotoServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getFile() {
        return file;
    }

    public AddPhotoServiceModel setFile(MultipartFile file) {
        this.file = file;
        return this;
    }
}
