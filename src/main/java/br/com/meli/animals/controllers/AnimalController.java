package br.com.meli.animals.controllers;

import br.com.meli.animals.dto.animals.AnimalAndHabitatDTO;
import br.com.meli.animals.dto.animals.CreateAnimalRequestDTO;
import br.com.meli.animals.dto.animals.CreateAnimalResponseDTO;
import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.services.AnimalService;
import br.com.meli.animals.services.interfaces.IAnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AnimalController {

    private static final Logger log = LoggerFactory.getLogger(AnimalController.class);
    private final IAnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping(value = "/animals")
    public ResponseEntity<List<CreateAnimalResponseDTO>> getAllAnimals() {

        List<CreateAnimalResponseDTO> animals = animalService.getAllAnimals();
        log.info("Find Animals: {}", animals);
        return ResponseEntity.ok(animals);
    }

    @PostMapping(value = "/animals")
    public ResponseEntity<AnimalAndHabitatDTO> create(@RequestBody CreateAnimalRequestDTO animal, @RequestParam String habitatName, @RequestParam String typeAnimal) {

        {
            AnimalAndHabitatDTO createAnimal = animalService.create(animal, habitatName, typeAnimal);
            log.info("Create Animal: {}", createAnimal);
            return ResponseEntity.ok(createAnimal);
        }
    }

    @PutMapping(value = "/animals/{id}")
    public ResponseEntity<AnimalAndHabitatDTO> update(@PathVariable Integer id, @RequestBody CreateAnimalRequestDTO animal) {
        AnimalAndHabitatDTO editedAnimal = animalService.update(
                id, animal
        );

        log.info("Update Animal", editedAnimal);

        return ResponseEntity.ok(editedAnimal);
    }

    @DeleteMapping(value = "/animals/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        animalService.deleteAnimal(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/animals/{id}")
    public ResponseEntity<AnimalAndHabitatDTO> findById(@PathVariable(value = "id") Integer id) {

        AnimalAndHabitatDTO findAnimal = animalService.getById(id);

        return ResponseEntity.ok(findAnimal);
    }

    @GetMapping("animals/{id}/habitat")
    public ResponseEntity<AnimalAndHabitatDTO> findByHabitatId(@PathVariable Integer id){
        Habitat animalHabitat = animalService.(id);
        if(animalHabitat != null){
            return ResponseEntity.ok(animalHabitat);
        }

        return ResponseEntity.notFound().build();
    }

}
