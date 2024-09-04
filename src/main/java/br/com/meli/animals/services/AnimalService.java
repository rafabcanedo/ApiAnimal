package br.com.meli.animals.services;

import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {

    // Connect db => Animal repository
    private final AnimalRepository repository;

    // Constructor (1 of 2 suggestion)
    // Pq usar essa maneira?
    /*AnimalService(AnimalRepository animalRepository) {
        this.repository = animalRepository;
    }*/

    // Final => for not alter dates during the execution ??
    public Animal create(final String name, final Integer age, final String color) {
        Animal animal = new Animal();

        // Getters and Satters
        // Getters ???
        animal.setName(name);
        animal.setAge(age);
        animal.setColor(color);

        return repository.save(animal);
    }

    //public Animal findAll()
    public Animal deleteAnimal(final Integer id ) {
        return repository.findById(id).orElse(null);
    }
}
