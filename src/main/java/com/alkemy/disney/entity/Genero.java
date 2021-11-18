
package com.alkemy.disney.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "genero")
//@Inheritance(strategy = InheritanceType.JOINED)

public class Genero {
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer genero_id;
            
    private String nombre;
    
    @OneToOne
    private Imagen imagen;
    
    
}
