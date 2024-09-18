package br.com.meli.animals.controllers;

import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.services.TypeAnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RequiredArgsConstructor
public class TypeAnimalController {

    private final TypeAnimalService service;
    //private final TypeAnimalRepository repository;

    public TypeAnimalController(TypeAnimalService typeAnimalService) {
        this.service = typeAnimalService;
    }

    /*@RequestMapping(value = "/typeanimals")
    public ResponseEntity getAllTypeAnimals() {

        var allTypeAnimals = repository.findAll();

        return ResponseEntity.ok(allTypeAnimals);
    }*/

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
    public ResponseEntity<List<TypeAnimal>> getTypesByHabitatId(@PathVariable Integer id){
        List<TypeAnimal> typesByHabitat = service.findTypesByHabitatId(id);
        if(typesByHabitat.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(typesByHabitat);
    }

}
