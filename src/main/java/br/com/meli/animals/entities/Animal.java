package br.com.meli.animals.entities;

import br.com.meli.animals.dto.animals.CreateAnimalRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    
    @ManyToOne(cascade = CascadeType.ALL)
    //@JsonBackReference
    @JoinColumn(name = "type_animal_id")
    private TypeAnimal typeAnimal;

    @ManyToOne(cascade = CascadeType.ALL)
    //@JsonManagedReference
    @JoinColumn(name = "habitat_id")
    private Habitat habitatAnimal;

    public Animal(String name, Integer age, String color, Habitat habitatAnimal) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.habitatAnimal = habitatAnimal;
    }

    public Animal(CreateAnimalRequestDTO animalDto) {
        this.setName(animalDto.name());
        this.setAge(animalDto.age());
        this.setColor(animalDto.color());
    }

}
