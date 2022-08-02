package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.binding.UserRegistrationBindingModel;
import com.example.onlinehotelbookingsystem.model.service.UserRegistrationServiceModel;
import com.example.onlinehotelbookingsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class AuthenticationController {

    private final UserService userService;
    private final ModelMapper mapper;

    public AuthenticationController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }


    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerAndLogin(@Valid UserRegistrationBindingModel userModel, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userModel.getPassword().equals(userModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);

            return "redirect:register";
        }

        UserRegistrationServiceModel serviceModel =
                this.mapper.map(userModel, UserRegistrationServiceModel.class);

        this.userService.registerAndLogin(serviceModel);

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String email, RedirectAttributes attributes) {

        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("email", email);

        return "redirect:/users/login";
    }

    @ModelAttribute("userModel")
    public UserRegistrationBindingModel userModel() {
        return new UserRegistrationBindingModel();
    }
}

