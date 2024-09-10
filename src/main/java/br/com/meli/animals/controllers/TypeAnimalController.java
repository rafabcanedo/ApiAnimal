package br.com.meli.animals.controllers;

import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.repositories.TypeAnimalRepository;
import br.com.meli.animals.services.TypeAnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TypeAnimalController {

    private final TypeAnimalService service;
    private final TypeAnimalRepository repository;

    @RequestMapping(value = "/typeanimals")
    public ResponseEntity getAllTypeAnimals() {

        var allTypeAnimals = repository.findAll();

        return ResponseEntity.ok(allTypeAnimals);
    }

    @PostMapping(value = "/typeanimals")
    public ResponseEntity<TypeAnimal> create(@RequestBody TypeAnimal typeAnimal) {
        TypeAnimal createdTypeAnimal = service.create(
                typeAnimal.getName()
        );

        return ResponseEntity.ok(createdTypeAnimal);
    }

    @PutMapping(value = "/typeanimals/{id}")
    public ResponseEntity<TypeAnimal> update(@PathVariable Integer id, @RequestBody TypeAnimal typeAnimal) {

        TypeAnimal editedTypeAnimal = service.update(
                typeAnimal.getName(), id
        );

        return ResponseEntity.ok(editedTypeAnimal);
    }

    @DeleteMapping(value = "/typeanimals/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) {

        service.deleteTypeAnimal(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/typeanimals/{id}")
    public ResponseEntity<TypeAnimal> findById(@PathVariable(value = "id") Integer id) {

        TypeAnimal typeAnimal = service.findById(id);

        return ResponseEntity.ok(typeAnimal);
    }

}
