package com.example.onlinehotelbookingsystem.model.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private Integer petKilograms;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime joined;

//    @OneToMany
//    private List<BookingEntity> bookings = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "guest")
    private List<InvoiceEntity> invoices = new ArrayList<>();

//    @ManyToMany
//    private Set<RoomEntity> rooms;


//    @OneToMany(mappedBy = "guest")
//    private Set<InvoiceEntity> invoices;

    public UserEntity() {
    }

//    public List<PetEntity> getPets() {
//        return pets;
//    }
//
//    public UserEntity setPets(List<PetEntity> pets) {
//        this.pets = pets;
//        return this;
//    }


//    public List<BookingEntity> getBookings() {
//        return bookings;
//    }
//
//    public UserEntity setBookings(List<BookingEntity> bookings) {
//        this.bookings = bookings;
//        return this;
//    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public List<InvoiceEntity> getInvoices() {
        return invoices;
    }

    public UserEntity setInvoices(List<InvoiceEntity> invoices) {
        this.invoices = invoices;
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
}
