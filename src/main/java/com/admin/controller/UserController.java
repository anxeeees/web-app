package com.admin.controller;

import com.admin.dto.UserDto;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    //add service class
    @Autowired
    private UserService userService;


    //registration

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user")UserDto userDto, Model model) {

        return "register"; // name of html page
    }

    @PostMapping ("/registration")
    public String saveUser(@ModelAttribute("user")UserDto userDto, Model model) {
        userService.save(userDto);
        model.addAttribute("message", "Registered succesfuly");
        return "register";
    }




}
