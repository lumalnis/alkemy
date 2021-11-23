package com.alkemy.disney.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "characters")
@Inheritance(strategy = InheritanceType.JOINED)
public class Character {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer personaje_id;

    @OneToOne
    private Image imagen;

    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;
    

    @ManyToOne
    private Movie pelicula;

}
