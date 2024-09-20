package br.com.meli.animals.services;

import br.com.meli.animals.dto.animals.AnimalAndHabitatDTO;
import br.com.meli.animals.dto.animals.CreateAnimalResponseDTO;
import br.com.meli.animals.dto.habitat.HabitatAndAnimalsResponseDTO;
import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.repositories.HabitatRepository;
import br.com.meli.animals.services.exceptions.HabitatNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HabitatService {

    private final HabitatRepository repository;
    private final TypeAnimalService animalTypeService;

    public Habitat create(final String name) {
        Habitat habitat = new Habitat();

        habitat.setName(name);

        return repository.save(habitat);
    }

    public Habitat update(final String name, final Integer id) {

        Optional<Habitat> habitat = repository.findById(id);

        if(habitat.isPresent()) {
            habitat.get().setName(name);

            return repository.save(habitat.get());
        }

        return null;
    }


    public void deleteHabitat(Integer id) {
        repository.deleteById(id);
    }

    public Habitat findById(Integer id) {
        Optional<Habitat> habitat = repository.findById(id);

        if(habitat.isPresent()) {
            return habitat.get();
        }

        return null;
    }

    public HabitatAndAnimalsResponseDTO findAnimalAndHabitat(Integer id) {
        Optional<Habitat> habitat = repository.findById(id);

        if (habitat.isPresent()) {
            List<CreateAnimalResponseDTO> animals = getAnimalsDTO(habitat.get());
            HabitatAndAnimalsResponseDTO habitatDTO = new HabitatAndAnimalsResponseDTO(habitat.get().getId(), habitat.get().getName(), animals);
            return habitatDTO;
        }

        log.error("Habitat not found");
        throw new HabitatNotFoundException("Habitat no found");
    }

    private List<CreateAnimalResponseDTO> getAnimalsDTO(Habitat habitat) {
        assert habitat.getAnimals() != null;
        if(habitat.getAnimals().isEmpty()) {
            List<CreateAnimalResponseDTO> animalsDTOList = habitat.getAnimals().stream().map(animal -> new CreateAnimalResponseDTO(animal.getId(), animal.getName(), animal.getAge(), animal.getColor(),
                    animalTypeService.animalTypeToDTO(animal.getTypeAnimal()))
            ).toList();
            return animalsDTOList;
        }

        return null;
    }
}
