package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

        @GetMapping("/user")
    public String show(Principal principal, Model model) {
        model.addAttribute("user", userServiceImp.findByUsername(principal.getName()));
        return "user";
    }
}
