package com.alkemy.disney.controller;

import com.alkemy.disney.service.characterService;
import com.alkemy.disney.entity.Character;
import com.alkemy.disney.entity.Movie;
import com.alkemy.disney.exception.webException;
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
@RequestMapping("/characters")
public class characterController {

    @Autowired
    private characterService characterService;

    @GetMapping("")
    public List<Character> listarByQuery(@RequestParam (required = false) String query) {
        if (query != null) {
            return characterService.byQuery(query);
        }else{
        return characterService.listAll();
    }}

    @GetMapping("/movies")
    public List<Character> listarByMovies(@RequestParam String id) {
        return characterService.byMovie(id);
    }

    @GetMapping("/id")
    public Optional<Character> findById(@RequestParam Integer id) {
        return characterService.findById(id);
    }

    @PostMapping("/create")
    public Character create(@RequestBody Character personaje, @RequestParam(required = false) MultipartFile image) throws webException {
        return characterService.create(personaje, image);
    }

    @PostMapping("/modify")
    public Character modify(@RequestBody Character personaje, @RequestParam(required = false) MultipartFile image, @RequestParam(required = false) String image_id) throws webException {
        return characterService.modify(personaje, image, image_id);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer personaje_id) throws Exception {
        characterService.delete(personaje_id);
    }

}
