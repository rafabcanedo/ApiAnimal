package br.com.meli.animals.controllers;

import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.repositories.AnimalRepository;
import br.com.meli.animals.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/animals/create")
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {
        Animal createdAnimal = service.create(
                animal.getName(), animal.getAge(), animal.getColor()
        );

        return ResponseEntity.ok(createdAnimal);
    }

}
