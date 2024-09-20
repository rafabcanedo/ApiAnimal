package br.com.meli.animals.entities;

import br.com.meli.animals.dto.animals.CreateAnimalRequestDTO;
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
    @JoinColumn(name = "type_animal_id")
    private TypeAnimal typeAnimal;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Habitat getHabitatAnimal() {
        return habitatAnimal;
    }

    public TypeAnimal getTypeAnimal() {
        return typeAnimal;
    }

}
