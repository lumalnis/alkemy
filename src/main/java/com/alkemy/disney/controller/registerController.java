package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Usuario;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.service.userService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/register")
public class registerController {

    @Autowired
    private userService userService;

    @GetMapping("")
    public String register() {
        return "/register";
    }

    @PostMapping("")
    public String register(Model model, @ModelAttribute Usuario usuario) throws IOException, webException {
        try {
            userService.create(usuario);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuario", usuario);
            return "/register";
        }
    }
}
