package br.com.meli.animals.dto.habitat;

import br.com.meli.animals.dto.animals.CreateAnimalResponseDTO;

import java.util.List;

public class HabitatAndAnimalsResponseDTO {
    private Integer id;
    private String name;
    List<CreateAnimalResponseDTO> habitats;
}
