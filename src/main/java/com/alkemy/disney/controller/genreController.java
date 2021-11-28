package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.service.genreService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/genres")
public class genreController {

    @Autowired
    private genreService genreService;

    @GetMapping("")
    public String listAll(Model model) {
        model.addAttribute("generos", genreService.listAll());
        return "genres";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("generos", genreService.listAll());
        return "genresEdit";
    }

    @GetMapping("/id")
    public String groupBy(String id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        return "";
    }

    @GetMapping("/group")
    public String groupBy(Model model) {
        model.addAttribute("generos", genreService.groupBy());
        return "";
    }

    @GetMapping("/create")
    public String create(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Genre> optional = genreService.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("genre", optional.get());
            } else {
                return "redirect:/genres/genresEdit";
            }
        } else {
            model.addAttribute("genre", new Genre());
        }
        return "genresCreate";
    }

    @PostMapping("/create")
    public String create(Model model, RedirectAttributes redirectAttributes, Genre genre, @RequestParam(required = false) MultipartFile imagen) {
        try {
            genreService.create(genre, imagen);
            redirectAttributes.addFlashAttribute("success", "Genero guardada con exito");
            return "redirect:/genres/edit";
        } catch (webException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            model.addAttribute("genre", genre);
            return "genresCreate";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) throws Exception {
        try {
            genreService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Genero eliminado con exito");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/genres/edit";
    }
}
