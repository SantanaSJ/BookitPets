package com.example.onlinehotelbookingsystem.model.entity;

import com.example.onlinehotelbookingsystem.model.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEntity() {
    }

    public Long getId() {
        return id;
    }

    public UserRoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
