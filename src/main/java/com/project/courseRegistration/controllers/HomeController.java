package com.project.courseRegistration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/admin")
    public String indexAdmin() {
        return "admin/index";
    }
    @GetMapping("/user")
    public String indexWeb() {
        return "web/index";
    }
}
