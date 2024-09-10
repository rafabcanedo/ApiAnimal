package br.com.meli.animals.controllers;

import br.com.meli.animals.entities.Animal;
import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.repositories.HabitatRepository;
import br.com.meli.animals.services.HabitatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HabitatController {

    private final HabitatService service;
    private final HabitatRepository habitatRepository;

    @RequestMapping(value = "/habitats")
    public ResponseEntity getAllHabitats() {

        var allHabitats = habitatRepository.findAll();

        return ResponseEntity.ok(allHabitats);
    }

    @PostMapping(value = "/habitats")
    public ResponseEntity<Habitat> create(@RequestBody Habitat habitat) {

        Habitat createdHabitat = service.create(
                habitat.getName()
        );

        return ResponseEntity.ok(createdHabitat);
    }

    @PutMapping(value = "/habitats/{id}")
    public ResponseEntity<Habitat> update(@PathVariable Integer id, @RequestBody Habitat habitat) {
        Habitat editedHabitat = service.update(
                habitat.getName(), id
        );

        return ResponseEntity.ok(editedHabitat);
    }

    @DeleteMapping(value = "/habitats/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {

        service.deleteHabitat(id);

        return ResponseEntity.ok("Animal has been deleted.");
    }

    @GetMapping(value = "/habitats/{id}")
    public ResponseEntity<Habitat> findById(@PathVariable(value = "id") Integer id) {

        Habitat habitat = service.findById(id);

        return ResponseEntity.ok(habitat);
    }
}
