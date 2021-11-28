package com.alkemy.disney.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "characters")
@Inheritance(strategy = InheritanceType.JOINED)
public class Character implements Serializable {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Id
    private String id;

    @OneToOne
    private Image imagen;

    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;

    @ManyToOne
    private Movie pelicula;

}
