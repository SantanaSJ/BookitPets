package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.service.UserService;
import com.example.onlinehotelbookingsystem.security.CustomUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.time.Instant;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

//    https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpSession.html
    @GetMapping("/")
    public String index(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            CustomUser principal = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String currentUserFirstName = principal.getFirstName();
            Long currentUserId = principal.getUserId();
//            String currentUserEmail = auth.getName();
//            String currentUserFirstName = this.userService.findUserByEmail(currentUserEmail);
//
//
            session.setAttribute("currentUserFirstName", currentUserFirstName);
            session.setAttribute("currentUserId", currentUserId);

            System.out.println(org.hibernate.Version.getVersionString());
            Instant instant = Instant.now();
            System.out.println(instant);

            return "home";
        }
        return "index";
    }

}

