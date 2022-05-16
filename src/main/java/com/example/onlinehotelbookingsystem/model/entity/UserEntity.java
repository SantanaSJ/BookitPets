package com.example.onlinehotelbookingsystem.model.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String petName;

    @OneToOne
    private PetImageEntity petImage;

    @OneToOne
    private ProfileImageEntity profilePicture;

    private Integer petKilograms;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime joined;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<BookingEntity> bookings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<PhotoEntity> photoEntities;


    public UserEntity() {
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDateTime getJoined() {
        return joined;
    }

    public UserEntity setJoined(LocalDateTime joined) {
        this.joined = joined;
        return this;
    }

    public String getPetName() {
        return petName;
    }

    public UserEntity setPetName(String petName) {
        this.petName = petName;
        return this;
    }

    public Integer getPetKilograms() {
        return petKilograms;
    }

    public UserEntity setPetKilograms(Integer petKilograms) {
        this.petKilograms = petKilograms;
        return this;
    }

    public PetImageEntity getPetImage() {
        return petImage;
    }

    public UserEntity setPetImage(PetImageEntity petImage) {
        this.petImage = petImage;
        return this;
    }

    public ProfileImageEntity getProfilePicture() {
        return profilePicture;
    }

    public UserEntity setProfilePicture(ProfileImageEntity profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public UserEntity setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return roles.hashCode();
    }

    public List<PhotoEntity> getPhotoEntities() {
        return photoEntities;
    }

    public UserEntity setPhotoEntities(List<PhotoEntity> photoEntities) {
        this.photoEntities = photoEntities;
        return this;
    }
}


