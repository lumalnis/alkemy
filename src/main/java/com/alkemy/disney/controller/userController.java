package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Usuario;
import com.alkemy.disney.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class userController {

    @Autowired
    private userService userService;

    @GetMapping("/login")
    public String login(Model model,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String mail,
            @RequestParam(required = false) String logout) {

        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña invalido");
            return "error";
        }
        if (mail != null) {
            model.addAttribute("mail", mail);
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public Usuario registro(@RequestParam String mail,
            @RequestParam String password, @RequestParam String password2) throws Exception {
        return userService.create(mail, password, password2);
    }
}
