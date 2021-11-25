package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Movie;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.service.movieService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/movies")
public class movieController {

    @Autowired
    private movieService movieService;

    @GetMapping("")
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

    @GetMapping("/id")
    public Optional<Movie> findById(Integer id) {
        return movieService.findById(id);
    }

    @PostMapping("/create")
    public Movie create(@RequestBody Movie pelicula, @RequestParam(required = false) MultipartFile image) throws webException {
        return movieService.create(pelicula, image);
    }

    @PostMapping("/modify")
    public Movie modify(@RequestBody Movie pelicula, @RequestParam(required = false) MultipartFile image, @RequestParam(required = false) String image_id) throws webException {
        return movieService.modify(pelicula, image, image_id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer pelicula_id) throws Exception {
        movieService.delete(pelicula_id);
    }
}
