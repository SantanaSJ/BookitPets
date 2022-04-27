package com.example.onlinehotelbookingsystem.model.service;

import org.springframework.web.multipart.MultipartFile;


public class ProfileUpdateServiceModel {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String petName;
    private Integer petKilograms;
    private String profileImageUrl;
    private String petImageUrl;
    private String userImageTitle;
    private MultipartFile userImageFile;
    private String petImageTitle;
    private MultipartFile petImageFile;

//    private AddUserImageBindingModel addUserImageBindingModel;
//    private AddPetImageBindingModel addPetImageBindingModel;

    public String getFirstName() {
        return firstName;
    }

    public ProfileUpdateServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileUpdateServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileUpdateServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public ProfileUpdateServiceModel setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public ProfileUpdateServiceModel setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ProfileUpdateServiceModel setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileUpdateServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

//    public AddPetImageBindingModel getAddPetImageBindingModel() {
//        return addPetImageBindingModel;
//    }
//
//    public ProfileUpdateServiceModel setAddPetImageBindingModel(AddPetImageBindingModel addPetImageBindingModel) {
//        this.addPetImageBindingModel = addPetImageBindingModel;
//        return this;
//    }

//    public AddUserImageBindingModel getAddUserImageBindingModel() {
//        return addUserImageBindingModel;
//    }
//
//    public ProfileUpdateServiceModel setAddUserImageBindingModel(AddUserImageBindingModel addUserImageBindingModel) {
//        this.addUserImageBindingModel = addUserImageBindingModel;
//        return this;
//    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public ProfileUpdateServiceModel setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
        return this;
    }

    public String getPetImageUrl() {
        return petImageUrl;
    }

    public ProfileUpdateServiceModel setPetImageUrl(String petImageUrl) {
        this.petImageUrl = petImageUrl;
        return this;
    }

    public String getUserImageTitle() {
        return userImageTitle;
    }

    public ProfileUpdateServiceModel setUserImageTitle(String userImageTitle) {
        this.userImageTitle = userImageTitle;
        return this;
    }

    public MultipartFile getUserImageFile() {
        return userImageFile;
    }

    public ProfileUpdateServiceModel setUserImageFile(MultipartFile userImageFile) {
        this.userImageFile = userImageFile;
        return this;
    }

    public String getPetImageTitle() {
        return petImageTitle;
    }

    public ProfileUpdateServiceModel setPetImageTitle(String petImageTitle) {
        this.petImageTitle = petImageTitle;
        return this;
    }

    public MultipartFile getPetImageFile() {
        return petImageFile;
    }

    public ProfileUpdateServiceModel setPetImageFile(MultipartFile petImageFile) {
        this.petImageFile = petImageFile;
        return this;
    }
}
