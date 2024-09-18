package br.com.meli.animals.dto.habitat;

import br.com.meli.animals.dto.animals.CreateAnimalResponseDTO;

import java.util.List;

public record HabitatAndAnimalsResponseDTO(Integer id, String name, List<CreateAnimalResponseDTO> habitatAnimals) {

}
