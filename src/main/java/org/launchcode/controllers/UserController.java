package org.launchcode.controllers;

import org.springframework.ui.Model;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")

public class UserController {

    @GetMapping("add")
    public String displayAddUserForm() {
        return "user/add";
    }

    @PostMapping("add")
    public String processAddUserForm(@ModelAttribute User user, String verify, Model model) {
        if (user.getPassword() != null && user.getPassword().equals(verify)) {
            // Passwords match, render user/index.html with a welcome message
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail()); // Preserve email
            return "user/index";
        } else {
            // Passwords don't match, render the form again with an error message
            model.addAttribute("error", "Passwords do not match");
            model.addAttribute("username", user.getUsername()); // Preserve username
            model.addAttribute("email", user.getEmail()); // Preserve email
            return "user/add";
        }
    }
}
