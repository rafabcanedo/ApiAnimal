package br.com.meli.animals.controllers;

import br.com.meli.animals.dto.types.TypeAndAnimalsResponseDTO;
import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.services.TypeAnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeAnimalController {

    private static final Logger log = LoggerFactory.getLogger(TypeAnimalController.class);
    private final TypeAnimalService service;
    //private final TypeAnimalRepository repository;

    public TypeAnimalController(TypeAnimalService typeAnimalService) {
        this.service = typeAnimalService;
    }

    @PostMapping(value = "/typeanimals")
    public ResponseEntity<TypeAnimal> create(@RequestBody TypeAnimal typeAnimal) {
        TypeAnimal createdTypeAnimal = service.create(
                typeAnimal.getName()
        );

        return ResponseEntity.ok(createdTypeAnimal);
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

    @GetMapping("/habitat-id/{id}")
    public ResponseEntity<TypeAndAnimalsResponseDTO> getTypeAnimalById (@PathVariable Integer id) {
        TypeAndAnimalsResponseDTO typeAndAnimalsResponseDTO = service.findTypeAnimalById(id);
        log.info("TypeAnimals: {}", typeAndAnimalsResponseDTO);
        return ResponseEntity.ok(typeAndAnimalsResponseDTO);
    }

}
