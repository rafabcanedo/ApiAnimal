package br.com.meli.animals.services;

import br.com.meli.animals.dto.animals.CreateAnimalRequestDTO;
import br.com.meli.animals.dto.animals.CreateAnimalResponseDTO;
import br.com.meli.animals.dto.habitat.HabitatAndAnimalsResponseDTO;
import br.com.meli.animals.dto.types.AnimalTypeResponseDTO;
import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.repositories.AnimalRepository;
import br.com.meli.animals.repositories.HabitatRepository;
import br.com.meli.animals.repositories.TypeAnimalRepository;
import br.com.meli.animals.services.exceptions.AnimalAlreadyExists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private static final Logger log = LoggerFactory.getLogger(AnimalService.class);
    // Connect db => Animal repository
    private final AnimalRepository repository;
    private final HabitatRepository habitatRepository;
    private final TypeAnimalRepository typeAnimalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository, HabitatRepository habitatRepository, TypeAnimalRepository typeAnimalRepository) {
        this.repository = animalRepository;
        this.habitatRepository = habitatRepository;
        this.typeAnimalRepository = typeAnimalRepository;
    }

    //@Override
    public List<CreateAnimalResponseDTO> getAllAnimals() {
        List<Animal> findAnimals = repository.findAll();

        List<CreateAnimalResponseDTO> listDTO = findAnimals
                .stream().map(animal -> new CreateAnimalResponseDTO(animal.getId(), animal.getName(), animal.getAge(),
                        animal.getColor(), new AnimalTypeResponseDTO(animal.getTypeAnimal().getId(), animal.getTypeAnimal().getName()))).toList();

        return listDTO;
    }

    public HabitatAndAnimalsResponseDTO create(CreateAnimalRequestDTO createAnimalRequestDTO, String habitatName, String typeAnimal) {

        boolean animalExists = animalExist(createAnimalRequestDTO.name());

        if(animalExists) {
            log.error("Animal with name {} already exists", createAnimalRequestDTO.name());
            throw new AnimalAlreadyExists("Animal already exists");
        }

        Animal animal = new Animal(createAnimalRequestDTO);

        Optional<Habitat> findHabitat = habitatRepository.findById(habitat.getId());
        Optional<TypeAnimal> findType = typeAnimalRepository.findById(typeAnimal.getId());

        animal.setName(name);
        animal.setAge(age);
        animal.setColor(color);
        animal.setTypeAnimal(typeAnimal);
        animal.setHabitatAnimal(habitat);

        findHabitat.ifPresent(animal::setHabitatAnimal);
        findType.ifPresent(animal::setTypeAnimal);

        return repository.save(animal);
    }

    public Animal update(final String name, final Integer age, final String color, final Integer id) {

        Optional<Animal> animal = repository.findById(id);

        if(animal.isPresent()) {
            animal.get().setName(name);
            animal.get().setAge(age);
            animal.get().setColor(color);

            return repository.save(animal.get());
        }

        return null;
    }


    public void deleteAnimal(final Integer id) {
        Optional<Animal> toDelete = repository.findById(id);
        toDelete.ifPresent(repository::delete);
    }

    public Optional<Animal> findById(final Integer id) {

        return repository.findById(id);
    }

    public Habitat getHabitatByAnimalId(Integer id) {
        Optional<Animal> foundAnimal = repository.findById(id);
        return foundAnimal.map(Animal::getHabitatAnimal).orElse(null);
    }
}
