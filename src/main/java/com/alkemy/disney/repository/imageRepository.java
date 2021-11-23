
package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface imageRepository extends JpaRepository<Image, String> {
    
}
