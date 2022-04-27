package com.example.onlinehotelbookingsystem.config;

import com.example.onlinehotelbookingsystem.repository.UserRepository;
import com.example.onlinehotelbookingsystem.service.BookingHistoryService;
import com.example.onlinehotelbookingsystem.service.BookingService;
import com.example.onlinehotelbookingsystem.service.PhotoService;
import com.example.onlinehotelbookingsystem.service.UserService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class AppMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final BookingService bookingService;
    private final BookingHistoryService bookingHistoryService;
    private final UserService userService;
    private final PhotoService photoService;

    public AppMethodSecurityExpressionHandler(BookingService bookingService, BookingHistoryService bookingHistoryService,
                                              UserService userService, PhotoService photoService) {
        this.bookingService = bookingService;
        this.bookingHistoryService = bookingHistoryService;
        this.userService = userService;
        this.photoService = photoService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        OwnerSecurityExpressionRoot root = new OwnerSecurityExpressionRoot(authentication);

        root.setBookingService(this.bookingService);
        root.setBookingHistoryService(this.bookingHistoryService);
        root.setUserService(this.userService);
        root.setPhotoService(this.photoService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(new AuthenticationTrustResolverImpl());
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
