package br.com.meli.animals.dto.types;

import br.com.meli.animals.dto.animals.CreateAnimalResponseDTO;

import java.util.List;

public record TypeAndAnimalsResponseDTO(String name, List<CreateAnimalResponseDTO> animals) {

}
