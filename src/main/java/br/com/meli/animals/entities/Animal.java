package br.com.meli.animals.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity => Representation a table in db
@Entity(name = "ANIMAL")
@Getter
@Setter
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "type_animal_id")
    private String typeAnimalId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_animal_id")
    private TypeAnimal typeAnimal;

    /*@ManyToOne(mappedBy = "habitat", cascade = CascadeType.ALL)
    @JoinColumn(name = "habitat_id")
    private Habitat habitatAnimal;*/
}
