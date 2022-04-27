package com.example.onlinehotelbookingsystem.model.view;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AllUsersViewModel {

    private Long id;
    private java.lang.String firstName;
    private java.lang.String lastName;
    private java.lang.String email;
    private Integer numberOfBookings;
    private List<String> roles = new ArrayList<>();
    private LocalDateTime joined;

    public java.lang.String getFirstName() {
        return firstName;
    }

    public AllUsersViewModel setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
        return this;
    }

    public java.lang.String getLastName() {
        return lastName;
    }

    public AllUsersViewModel setLastName(java.lang.String lastName) {
        this.lastName = lastName;
        return this;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public AllUsersViewModel setEmail(java.lang.String email) {
        this.email = email;
        return this;
    }

    public Integer getNumberOfBookings() {
        return numberOfBookings;
    }

    public AllUsersViewModel setNumberOfBookings(Integer numberOfBookings) {
        this.numberOfBookings = numberOfBookings;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public AllUsersViewModel setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public LocalDateTime getJoined() {
        return joined;
    }

    public AllUsersViewModel setJoined(LocalDateTime joined) {
        this.joined = joined;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AllUsersViewModel setId(Long id) {
        this.id = id;
        return this;
    }


}
