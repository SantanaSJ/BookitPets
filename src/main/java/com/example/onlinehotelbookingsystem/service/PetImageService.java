package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.model.entity.PetImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PetImageService {

    PetImageEntity addImage(MultipartFile file, String title) throws IOException;
}
