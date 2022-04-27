package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "profile_pictures")
public class ProfileImageEntity extends BaseEntity {

    private String title;
    private String url;
    private String publicId;

    public String getTitle() {
        return title;
    }

    public ProfileImageEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ProfileImageEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public ProfileImageEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
