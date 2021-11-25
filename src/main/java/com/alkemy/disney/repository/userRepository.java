package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<Usuario, String> {

    @Query("select u from Usuario u where u.mail = :query")
    public String findByMail(@Param("query") String query);

    @Query("select u from Usuario u where u.mail = :mail")
    public Usuario findByMailUser(@Param("mail") String query);
}
