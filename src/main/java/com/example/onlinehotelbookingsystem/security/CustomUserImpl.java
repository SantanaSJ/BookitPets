package com.example.onlinehotelbookingsystem.security;

import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
import com.example.onlinehotelbookingsystem.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found! "));
        return mapToUserDetails(userEntity);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        List<GrantedAuthority> authorities =
                userEntity
                        .getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                        .collect(Collectors.toList());
//        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
        return new CustomUser(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getId(),
                userEntity.getFirstName(),
                authorities);

    }
}
