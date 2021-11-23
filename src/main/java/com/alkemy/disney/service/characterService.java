package com.alkemy.disney.service;

import com.alkemy.disney.entity.Image;
import com.alkemy.disney.entity.Character;
import com.alkemy.disney.repository.characterRepository;
import com.alkemy.disney.repository.imageRepository;
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

    //CRUD 
//    @Transactional
//    public Character create(Character personaje, MultipartFile img) {
//
//        //Image image = imageRepository.save(img);
//        personaje.setImage(img);
//        //return characterRepository.save(personaje);
//    }

    @Transactional
    public Character modify(Character personaje, MultipartFile img) {
        return personaje;
    }

    @Transactional
    public void delete(Character personaje) {
    }
}
