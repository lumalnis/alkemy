package com.alkemy.disney.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class errorController {

    @RequestMapping(value = "/error", method = {RequestMethod.POST})
    public String errorPage(Model model, HttpServletRequest httpServletRequest) {
        String errorMessage = "";
        int errorCode = (int) httpServletRequest.getAttribute("javax.servlet.error.status_code");
        switch (errorCode) {
            case 400:
                errorMessage = "El recurso solicitado no existe";
                break;
            case 401:
                errorMessage = "No se encuentra autorizado";
                break;
            case 403:
                errorMessage = "No tiene permiso para acceder al recurso";
                break;
            case 404:
                errorMessage = "El recurso solicitado no se ha encontrado";
                break;
            case 500:
                errorMessage = "El servidor no pudo realizar la peticion exitosamente";
                break;
            default:
        }
        model.addAttribute("code", errorCode);
        model.addAttribute("message", errorMessage);
        return "error";
    }
}
