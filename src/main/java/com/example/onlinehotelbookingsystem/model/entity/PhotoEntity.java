package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photos")
public class PhotoEntity extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    private String title;
    private String url;
    private String publicId;
    @Column(columnDefinition = "TEXT")
    private String notes;

    public String getTitle() {
        return title;
    }

    public PhotoEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PhotoEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PhotoEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public PhotoEntity setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public PhotoEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
