package br.com.meli.animals.services.interfaces;

import br.com.meli.animals.dto.animals.AnimalAndHabitatDTO;
import br.com.meli.animals.dto.animals.CreateAnimalRequestDTO;
import br.com.meli.animals.dto.animals.CreateAnimalResponseDTO;
import br.com.meli.animals.entities.Animal;

import java.util.List;

public interface IAnimalService {
 AnimalAndHabitatDTO create(CreateAnimalRequestDTO createAnimalRequestDTO, String habitatName, String typeAnimal);
 List<CreateAnimalResponseDTO> getAllAnimals();
 AnimalAndHabitatDTO getById(Integer id);
 AnimalAndHabitatDTO update(Integer id, CreateAnimalRequestDTO createAnimalRequestDTO);
 void deleteAnimal(Integer id);
 List<CreateAnimalResponseDTO> animalToTDO(List<Animal> animals);

 boolean animalExist(String name);
}
