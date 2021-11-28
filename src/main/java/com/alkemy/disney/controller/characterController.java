package com.alkemy.disney.controller;

import com.alkemy.disney.service.characterService;
import com.alkemy.disney.entity.Character;
import com.alkemy.disney.entity.Movie;
import com.alkemy.disney.exception.webException;
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
@RequestMapping("/characters")
public class characterController {

    @Autowired
    private characterService characterService;

    @GetMapping("")
    public String listarAll(Model model, @RequestParam(required = false) String query) {
        model.addAttribute("personajes", characterService.listAll());
        return "characters";

    }

    @GetMapping("/query")
    public String listarByQuery(Model model, @RequestParam(required = false) String query) {
        if (query != null) {
            model.addAttribute("personajesq", characterService.byQuery(query));
        }
        return "characters";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("personajes", characterService.listAll());
        return "charactersEdit";
    }

    @GetMapping("/create")
    public String create(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Character> optional = characterService.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("character", optional.get());
            } else {
                return "redirect:/characters/edit";
            }
        } else {
            model.addAttribute("character", new Character());
        }
        return "charactersCreate";
    }

    @PostMapping("/create")
    public String create(Model model, Character character, @RequestParam(required = false) MultipartFile imagen, RedirectAttributes redirectAttributes) throws webException {
        try {
            characterService.create(character, imagen);
            return "redirect:/characters/edit";
        } catch (webException ex) {
            ex.printStackTrace();
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("character", character);
            return "charactersCreate";
        }
    }

//    @GetMapping("/movies")
//    public String listarByMovies(@RequestParam String id, Model model) {
//      model.addAttribute("moviesC" ,characterService.byMovie(id));
//    return "";
//    }
    @GetMapping("/id")
    public String findById(@RequestParam String id, Model model) {
        model.addAttribute("character", characterService.findById(id));
        return "";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) throws Exception {
        try {
            characterService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Personaje eliminado con exito");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/characters/edit";
    }

}
