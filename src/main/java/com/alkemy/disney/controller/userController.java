package com.alkemy.disney.controller;

import com.alkemy.disney.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService userService;

//    @GetMapping("/list")
//    public String listUsers(Model model, @RequestParam(required = false) String q) {
//        model.addAttribute("usuarios", userService.listAll());
//        return "userList";
}
