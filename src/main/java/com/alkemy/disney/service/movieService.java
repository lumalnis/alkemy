package com.alkemy.disney.service;

import com.alkemy.disney.entity.Image;
import com.alkemy.disney.entity.Movie;
import com.alkemy.disney.exception.webException;
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

    //CRUD 
    @Transactional
    public Movie create(Movie pelicula, MultipartFile image) throws webException {
        try {
            validate(pelicula);
            if (image != null) {
                Image img = imageService.save(image);
                pelicula.setImagen(img);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
        return movieRepository.save(pelicula);
    }

    @Transactional
    public Movie modify(Movie pelicula, MultipartFile image, String id_image) throws webException {

        Optional<Movie> optional = movieRepository.findById(pelicula.getId());
        if (optional.isPresent() || optional != null) {
            Movie peliculaModificada = optional.get();
            try {
                validate(pelicula);
                peliculaModificada.setTitulo(pelicula.getTitulo());
                peliculaModificada.setCalificacion(pelicula.getCalificacion());
                peliculaModificada.setFechaCreacion(pelicula.getFechaCreacion());
                peliculaModificada.setGenero(pelicula.getGenero());
                if (image != null) {
                    pelicula.setImagen(imageService.update(id_image, image));
                }
                return movieRepository.save(peliculaModificada);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            throw new webException("Esta pelicula no existe");
        }
        return null;
    }

    @Transactional
    public void delete(String id) throws Exception {

        try {
            movieRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //VALIDACIONES
    public void validate(Movie pelicula) throws webException {

        if (pelicula.getTitulo() == null || pelicula.getTitulo().isEmpty()) {
            throw new webException("Agregue un título.");
        }
        if (pelicula.getCalificacion() == null) {
            throw new webException("Elija una calificacion.");
        }

        if (pelicula.getFechaCreacion() == null) {
            throw new webException("Elija una fecha.");
        }
        if (pelicula.getGenero() == null) {
            throw new webException("Elija un genero.");
        }
    }

    //LIST
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(String id) {
        return movieRepository.findById(id);
    }

    public List<Movie> listByTitle(String titulo) {
        return movieRepository.byName("%" + titulo + "%");
    }

    public List<Movie> listByGenre(String genero) {
        return movieRepository.byGenre("%" + genero + "%");
    }

    public List<Movie> listByOrder(String order) {
        return movieRepository.byOrder(order.toUpperCase());
    }
        public List<Movie> byQuery(String query) {
        return movieRepository.byQuery("%" + query + "%");
    }

}
