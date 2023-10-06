package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImp userServiceImp;

    @Autowired
    public AdminController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/{id}")
    public String user(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServiceImp.findUserById(id));
        return "edit";
    }
    @GetMapping
    public String pageForAdmin(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("users", userServiceImp.getUserList());
        return "admin";
    }

    @PostMapping
    public String create(@ModelAttribute("admin") User user) {
        userServiceImp.createUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("admin") User user) {
        userServiceImp.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping
    public String delete(@ModelAttribute("user") User user) {
        userServiceImp.deleteUser(user.getId());
        return "redirect:/admin";
    }

}
