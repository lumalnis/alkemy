package com.alkemy.disney.controller;

import com.alkemy.disney.entity.Movie;
import com.alkemy.disney.entity.Character;
import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.service.characterService;
import com.alkemy.disney.service.genreService;
import com.alkemy.disney.service.movieService;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/images")
public class imageController {

    @Autowired
    private movieService movieService;

    @Autowired
    private characterService characterService;
    
    @Autowired
    private genreService genreService;

    @GetMapping("/movies")
    public ResponseEntity<byte[]> imgMovie(@RequestParam String id)
            throws webException {
        try {
            Optional<Movie> optional = movieService.findById(id);
            if (optional.isPresent()) {
                if (optional.get().getImagen() == null) {
                    throw new webException("La pelicula no tiene una imagen asignada.");
                }
            }
            byte[] imagen = optional.get().getImagen().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(imageController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/characters")
    public ResponseEntity<byte[]> imgCharacter(@RequestParam String id)
            throws webException {
        try {
            Optional<Character> optional = characterService.findById(id);
            if (optional.isPresent()) {
                if (optional.get().getImagen() == null) {
                    throw new webException("El personaje no tiene una imagen asignada.");
                }
            }
            byte[] imagen = optional.get().getImagen().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imagen, headers, HttpStatus.OK);

        } catch (Exception e) {
            Logger.getLogger(imageController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/genres")
    public ResponseEntity<byte[]> imgGenre(@RequestParam String id)
            throws webException {
        try {
            Optional<Genre> optional = genreService.findById(id);
            if (optional.isPresent()) {
                if (optional.get().getImagen() == null) {
                    throw new webException("El genero no tiene una imagen asignada.");
                }
            }
            byte[] imagen = optional.get().getImagen().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(imageController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
