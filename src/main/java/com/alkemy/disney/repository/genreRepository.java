
package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface genreRepository extends JpaRepository<Genre,Integer>{
    
}
