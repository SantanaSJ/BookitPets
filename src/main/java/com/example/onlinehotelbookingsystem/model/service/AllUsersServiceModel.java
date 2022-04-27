package com.example.onlinehotelbookingsystem.model.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AllUsersServiceModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer numberOfBookings;
    private List<String> roles = new ArrayList<>();
    private LocalDateTime joined;

    public String getFirstName() {
        return firstName;
    }

    public AllUsersServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AllUsersServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AllUsersServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public AllUsersServiceModel setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public Integer getNumberOfBookings() {
        return numberOfBookings;
    }

    public AllUsersServiceModel setNumberOfBookings(Integer numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
        return this;
    }

    public LocalDateTime getJoined() {
        return joined;
    }

    public AllUsersServiceModel setJoined(LocalDateTime joined) {
        this.joined = joined;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AllUsersServiceModel setId(Long id) {
        this.id = id;
        return this;
    }


}
