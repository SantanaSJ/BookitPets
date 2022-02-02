package com.example.onlinehotelbookingsystem.service;

import com.example.onlinehotelbookingsystem.service.impl.CloudinaryImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    CloudinaryImage upload(MultipartFile file) throws IOException;
    boolean delete(String publicId);

}
