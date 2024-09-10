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

    @Column(name= "TYPE_ANIMAL_NAME")
    private String name;
}
