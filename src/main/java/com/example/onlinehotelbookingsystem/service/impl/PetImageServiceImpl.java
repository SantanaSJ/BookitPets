package com.example.onlinehotelbookingsystem.service.impl;

import com.example.onlinehotelbookingsystem.model.entity.PetImageEntity;
import com.example.onlinehotelbookingsystem.repository.PetImageRepository;
import com.example.onlinehotelbookingsystem.service.CloudinaryService;
import com.example.onlinehotelbookingsystem.service.PetImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PetImageServiceImpl implements PetImageService
{
    private final CloudinaryService cloudinaryService;
    private final PetImageRepository petImageRepository;

    public PetImageServiceImpl(CloudinaryService cloudinaryService, PetImageRepository petImageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.petImageRepository = petImageRepository;
    }


    @Override
    public PetImageEntity addImage(MultipartFile file, String title) throws IOException {
        PetImageEntity petImageEntity = createPetImageEntity(file, title);
        PetImageEntity saved = this.petImageRepository.save(petImageEntity);
//        PetImageServiceModel serviceModel = this.mapper.map(saved, PetImageServiceModel.class);
        return saved;
    }

    private PetImageEntity createPetImageEntity(MultipartFile file, String title) throws IOException {
        CloudinaryImage uploaded = this.cloudinaryService.upload(file);
        PetImageEntity petImage = new PetImageEntity();
        petImage
                .setPublicId(uploaded.getPublicId())
                .setTitle(title)
                .setUrl(uploaded.getUrl());
        return petImage;
    }
}
