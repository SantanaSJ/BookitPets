package com.example.onlinehotelbookingsystem.model.binding;

import com.example.onlinehotelbookingsystem.model.binding.validator.ValidateMultipartForm;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddToPhotosBindingModel {

    private Long userId;
    private String notes;


    @NotBlank(message = "Image title is required!")
    @Size(min = 2, max = 20, message = "Title must be between 2-20 characters!")
    private String title;

    @ValidateMultipartForm
    private MultipartFile file;


    public String getNotes() {
        return notes;
    }

    public AddToPhotosBindingModel setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AddToPhotosBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getFile() {
        return file;
    }

    public AddToPhotosBindingModel setFile(MultipartFile file) {
        this.file = file;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public AddToPhotosBindingModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
