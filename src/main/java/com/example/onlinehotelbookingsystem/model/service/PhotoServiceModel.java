package com.example.onlinehotelbookingsystem.model.service;

public class PhotoServiceModel {

    private String title;
    private String url;
    private String notes;

    public String getTitle() {
        return title;
    }

    public PhotoServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PhotoServiceModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public PhotoServiceModel setNotes(String notes) {
        this.notes = notes;
        return this;
    }
}
