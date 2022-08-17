package com.example.onlinehotelbookingsystem.web;

import com.example.onlinehotelbookingsystem.model.binding.AddToPhotosBindingModel;
import com.example.onlinehotelbookingsystem.model.service.AddToPhotosServiceModel;
import com.example.onlinehotelbookingsystem.model.service.PhotoServiceModel;
import com.example.onlinehotelbookingsystem.model.service.ProfileServiceModel;
import com.example.onlinehotelbookingsystem.model.view.PhotoViewModel;
import com.example.onlinehotelbookingsystem.model.view.ProfileViewModel;
import com.example.onlinehotelbookingsystem.service.PhotoService;
import com.example.onlinehotelbookingsystem.service.UserService;
import com.example.onlinehotelbookingsystem.web.responseMessages.Message;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AlbumController {
    private final ModelMapper mapper;
    private final PhotoService photoService;
    private final UserService userService;

    public AlbumController(ModelMapper mapper, PhotoService albumService, UserService userService) {
        this.mapper = mapper;
        this.photoService = albumService;
        this.userService = userService;
    }


    @PreAuthorize("isOwnerOfAlbum(#id) or hasRole('ADMIN')")
    @GetMapping("/view-album/{id}")
    public String viewAlbum(@PathVariable Long id, Model model) {
        List<PhotoServiceModel> photosByUser = this.photoService.findPhotosByUser(id);
        List<PhotoViewModel> viewModels = photosByUser
                .stream()
                .map(p -> this.mapper.map(p, PhotoViewModel.class))
                .collect(Collectors.toList());

        ProfileServiceModel serviceModel = this.userService.findById(id);
        ProfileViewModel viewModel = this.mapper.map(serviceModel, ProfileViewModel.class);
        model
//                .addAttribute("userName", viewModel.getFirstName())
//                .addAttribute("petName", viewModel.getPetName())
                .addAttribute("pageTitle",
                        String.format("%s and %s's photo album", viewModel.getFirstName(), viewModel.getPetName()));

        if (viewModels.isEmpty()) {
            model.addAttribute("noContent", new Message("Nothing to show. Go make some memories!"));
        } else {
            model.addAttribute("photoViewModels", viewModels);
        }

        return "album-photos";

    }

    @GetMapping("/upload")
    public String showUploadPage() {

        return "album-upload";
    }

    @ModelAttribute("photosBindingModel")
    public AddToPhotosBindingModel photosBindingModel() {
        return new AddToPhotosBindingModel();
    }

    @PreAuthorize("isOwnerOfAlbum(#id) or hasRole('ADMIN')")
    @PostMapping("/album/upload/user/{id}")
    public String upload(@PathVariable("id") Long id, @Valid AddToPhotosBindingModel photosBindingModel,
                         BindingResult br, RedirectAttributes rAtt) {

        if (br.hasErrors()) {
            rAtt
                    .addFlashAttribute("photosBindingModel", photosBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.photosBindingModel", br);
            return "redirect:/upload";
        }

        AddToPhotosServiceModel toPhotosServiceModel = this.mapper.map(photosBindingModel, AddToPhotosServiceModel.class);
        toPhotosServiceModel.setUserId(id);
        this.photoService.upload(toPhotosServiceModel);
        System.out.println();
        return "redirect:/view-album/" + id;
    }
}
