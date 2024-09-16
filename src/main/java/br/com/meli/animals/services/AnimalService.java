package br.com.meli.animals.services;

import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.repositories.AnimalRepository;
import br.com.meli.animals.repositories.HabitatRepository;
import br.com.meli.animals.repositories.TypeAnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    // Connect db => Animal repository
    private final AnimalRepository repository;
    private final HabitatRepository habitatRepository;
    private final TypeAnimalRepository tipoAnimalRepository;

    public Animal create(final String name, final Integer age, final String color) {
        Animal animal = new Animal();

        animal.setName(name);
        animal.setAge(age);
        animal.setColor(color);

        return repository.save(animal);
    }

    public Animal create(final String name, final int age, final String color, final TypeAnimal typeAnimal, final Habitat habitat){
        Animal animal = new Animal();
        Optional<Habitat> findHabitat = habitatRepository.findById(habitat.getId());
        Optional<TypeAnimal> findType = tipoAnimalRepository.findById(typeAnimal.getId());

        animal.setName(name);
        animal.setAge(age);
        animal.setColor(color);

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
        repository.deleteById(id);
    }

    public Animal findById(final Integer id) {
        Optional<Animal> animal = repository.findById(id);

        if(animal.isPresent()) {
            return animal.get();
        }

        return null;
    }

    public Habitat getHabitatByAnimalId(Integer id) {
        Optional<Animal> foundAnimal = repository.findById(id);
        return foundAnimal.map(Animal::getHabitatAnimal).orElse(null);
    }
}
