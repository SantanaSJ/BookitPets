package com.example.onlinehotelbookingsystem.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pet_image")
public class PetImageEntity extends BaseEntity {


    private String title;
    private String url;
    private String publicId;

    public String getTitle() {
        return title;
    }

    public PetImageEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PetImageEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PetImageEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
