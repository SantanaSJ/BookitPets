package com.example.onlinehotelbookingsystem.model.service;

import org.springframework.web.multipart.MultipartFile;

public class AddToPhotosServiceModel {

    private Long userId;
    private String notes;

    private String title;

    private MultipartFile file;

//    not mapped with ServiceModel
//    private AddPhotoBindingModel addPhotoBindingModel;

    public Long getUserId() {
        return userId;
    }

    public AddToPhotosServiceModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public AddToPhotosServiceModel setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AddToPhotosServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getFile() {
        return file;
    }

    public AddToPhotosServiceModel setFile(MultipartFile file) {
        this.file = file;
        return this;
    }

    //    public AddPhotoServiceModel getAddPhotoServiceModel() {
//        return addPhotoServiceModel;
//    }
//
//    public AddToPhotosServiceModel setAddPhotoServiceModel(AddPhotoServiceModel addPhotoServiceModel) {
//        this.addPhotoServiceModel = addPhotoServiceModel;
//        return this;
//    }
}
