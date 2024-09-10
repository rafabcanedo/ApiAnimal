package br.com.meli.animals.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "HABITAT")
@Getter
@Setter
@NoArgsConstructor
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name= "HABITAT_NAME")
    private String name;

    //@OneToMany(mappedBy = "habitat", fetch = FetchType.EAGER)
    //private List<Animal> animalsHabitat;
}
