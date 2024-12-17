package com.example.rentalmansystem.controller;

import com.example.rentalmansystem.Entity.User;
import com.example.rentalmansystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerUser(user);
        model.addAttribute("success", "Регистрация прошла успешно! Теперь вы можете войти.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}

