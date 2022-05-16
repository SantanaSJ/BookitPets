package com.example.onlinehotelbookingsystem.model.binding;

import com.example.onlinehotelbookingsystem.model.binding.validator.ValidateMultipartForm;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class    ProfileUpdateBindingModel {

    private Long userId;

    @NotEmpty(message = "Please provide first name!")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters.")
    private String firstName;

    @NotBlank(message = "Please provide last name!")
    @Size(min = 3, max = 20, message = "Last name must be between 3 and 20 characters.")
    private String lastName;

    @NotBlank(message = "Please provide an email!")
    @Pattern(regexp = "^[^\\s@]+@([^\\s@.,]+\\.)+[^\\s@.,]{2,}$", message = "Please enter a valid email!")
    private String email;

    @NotBlank(message = "Please provide a phone number!")
    @Pattern(regexp = "^0\\d{9}$", message = "Phone number should be in the format: 0xx xx xx x")
    private String phoneNumber;

    @NotBlank(message = "Please provide your pet name!")
    @Size(min = 3, max = 10, message = "Pet name must be between 3 and 10 characters.")
    private String petName;

    private String profileImageUrl;
    private String petImageUrl;

    @NotBlank(message = "Photo title is required!")
    @Size(min = 2, max = 20, message = "Title must be between 2-20 characters!")
    private String userImageTitle;

    @ValidateMultipartForm
    private MultipartFile userImageFile;

    @NotBlank(message = "Photo title is required!")
    @Size(min = 2, max = 20, message = "Title must be between 2-20 characters!")
    private String petImageTitle;

    @ValidateMultipartForm
    private MultipartFile petImageFile;

    @NotNull(message = "Please provide pet kilograms.")
    @Positive
    private Integer petKilograms;

    public Long getUserId() {
        return userId;
    }

    public ProfileUpdateBindingModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfileUpdateBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileUpdateBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileUpdateBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public ProfileUpdateBindingModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public ProfileUpdateBindingModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileUpdateBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public ProfileUpdateBindingModel setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
        return this;
    }

    public String getPetImageUrl() {
        return petImageUrl;
    }

    public ProfileUpdateBindingModel setPetImageUrl(String petImageUrl) {
        this.petImageUrl = petImageUrl;
        return this;
    }

    public String getUserImageTitle() {
        return userImageTitle;
    }

    public ProfileUpdateBindingModel setUserImageTitle(String userImageTitle) {
        this.userImageTitle = userImageTitle;
        return this;
    }

    public MultipartFile getUserImageFile() {
        return userImageFile;
    }

    public ProfileUpdateBindingModel setUserImageFile(MultipartFile userImageFile) {
        this.userImageFile = userImageFile;
        return this;
    }

    public String getPetImageTitle() {
        return petImageTitle;
    }

    public ProfileUpdateBindingModel setPetImageTitle(String petImageTitle) {
        this.petImageTitle = petImageTitle;
        return this;
    }

    public MultipartFile getPetImageFile() {
        return petImageFile;
    }

    public ProfileUpdateBindingModel setPetImageFile(MultipartFile petImageFile) {
        this.petImageFile = petImageFile;
        return this;
    }
}
