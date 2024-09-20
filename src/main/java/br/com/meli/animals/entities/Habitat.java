package br.com.meli.animals.entities;

import br.com.meli.animals.dto.habitat.HabitatRequestDTO;
import br.com.meli.animals.dto.habitat.HabitatResponseDTO;
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

    @OneToMany(mappedBy = "habitatAnimal", fetch = FetchType.EAGER)
    private List<Animal> animals;

    public Habitat(String name) {
        this.name = name;
    }

    public Habitat(HabitatRequestDTO habitat){
        this.name = habitat.name();
    }
}
