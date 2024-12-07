package com.project.courseRegistration.controllers;

import com.project.courseRegistration.dtos.UserDTO;
import com.project.courseRegistration.models.User;
import com.project.courseRegistration.services.EmailService;
import com.project.courseRegistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @GetMapping("/home")
    public String showHomePage() {
        return "faculty-detail";
    }

    @GetMapping("/test")
    public String showTestPage() {
        return "admin/test";
    }

    @PostMapping("/registration")
    public String createUser(UserDTO userDTO, Model model,
                             BindingResult result) {
        try {
            User user = userService.insertUser(userDTO);
            model.addAttribute("user", user);
            return "result";
        }catch (Exception e) {
            model.addAttribute("errors", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }


    @PostMapping("/forgot-password")
    public String forgotPass(@RequestParam String email ){
        String token = userService.forgotPass(email);
        try {
            emailService.sendHtmlEmail(email,token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "forgot-password";
    }


    @PostMapping("/reset-password")
    public String resetPass(@RequestParam String token, @RequestParam String password){
        userService.resetPass(token,password);
        return "redirect:/login";
    }



}
