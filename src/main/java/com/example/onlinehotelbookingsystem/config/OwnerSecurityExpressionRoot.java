package com.example.onlinehotelbookingsystem.config;

import com.example.onlinehotelbookingsystem.model.entity.UserEntity;
import com.example.onlinehotelbookingsystem.model.service.ProfileServiceModel;
import com.example.onlinehotelbookingsystem.repository.UserRepository;
import com.example.onlinehotelbookingsystem.repository.UserRoleRepository;
import com.example.onlinehotelbookingsystem.service.BookingHistoryService;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.service.PhotoService;
import com.example.onlinehotelbookingsystem.service.UserService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class OwnerSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private BookingService bookingService;
    private BookingHistoryService bookingHistoryService;
    private UserService userService;
    private PhotoService photoService;
    private Object filterObject;
    private Object returnObject;

    public OwnerSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean isOwner(Long id) {
        String email = currentUserEmail();

        if (email != null) {
            return this.bookingService.isOwner(currentUserEmail(), id);
        }
        return false;
    }

    public boolean isOwnerHistory(Long id) {
        String email = currentUserEmail();
        if (email != null) {
            return this.bookingHistoryService.isOwnerHistory(currentUserEmail(), id);
        }
        return false;
    }

    public boolean isOwnerOfProfile(Long id) {
        String email = currentUserEmail();
        if (email != null) {
           return this.userService.isOwnerOfProfile(email, id);
        }
        return false;

    }
    public boolean isBookingsOwner(String email) {
        String currentEmail = currentUserEmail();

        if (email.equals(currentEmail)) {
            return true;
        }
        return false;

    }

    public boolean isOwnerOfAlbum(Long id) {
        String email = currentUserEmail();
        if (email != null) {
            return this.photoService.isOwnerOfAlbum(email, id);
        }
        return false;
    }

    private String currentUserEmail() {
        Authentication auth = getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) auth.getPrincipal()).getUsername();
        }
        return null;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void setBookingHistoryService(BookingHistoryService bookingHistoryService) {
        this.bookingHistoryService = bookingHistoryService;
    }

    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
