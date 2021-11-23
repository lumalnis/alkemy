package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Movie;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.service.movieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/movies")
public class movieController {

    @Autowired
    private movieService movieService;

    @GetMapping("/")
    public List<Movie> listarBy(@RequestParam(required = false) String titulo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String order) {

        if (titulo != null) {
            return movieService.listByTitle(titulo);
        }
        if (genero != null) {
            return movieService.listByGenre(genero);
        }
        if (order != null) {
            return movieService.listByOrder(order);
        } else {
            return movieService.listAll();
        }
    }

    @PostMapping("/create")
    public Movie create(@RequestBody Movie pelicula, @RequestParam(required = false) MultipartFile image) throws webException {
        return movieService.create(pelicula, image);
    }

    @PostMapping("/modify")
    public Movie modify(@RequestBody Movie pelicula, @RequestParam(required = false) MultipartFile image) throws webException {
        return movieService.modify(pelicula, image);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Movie pelicula) {
        movieService.delete(pelicula);
    }
}
