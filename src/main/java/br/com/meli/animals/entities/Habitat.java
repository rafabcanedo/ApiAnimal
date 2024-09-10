package br.com.meli.animals.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "HABITAT")
@Getter
@Setter
@NoArgsConstructor
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name= "PANTANAL")
    private String pantanal;

    @Column(name= "DESERTO")
    private String deserto;

    @Column(name= "ANTARTIDA")
    private String antartida;

    @Column(name= "SAVANA")
    private String savana;

    @Column(name= "TROPICAL")
    private String tropical;
}
