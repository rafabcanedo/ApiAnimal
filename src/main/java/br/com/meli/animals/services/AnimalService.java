package br.com.meli.animals.services;

import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.repositories.AnimalRepository;
import br.com.meli.animals.repositories.HabitatRepository;
import br.com.meli.animals.repositories.TypeAnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    // Connect db => Animal repository
    private final AnimalRepository repository;
    private final HabitatRepository habitatRepository;
    private final TypeAnimalRepository typeAnimalRepository;

    public List<Animal> getAllAnimals() {
        //List<Animal> findAnimals = repository.findAll();

        return repository.findAll();
    }

    public Animal create(final String name, final Integer age, final String color, final TypeAnimal typeAnimal, final Habitat habitat){
        Animal animal = new Animal();
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
