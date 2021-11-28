
package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Genre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface genreRepository extends JpaRepository<Genre,String>{
    
    @Query("SELECT g FROM Genre g GROUP BY nombre")
    public List<Genre> groupBy ();
}