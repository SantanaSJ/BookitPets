package com.example.onlinehotelbookingsystem.model.view;

public class PhotoViewModel {
    private String title;
    private String url;
    private String notes;

    public String getTitle() {
        return title;
    }

    public PhotoViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PhotoViewModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public PhotoViewModel setNotes(String notes) {
        this.notes = notes;
        return this;
    }
}
