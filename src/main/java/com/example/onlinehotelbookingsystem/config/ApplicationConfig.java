package com.example.onlinehotelbookingsystem.config;
import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Map;

@Configuration
public class ApplicationConfig {

    private final CloudinaryConfig cloudinaryConfig;

    public ApplicationConfig(CloudinaryConfig cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                Map.of(
                        "cloud_name", this.cloudinaryConfig.getCloudName(),
                        "api_key", this.cloudinaryConfig.getApiKey(),
                        "api_secret", this.cloudinaryConfig.getApiSecret()
                )
        );

    }

}
