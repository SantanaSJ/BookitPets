package com.example.onlinehotelbookingsystem.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddImageBindingModel {


    @NotBlank(message = "Photo title is required!")
    @Size(min = 2, max = 20, message = "Title must be between 2-20 characters!")
    private String title;
    private MultipartFile file;

    public String getTitle() {
        return title;
    }

    public AddImageBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getFile() {
        return file;
    }

    public AddImageBindingModel setFile(MultipartFile file) {
        this.file = file;
        return this;
    }
}
