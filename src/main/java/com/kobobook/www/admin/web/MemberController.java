package com.kobobook.www.admin.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@Slf4j
public class MemberController {

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "member/login";
    }

    @RequestMapping("/login-auth-error")
    public String loginAuthError(Model model) {
        model.addAttribute("loginAuthError", true);
        return "member/login";
    }

}
