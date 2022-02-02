package com.example.onlinehotelbookingsystem.config;

import com.example.onlinehotelbookingsystem.service.BookingService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class OwnerSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private BookingService bookingService;
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
