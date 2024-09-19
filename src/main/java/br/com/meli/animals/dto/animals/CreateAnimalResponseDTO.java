package br.com.meli.animals.dto.animals;

import br.com.meli.animals.dto.types.AnimalTypeResponseDTO;

public record CreateAnimalResponseDTO(Integer id, String name, Integer age, String color, AnimalTypeResponseDTO animalTypeDTO) {

}
