package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Character;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface characterRepository extends JpaRepository<Character, Integer> {

//    @Query("SELECT c FROM Character c WHERE nombre LIKE :query")
//    public List<Character> byName(@Param("query") String query);
//
//    @Query("SELECT c FROM Character c WHERE edad = :query")
//    public List<Character> byAge(@Param("query") String query);
//
//    @Query("SELECT c FROM Character c WHERE peso = :query")
//    public List<Character> byWeight(@Param("query") String query);
    
    @Query("SELECT c FROM Character c WHERE"
            + " nombre LIKE :query"
            + " or edad = :query"
            + " or peso = :query")
     public List<Character> byQuery(@Param("query") String query);

    @Query("SELECT m FROM Character c, Movie m WHERE"
            + " c.personaje_id LIKE :query")
    public List<Character> allMovies(@Param("query") String query);
    //VER BIEN ESTO SI FUNCIONA
}

/*Deberá permitir buscar por nombre, y filtrar por edad, 
peso o películas/series en las que participó.
Para especificar el término de búsqueda o filtros se 
deberán enviar como parámetros de query:
● GET /characters?name=nombre
● GET /characters?age=edad
● GET /characters?movies=idMovie*/
