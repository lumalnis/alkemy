package com.alkemy.disney.service;

import com.alkemy.disney.entity.Image;
import com.alkemy.disney.exception.webException;
import com.alkemy.disney.repository.imageRepository;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class imageService {

    @Autowired
    private imageRepository imageRepository;

    @Transactional
    public Image save(MultipartFile file) throws IOException{
        if (file != null) {
            try {
                Image img = new Image();
                img.setNombre(file.getName());
                img.setMime(file.getContentType());
                img.setContenido(file.getBytes());
                return imageRepository.save(img);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public Image update(String id, MultipartFile img) throws IOException {

        try {
            Image image = new Image();
            if (id != null) {
                Optional<Image> optional = imageRepository.findById(id);
                if (optional.isPresent()) {
                    image = optional.get();
                }
                image.setMime(img.getContentType());
                image.setNombre(img.getName());
                image.setContenido(img.getBytes());
                return imageRepository.save(image);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    public void delete(Image image) {
        imageRepository.delete(image);
    }
}
