package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.binding.ProfileUpdateBindingModel;
import com.example.onlinehotelbookingsystem.model.service.ProfileServiceModel;
import com.example.onlinehotelbookingsystem.model.service.ProfileUpdateServiceModel;
import com.example.onlinehotelbookingsystem.model.view.ProfileViewModel;
import com.example.onlinehotelbookingsystem.service.CloudinaryService;
import com.example.onlinehotelbookingsystem.service.UserService;
import org.modelmapper.ModelMapper;
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
    private final CloudinaryService cloudinaryService;

    public UserController(UserService userService, ModelMapper mapper, CloudinaryService cloudinaryService) {
        this.userService = userService;
        this.mapper = mapper;
        this.cloudinaryService = cloudinaryService;
    }

//    PROFILE
    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("isAuthorize" , true);
        ProfileServiceModel profileServiceModel = this.userService.findById(id);
        ProfileViewModel profileViewModel = this.mapper.map(profileServiceModel, ProfileViewModel.class);
        model.addAttribute("profileViewModel", profileViewModel);
        return "profile";

    }

//    UPDATE
    @GetMapping("/profile/update/{id}")
    public String showUpdate(@PathVariable ("id") Long id, Model model) {
        ProfileUpdateServiceModel profileServiceModel = this.userService.getProfileUpdateServiceModelById(id);
        ProfileUpdateBindingModel profileUpdateBindingModel = this.mapper.map(profileServiceModel, ProfileUpdateBindingModel.class);
        model.addAttribute("profileUpdateBindingModel", profileUpdateBindingModel);
        return "update-profile";

    }

    @PatchMapping("/profile/update/{id}")
    public String updateProfile(@PathVariable("id") Long id,
                                @Valid ProfileUpdateBindingModel updateBindingModel,
                                BindingResult br, RedirectAttributes rAtt) throws IOException {

        if (br.hasErrors()) {
            rAtt
                    .addFlashAttribute("updateBindingModel", updateBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.updateBindingModel", updateBindingModel);
            return "update-profile";
        }

        ProfileUpdateServiceModel updateServiceModel = this.mapper.map(updateBindingModel, ProfileUpdateServiceModel.class);
        updateServiceModel.setUserId(id);

        this.userService.update(updateServiceModel);

        return "redirect:/users/profile/" + id;
    }


}
