package com.mohtashim.sami.lawEntry.controller;

import com.mohtashim.sami.lawEntry.model.User;
import com.mohtashim.sami.lawEntry.repository.UserRepository;
import com.mohtashim.sami.lawEntry.service.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private UserDetailsServiceImplement userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Principal principal, Model model) {
        if (principal != null) return "redirect:/";
        model.addAttribute("title", "Law Entry Login");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Law Entry - Registration");
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("user")User user,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes attributes) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("user", user);
                return "register";
            }

            User userNew = userDetailsService.getByUserEmail(user.getUsername());

            if (userNew != null) {
                model.addAttribute("error", "Email is already registered");
                model.addAttribute("user", user);
                return "register";
            }

            if (user.getPassword().length() >= 5 && user.getPassword().length() <= 20) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                attributes.addFlashAttribute("success", user.getName() + " is successfully registered.");

                return "redirect:/login";
            } else {
                model.addAttribute("error", "Password should have 5-20 characters");
                model.addAttribute("user", user);
            }
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "Server have ran some problems");
            model.addAttribute("user", user);
        }
        return "register";
    }
}
