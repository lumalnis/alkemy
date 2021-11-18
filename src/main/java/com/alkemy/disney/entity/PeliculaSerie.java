package com.alkemy.disney.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "peliculaSerie")
@Inheritance(strategy = InheritanceType.JOINED)
public class PeliculaSerie {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer peliculaSerie_id;

    private String titulo;

    private Integer calificacion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @OneToOne
    private Imagen imagen;

    @ManyToOne
    private Personaje personajesAsociados;

}
