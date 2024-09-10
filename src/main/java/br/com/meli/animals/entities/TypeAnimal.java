package br.com.meli.animals.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "TYPE_ANIMAL")
@Getter
@Setter
@NoArgsConstructor
public class TypeAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name= "CARNIVORO")
    private String carnivoro;

    @Column(name= "HERBIVORO")
    private String herbivoro;

    @Column(name= "ONIVORO")
    private String onivoro;

    @Column(name= "TERRESTRE")
    private String terrestre;

    @Column(name= "AQUATICO")
    private String aquatico;

    @Column(name= "AEREO")
    private String aereo;

    @Column(name= "MAMIFERO")
    private String mamifero;

    @Column(name= "ANFIBIO")
    private String anfibio;

    @Column(name= "REPTIL")
    private String reptil;

    @Column(name= "AVE")
    private String ave;
}
