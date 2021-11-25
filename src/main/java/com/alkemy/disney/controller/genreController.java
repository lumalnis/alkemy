package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.service.genreService;
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
@RequestMapping("/genres")
public class genreController {

    @Autowired
    private genreService genreService;

    @GetMapping("")
    public List<Genre> listAll() {
        return genreService.listAll();
    }

    @GetMapping("/id")
    public Optional<Genre> groupBy(Integer id) {
        return genreService.findById(id);
    }

    @GetMapping("/group")
    public List<Genre> groupBy() {
        return genreService.groupBy();
    }
    @PostMapping("/create")
    public Genre create(@RequestBody Genre genero, @RequestParam(required = false) MultipartFile image) throws webException {
        return genreService.create(genero, image);
    }

    @PostMapping("/modify")
    public Genre modify(@RequestBody Genre genero, @RequestParam(required = false) MultipartFile image, @RequestParam(required = false) String image_id) throws webException {
        return genreService.modify(genero, image, image_id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer genero_id) throws Exception {
        genreService.delete(genero_id);
    }
}
