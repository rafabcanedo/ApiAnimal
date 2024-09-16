package br.com.meli.animals.controllers;

import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.repositories.AnimalRepository;
import br.com.meli.animals.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
//@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;
    //private final AnimalRepository repository;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    /*@RequestMapping(value = "/animals")
    public ResponseEntity getAllAnimals() {
        
        var allAnimals = AnimalRepository.findAll();

        return ResponseEntity.ok(allAnimals);
    }*/

    @PostMapping(value = "/animals")
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {

        try {
            Animal createdAnimal = animalService.create(
                    animal.getName(), animal.getAge(), animal.getColor(), animal.getTypeAnimal(), animal.getHabitatAnimal()
            );

            return ResponseEntity.status(201).body(createdAnimal);
        } catch(IllegalArgumentException event) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, event.getMessage(), event);
        }
    }

    @PutMapping(value = "/animals/{id}")
    public ResponseEntity<Animal> update(@PathVariable Integer id, @RequestBody Animal animal) {
        Animal editedAnimal = animalService.update(
                animal.getName(), animal.getAge(), animal.getColor(), id
        );

        return ResponseEntity.ok(editedAnimal);
    }

    @DeleteMapping(value = "/animals/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) {

        Optional<Animal> animal = animalService.findById(id);

        if(animal.isPresent()) {
            animalService.deleteAnimal(id);
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/animals/{id}")
    public ResponseEntity<Animal> findById(@PathVariable(value = "id") Integer id) {

        Optional<Animal> findAnimal = animalService.findById(id);

        return findAnimal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("animals/{id}/habitat")
    public ResponseEntity<Habitat> findByHabitatId(@PathVariable Integer id){
        Habitat animalHabitat = animalService.getHabitatByAnimalId(id);
        if(animalHabitat != null){
            return ResponseEntity.ok(animalHabitat);
        }

        return ResponseEntity.notFound().build();
    }

}
