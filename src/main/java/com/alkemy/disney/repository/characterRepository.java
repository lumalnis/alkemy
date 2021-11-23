package com.alkemy.disney.repository;

import com.alkemy.disney.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface characterRepository extends JpaRepository<Character, Integer> {

}
