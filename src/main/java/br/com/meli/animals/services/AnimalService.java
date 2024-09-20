package br.com.meli.animals.services;

import br.com.meli.animals.dto.animals.AnimalAndHabitatDTO;
import br.com.meli.animals.dto.animals.CreateAnimalRequestDTO;
import br.com.meli.animals.dto.animals.CreateAnimalResponseDTO;
import br.com.meli.animals.dto.habitat.HabitatResponseDTO;
import br.com.meli.animals.dto.types.AnimalTypeResponseDTO;
import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.repositories.AnimalRepository;
import br.com.meli.animals.repositories.HabitatRepository;
import br.com.meli.animals.repositories.TypeAnimalRepository;
import br.com.meli.animals.services.exceptions.AnimalAlreadyExists;
import br.com.meli.animals.services.exceptions.AnimalNotFoundException;
import br.com.meli.animals.services.exceptions.AnimalTypeNotFoundException;
import br.com.meli.animals.services.interfaces.IAnimalService;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService implements IAnimalService {

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

    public List<CreateAnimalResponseDTO> getAllAnimals() {
        List<Animal> findAnimals = repository.findAll();

        List<CreateAnimalResponseDTO> listDTO = findAnimals
                .stream().map(animal -> new CreateAnimalResponseDTO(animal.getId(), animal.getName(), animal.getAge(),
                        animal.getColor(), new AnimalTypeResponseDTO(animal.getTypeAnimal().getId(), animal.getTypeAnimal().getName()))).toList();

        return listDTO;
    }

    @Override
    public AnimalAndHabitatDTO create(CreateAnimalRequestDTO createAnimalRequestDTO, String habitatName, String typeAnimal) {

        boolean animalExists = animalExist(createAnimalRequestDTO.name());

        if(animalExists) {
            log.error("Animal with name {} already exists", createAnimalRequestDTO.name());
            throw new AnimalAlreadyExists("Animal already exists");
        }

        Animal animal = new Animal(createAnimalRequestDTO);

        Optional<Habitat> findHabitat = habitatRepository.findHabitatByName(habitatName);
        Optional<TypeAnimal> findType = typeAnimalRepository.findTypeAnimalByName(typeAnimal);

        if(findHabitat.isPresent() && findType.isPresent()){
            animal.setHabitatAnimal(findHabitat.get());
            animal.setTypeAnimal(findType.get());

            findHabitat.get().getAnimals().add(animal);
            findType.get().getAnimals().add(animal);

            repository.save(animal);
            habitatRepository.save(findHabitat.get());
            typeAnimalRepository.save(findType.get());

            AnimalAndHabitatDTO habitatAndAnimalsResponseDTO = new AnimalAndHabitatDTO(animal.getId(),animal.getName(),
                    animal.getAge(), animal.getColor(), new HabitatResponseDTO(findHabitat.get().getId(),
                    findHabitat.get().getName()));

            return habitatAndAnimalsResponseDTO;
        }
        log.error("Type Animal not found");
        throw new AnimalTypeNotFoundException("Type Animal not found");
    }

    public AnimalAndHabitatDTO update(Integer id, CreateAnimalRequestDTO createAnimalRequestDTO) {

        Optional<Animal> animal = repository.findById(id);

        if(animal.isEmpty()) {
            log.error("Animal not found");
            throw new AnimalNotFoundException("Animal not found");
        }

        Animal updateAnimal = animal.get();

        updateAnimal.setName(createAnimalRequestDTO.name());
        updateAnimal.setAge(createAnimalRequestDTO.age());
        updateAnimal.setColor(createAnimalRequestDTO.color());
        repository.save(updateAnimal);

        AnimalAndHabitatDTO responseAnimalAndHabitatDTO = new AnimalAndHabitatDTO(updateAnimal.getId(), updateAnimal.getName(), updateAnimal.getAge(),
                updateAnimal.getColor(), new HabitatResponseDTO(updateAnimal.getHabitatAnimal().getId(), updateAnimal.getHabitatAnimal().getName()));


        return responseAnimalAndHabitatDTO;
    }

    public void deleteAnimal(final Integer id) {

        Optional<Animal> toDelete = repository.findById(id);

        if(toDelete.isEmpty()) {
            log.error("Animal not found");
            throw new AnimalNotFoundException("Animal not found");
        }

        toDelete.ifPresent(repository::delete);
    }

    public AnimalAndHabitatDTO getById(final Integer id) {

        Optional<Animal> optionalAnimal = repository.findById(id);

        if(optionalAnimal.isEmpty()) {
            log.error("Animal not found");
            throw new AnimalNotFoundException("Animal not found");
        }

        AnimalAndHabitatDTO findAnimal = new AnimalAndHabitatDTO(optionalAnimal.get().getId(), optionalAnimal.get().getName(),
                optionalAnimal.get().getAge(), optionalAnimal.get().getColor(), new HabitatResponseDTO(
                        optionalAnimal.get().getHabitatAnimal().getId(), optionalAnimal.get().getHabitatAnimal().getName()
        ));

        return findAnimal;
    }

    @Override
    public List<CreateAnimalResponseDTO> animalToTDO(List<Animal> animals){
        List<CreateAnimalResponseDTO> listDTO = animals.stream().map(animal -> new CreateAnimalResponseDTO(animal.getId(),
                animal.getName(), animal.getAge(), animal.getColor(),
                new AnimalTypeResponseDTO(animal.getTypeAnimal().getId(), animal.getTypeAnimal().getName()))).toList();
        return listDTO;
    }

    @Override
    public boolean animalExist(final String name) {

        Optional<Animal> optionalAnimal = repository.findByName(name);

        return optionalAnimal.isPresent();
    }
}

    /*public Habitat getHabitatByAnimalId(Integer id) {

        Optional<Animal> foundAnimal = repository.findById(id);
        return foundAnimal.map(Animal::getHabitatAnimal).orElse(null);
    }*/
