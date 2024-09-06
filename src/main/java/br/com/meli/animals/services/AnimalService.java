package br.com.meli.animals.services;

import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    // Connect db => Animal repository
    private final AnimalRepository repository;

    public Animal create(final String name, final Integer age, final String color) {
        Animal animal = new Animal();

        animal.setName(name);
        animal.setAge(age);
        animal.setColor(color);

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


    public void deleteAnimal(Integer id) {
        repository.deleteById(id);
    }

    public Animal findById(Integer id) {
        Optional<Animal> animal = repository.findById(id);

        if(animal.isPresent()) {
            return animal.get();
        }

        return null;
    }

    public List<Animal> findAll() {
        return repository.findAll();
    }
}
