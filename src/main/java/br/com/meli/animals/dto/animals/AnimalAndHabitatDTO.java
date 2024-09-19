package br.com.meli.animals.dto.animals;

import br.com.meli.animals.dto.habitat.HabitatResponseDTO;

public record AnimalAndHabitatDTO(Integer id, String name, Integer age, String color, HabitatResponseDTO habitat) {

}
