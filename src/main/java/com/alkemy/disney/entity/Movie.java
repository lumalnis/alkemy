package com.alkemy.disney.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "movies")
@Inheritance(strategy = InheritanceType.JOINED)
public class Movie {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer pelicula_id;

    private String titulo;

    private Integer calificacion;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @OneToOne
    private Image imagen;

    @OneToOne
    private Genre genero;

//    @ManyToOne
//    private Character personajesAsociados;
}
