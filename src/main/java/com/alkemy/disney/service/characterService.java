package com.alkemy.disney.service;

import com.alkemy.disney.entity.Image;
import com.alkemy.disney.entity.Character;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.repository.characterRepository;
import com.alkemy.disney.repository.imageRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class characterService {

    @Autowired
    private characterRepository characterRepository;

    @Autowired
    private imageRepository imageRepository;

    @Autowired
    private imageService imageService;

    //CRUD 
    @Transactional
    public Character create(Character personaje, MultipartFile image) throws webException {

        try {
            validate(personaje);
            if (image != null) {
                Image img = imageService.save(image);
                personaje.setImagen(img);
            }
            return characterRepository.save(personaje);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    public Character modify(Character personaje, MultipartFile image, String id_image) throws webException {

        Optional<Character> optional = characterRepository.findById(personaje.getPersonaje_id());
        if (optional.isPresent() || optional != null) {
            Character personajeModificado = optional.get();
            try {
                validate(personaje);
                personajeModificado.setNombre(personaje.getNombre());
                personajeModificado.setEdad(personaje.getEdad());
                personajeModificado.setHistoria(personaje.getHistoria());
                personajeModificado.setPeso(personaje.getPeso());
                personajeModificado.setPelicula(personaje.getPelicula());
                if (image != null) {
                    personaje.setImagen(imageService.update(id_image, image));
                }
                return characterRepository.save(personajeModificado);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            throw new webException("Este personaje no existe");
        }

        return null;
    }

    @Transactional
    public void delete(Integer personaje_id) {
        try {
            characterRepository.deleteById(personaje_id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //VALIDACIONES
    public void validate(Character personaje) throws webException {
        if (personaje.getNombre() == null || personaje.getNombre().isEmpty()) {
            throw new webException("Necesito un nombre.");
        }
        if (personaje.getHistoria() == null || personaje.getHistoria().isEmpty()) {
            throw new webException("Necesito una hixstoria.");
        }
    }

    //LIST
    public List<Character> listAll() {
        return characterRepository.findAll();
    }

    public Optional<Character> findById(Integer id) {
        return characterRepository.findById(id);
    }

    public List<Character> byQuery(String query) {
        return characterRepository.byQuery("%" + query + "%");
    }

    public List<Character> byMovie(String id_movie) {
        return characterRepository.allMovies(id_movie);
    }

//    public List<Character> byName(String name) {
//        return characterRepository.byName(name);
//    }
//
//    public List<Character> byEdad(String age) {
//        return characterRepository.byAge(age);
//    }
//
//    public List<Character> byWeight(String weight) {
//        return characterRepository.byWeight(weight);
//    }
}
