package com.alkemy.disney.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth/login")
public class loginController {

    @GetMapping("")
    public String login() {
        return "login.html";
    }

    @PostMapping("")
    public String login(Model model,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String mail,
            @RequestParam(required = false) String logout) {

        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña invalido");
            return "error";
        }
        if (mail != null) {
            model.addAttribute("mail", "Mail incorrecto");
        }
        return "/";
    }
}
