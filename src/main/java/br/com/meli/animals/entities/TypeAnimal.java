package br.com.meli.animals.entities;

import br.com.meli.animals.dto.types.AnimalTypeResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "typeAnimal", cascade = CascadeType.ALL)
    private List<Animal> animals;

    public TypeAnimal(AnimalTypeResponseDTO animalTypeResponseDTO) {
        this.name = animalTypeResponseDTO.name();
    }
}
