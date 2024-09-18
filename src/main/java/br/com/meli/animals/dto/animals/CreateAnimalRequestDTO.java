package br.com.meli.animals.dto.animals;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnimalRequestDTO {
    private String name;
    private Integer age;
    private String color;
    private String typeAnimal;
    private String habitatAnimal;
}
