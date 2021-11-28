package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Movie;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.service.movieService;
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
@RequestMapping("/movies")
public class movieController {

    @Autowired
    private movieService movieService;

    @GetMapping("")
    public String listarBy(Model model,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String order) {

        if (titulo != null) {
            model.addAttribute("peliculas", movieService.listByTitle(titulo));
        }
        if (genero != null) {
            model.addAttribute("peliculas", movieService.listByGenre(genero));
        }
        if (order != null) {
            model.addAttribute("peliculas", movieService.listByOrder(order));
        } else {
            model.addAttribute("peliculas", movieService.listAll());
        }
        return "movies";
    }
    
        @GetMapping("/query")
    public String listarByQuery(Model model, @RequestParam(required = false) String query) {
        if (query != null) {
            model.addAttribute("peliculaq", movieService.byQuery(query));
        }
        return "characters";
    }

    @GetMapping("/edit")
    public String listEdit(Model model) {
        model.addAttribute("peliculas", movieService.listAll());
        return "moviesEdit";
    }

    @GetMapping("/id")
    public String findById(String id, Model model) {
        model.addAttribute("movie", movieService.findById(id));
        return "";
    }

    @GetMapping("/create")
    public String create(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Movie> optional = movieService.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("movie", optional.get());
            } else {
                return "redirect:/movies/edit";
            }
        } else {
            model.addAttribute("movie", new Movie());
            return "moviesCreate";
        }
        return "moviesCreate";
    }

    @PostMapping("/create")
    public String create(Movie movie,
            @RequestParam(required = false) MultipartFile imagen, RedirectAttributes redirectAttributes,
            Model model) throws webException {
        try {
            movieService.create(movie, imagen);
            redirectAttributes.addFlashAttribute("success", "Genero guardada con exito");
                    return "redirect:/movies/edit";
        } catch (webException ex) {
            ex.printStackTrace();
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("movie", movie);
            return "moviesCreate";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) throws Exception {
        try {
            movieService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Pelicula eliminada con exito");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/movies/edit";
    }
}
