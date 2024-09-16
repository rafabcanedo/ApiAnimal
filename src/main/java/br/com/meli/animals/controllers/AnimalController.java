package br.com.meli.animals.controllers;

import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.repositories.AnimalRepository;
import br.com.meli.animals.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService service;
    //private final AnimalRepository repository;

    public AnimalController(AnimalService service, AnimalRepository repository) {
        this.service = service;
    }

    /*@RequestMapping(value = "/animals")
    public ResponseEntity getAllAnimals() {
        
        var allAnimals = repository.findAll();

        return ResponseEntity.ok(allAnimals);
    }*/

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

    @GetMapping("animals/{id}/habitat")
    public ResponseEntity<Habitat> findByHabitatId(@PathVariable Integer id){
        Habitat animalHabitat = service.getHabitatByAnimalId(id);
        if(animalHabitat != null){
            return ResponseEntity.ok(animalHabitat);
        }

        return ResponseEntity.notFound().build();
    }

}
