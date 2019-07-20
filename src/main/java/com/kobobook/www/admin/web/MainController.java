package com.kobobook.www.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/admin/dashboard")
    public String home() {
        return "index";
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
