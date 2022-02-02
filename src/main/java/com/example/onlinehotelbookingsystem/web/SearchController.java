package com.example.onlinehotelbookingsystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class SearchController {

    @GetMapping("search")
    public String showSearchForm() {
        return "search-hotels";
    }
}
