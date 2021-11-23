
package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface movieRepository extends JpaRepository<Movie, Integer> {
    
    @Query("SELECT m FROM Movie m WHERE nombre LIKE :query")
    public List<Movie> byName(@Param("query") String query);
    
    @Query("SELECT m FROM Movie m WHERE genero = :query")
    public List<Movie> byGenre(@Param("query") String query);
    
    @Query("SELECT m FROM Movie m ORDER BY :query")
    public List<Movie> byOrder (@Param("query") String query);
}
