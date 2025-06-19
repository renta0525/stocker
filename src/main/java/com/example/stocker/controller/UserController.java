package com.example.stocker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/toLogin")
public class UserController {
    
    /**
     * ログイン画面表示
     */
    @GetMapping("")
    public String index () {
        return "login/login";
    }

    /**
     * ログイン後の遷移
     */
    @GetMapping("/login")
    public String shops () {
        return "redirect:/list/shops";
    }
}
