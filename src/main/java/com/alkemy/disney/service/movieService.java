package com.alkemy.disney.service;

import com.alkemy.disney.entity.Image;
import com.alkemy.disney.entity.Movie;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.repository.imageRepository;
import com.alkemy.disney.repository.movieRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class movieService {

    @Autowired
    private movieRepository movieRepository;

    @Autowired
    private imageService imageService;

    @Autowired
    private imageRepository imageRepository;

    //CRUD 
    @Transactional
    public Movie save(Movie pelicula, MultipartFile image) {

        Movie movie = new Movie();
        movie.setCalificacion(pelicula.getCalificacion());
        movie.setTitulo(pelicula.getTitulo());
        movie.setFechaCreacion(pelicula.getFechaCreacion());
        movie.setGenero(pelicula.getGenero());
        if (image != null) {
            Image img = imageService.save(image);
            movie.setImagen(img);
        }
        return movie;
    }

    @Transactional
    public Movie create(Movie pelicula, MultipartFile image) throws webException {

        Optional<Movie> optional = movieRepository.findById(pelicula.getPelicula_id());
        if (optional.isPresent() || optional != null) {
            throw new webException("Esta pelicula ya existe");
        } else {
            try {
                validate(pelicula);
                return save(pelicula, image);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    //FALTA AGRGAR IMAGEN
    @Transactional
    public Movie modify(Movie pelicula, MultipartFile image) throws webException {

        Optional<Movie> optional = movieRepository.findById(pelicula.getPelicula_id());
        if (optional.isPresent() || optional != null) {
            try {
                validate(pelicula);
                return save(pelicula, image);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            throw new webException("Esta pelicula no existe");
        }
        return null;
    }

    @Transactional
    public void delete(Movie pelicula) {

        Optional<Movie> optional = movieRepository.findById(pelicula.getPelicula_id());

        if (optional.isPresent()) {

            pelicula = optional.get();
            imageRepository.delete(pelicula.getImagen());
            movieRepository.delete(pelicula);
        }
    }

    //VALIDACIONES
    public void validate(Movie pelicula) {

        if (pelicula.getTitulo() == null || pelicula.getTitulo().isEmpty()) {
        }
        if (pelicula.getCalificacion() == null) {
            //throw new webException ()            
        }

        if (pelicula.getFechaCreacion() == null) {
        }
        if (pelicula.getGenero() == null) {
        }
        if (pelicula.getImagen() == null) {
        }
    }

    //LIST
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    public List<Movie> listByTitle(String titulo) {
        return movieRepository.byName("%" + titulo + "%");
    }

    public List<Movie> listByGenre(String genero) {
        return movieRepository.byGenre("%" + genero + "%");
    }

    public List<Movie> listByOrder(String order) {
        return movieRepository.byOrder("%" + order + "%");
    }

}
