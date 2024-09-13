package br.com.meli.animals.controllers;

import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.repositories.AnimalRepository;
import br.com.meli.animals.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService service;
    private final AnimalRepository animalRepository;

    @RequestMapping(value = "/animals")
    public ResponseEntity getAllAnimals() {
        
        var allAnimals = animalRepository.findAll();

        return ResponseEntity.ok(allAnimals);
    }

    @PostMapping(value = "/animals")
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {
        Animal createdAnimal = service.create(
                animal.getName(), animal.getAge(), animal.getColor()
        );

        return ResponseEntity.ok(createdAnimal);
    }

    @PutMapping(value = "/animals/{id}")
    public ResponseEntity<Animal> update(@PathVariable Integer id, @RequestBody Animal animal) {
        Animal editedAnimal = service.update(
                animal.getName(), animal.getAge(), animal.getColor(), id
        );

        return ResponseEntity.ok(editedAnimal);
    }

    @DeleteMapping(value = "/animals/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) {

        service.deleteAnimal(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/animals/{id}")
    public ResponseEntity<Animal> findById(@PathVariable(value = "id") Integer id) {

        Animal animal = service.findById(id);

        return ResponseEntity.ok(animal);
    }

    /*@RequestMapping(value = "animals/{type_animal_id}")
    public ResponseEntity getAllAnimalsByType() {
        @Query(value = "SELECT type_animal_id FROM animal", nativeQuery = true)
        List<TypeAnimal> animals;
    }*/

}
