package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.service.AddToPhotosServiceModel;
import com.example.onlinehotelbookingsystem.model.service.PhotoServiceModel;

import java.util.List;

public interface PhotoService {
    void upload(AddToPhotosServiceModel toAlbumServiceModel);

    List<PhotoServiceModel> findPhotosByUser(Long id);

    boolean isOwnerOfAlbum(String email, Long id);
}
