package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.PhotoEntity;
import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
import com.example.onlinehotelbookingsystem.model.service.AddToPhotosServiceModel;
import com.example.onlinehotelbookingsystem.model.service.PhotoServiceModel;
import com.example.onlinehotelbookingsystem.repository.PhotoRepository;
import com.example.onlinehotelbookingsystem.repository.UserRepository;
import com.example.onlinehotelbookingsystem.service.CloudinaryService;
import com.example.onlinehotelbookingsystem.service.PhotoService;
import com.example.onlinehotelbookingsystem.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;

    public PhotoServiceImpl(PhotoRepository photoRepository, ModelMapper mapper, UserRepository userRepository, CloudinaryService cloudinaryService) {
        this.photoRepository = photoRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
    }


    @Override
    public void upload(AddToPhotosServiceModel toAlbumServiceModel) {
        UserEntity userEntity = getUserEntity(toAlbumServiceModel);

        PhotoEntity photoEntity = getPhotoEntity(toAlbumServiceModel, userEntity);


        MultipartFile file = toAlbumServiceModel.getFile();
        String title = toAlbumServiceModel.getTitle();

        try {
            createPhotoEntity(file, title, photoEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.photoRepository.save(photoEntity);
    }

    private PhotoEntity getPhotoEntity(AddToPhotosServiceModel toAlbumServiceModel, UserEntity user) {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity
                .setNotes(toAlbumServiceModel.getNotes())
                .setUser(user);
        return photoEntity;
    }

    private UserEntity getUserEntity(AddToPhotosServiceModel toAlbumServiceModel) {
        return this.userRepository
                    .findById(toAlbumServiceModel.getUserId())
                    .orElseThrow(() -> new ObjectNotFoundException("User with id " + toAlbumServiceModel.getUserId() + "not found!"));
    }

    @Override
    public List<PhotoServiceModel> findPhotosByUser(Long id) {
        List<PhotoEntity> photoEntities = this.photoRepository.findByUserId(id);
        List<PhotoServiceModel> collect = photoEntities
                .stream()
                .map(p -> this.mapper.map(p, PhotoServiceModel.class))
                .collect(Collectors.toList());
        System.out.println();
        return collect;
    }

    @Override
    public boolean isOwnerOfAlbum(String currentUserEmail, Long id) {
        UserEntity userEntity = this.userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User with id " + id + " not found!"));
//        PhotoEntity photoEntity = this.photoRepository.findById(userEntity.getId()).orElseThrow(() -> new ObjectNotFoundException("Album for user with id " + id + " not found!"));

        String email = userEntity.getEmail();
        if (email.equals(currentUserEmail)) {
            return true;
        }
        return false;
    }


    private PhotoEntity createPhotoEntity(MultipartFile file, String title, PhotoEntity photoEntity) throws IOException {
        CloudinaryImage uploaded = this.cloudinaryService.upload(file);
        photoEntity
                .setPublicId(uploaded.getPublicId())
                .setTitle(title)
                .setUrl(uploaded.getUrl());
        return photoEntity;
    }
}
