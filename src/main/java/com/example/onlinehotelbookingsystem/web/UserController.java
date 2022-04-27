package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.binding.ProfileUpdateBindingModel;
import com.example.onlinehotelbookingsystem.model.service.ProfileServiceModel;
import com.example.onlinehotelbookingsystem.model.service.ProfileUpdateServiceModel;
import com.example.onlinehotelbookingsystem.model.view.ProfileViewModel;
import com.example.onlinehotelbookingsystem.service.CloudinaryService;
import com.example.onlinehotelbookingsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    //    PROFILE
    @PreAuthorize("isOwnerOfProfile(#id)")
    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable("id") Long id) {
        ProfileServiceModel profileServiceModel = this.userService.findById(id);
        ProfileViewModel profileViewModel = this.mapper.map(profileServiceModel, ProfileViewModel.class);
        model.addAttribute("profileViewModel", profileViewModel);
        return "profile";
    }

    @PreAuthorize("isOwnerOfProfile(#id)")
    @GetMapping("/profile/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        ProfileServiceModel profileServiceModel = this.userService.findById(id);
        ProfileUpdateBindingModel updateBindingModel = this.mapper.map(profileServiceModel, ProfileUpdateBindingModel.class);
        ProfileViewModel viewModel = this.mapper.map(profileServiceModel, ProfileViewModel.class);
        model.addAttribute("profileViewModel", viewModel);

        if (!model.containsAttribute("updateBindingModel")) {
            model.addAttribute("updateBindingModel", updateBindingModel);
        }

        return "update-profile";
    }

    @PatchMapping("/profile/update/{id}")
    public String updateProfile(@PathVariable("id") Long id,
                                @Valid ProfileUpdateBindingModel updateBindingModel,
                                BindingResult br, RedirectAttributes rAtt) throws IOException {

        if (br.hasErrors()) {
            rAtt
                    .addFlashAttribute("updateBindingModel", updateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.updateBindingModel", br);
//            updateBindingModel userId is null for some reason. That is why I am setting it here. -> PATCH "/users/profile/update/" Completed 405 METHOD_NOT_ALLOWED.            updateBindingModel.setUserId(id);
            updateBindingModel.setUserId(id);
            return "redirect:/users/profile/update/" + id;
        }

        ProfileUpdateServiceModel updateServiceModel = this.mapper.map(updateBindingModel, ProfileUpdateServiceModel.class);
        updateServiceModel.setUserId(id);

        this.userService.update(updateServiceModel);

        return "redirect:/users/profile/" + id;
    }
}
