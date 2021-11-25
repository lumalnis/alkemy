package com.alkemy.disney.service;

import com.alkemy.disney.entity.Genre;
import com.alkemy.disney.entity.Image;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.repository.genreRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class genreService {

    @Autowired
    private genreRepository genreRepository;

    @Autowired
    private imageService imageService;

    //CRUD 
    @Transactional
    public Genre create(Genre genero, MultipartFile image) throws webException {
        try {
            validate(genero);
            if (image != null) {
                Image img = imageService.save(image);
                genero.setImagen(img);
            }
            return genreRepository.save(genero);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Transactional
    public Genre modify(Genre genero, MultipartFile image, String id_image) throws webException {

        Optional<Genre> optional = genreRepository.findById(genero.getGenero_id());
        if (optional.isPresent() || optional != null) {
            Genre generoModificado = optional.get();
            try {
                validate(genero);
                generoModificado.setNombre(genero.getNombre());
                if (image != null) {
                    genero.setImagen(imageService.update(id_image, image));
                }

                return genreRepository.save(generoModificado);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            throw new webException("Este genero no existe, debe crearlo.");
        }
        return null;
    }

    @Transactional
    public void delete(Integer genero_id) throws Exception {

        try {
            genreRepository.deleteById(genero_id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //VALIDACIONES
    public void validate(Genre genero) throws webException {
        if (genero.getNombre() == null || genero.getNombre().isEmpty()) {
            throw new webException("Agregue un nombre.");
        }
    }

    //LIST
    public List<Genre> listAll() {
        return (List<Genre>) genreRepository.findAll();
    }

    public Optional<Genre> findById(Integer id) {
        return genreRepository.findById(id);
    }

    public List<Genre> groupBy() {
        return genreRepository.groupBy();
    }
}
