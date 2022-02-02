package com.example.onlinehotelbookingsystem.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private Long id;
    private String firstName;

    public CustomUser(String username, String password, Long id, String firstName,
                      Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.firstName = firstName;

    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
                      boolean credentialsNonExpired, boolean accountNonLocked,
                      Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                authorities);
    }

    // some own methods below.

    public Long getUserId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }
}
