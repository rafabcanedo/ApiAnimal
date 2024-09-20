package br.com.meli.animals.controllers;

import br.com.meli.animals.dto.animals.AnimalAndHabitatDTO;
import br.com.meli.animals.dto.habitat.HabitatAndAnimalsResponseDTO;
import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.services.HabitatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class HabitatController {

    private final HabitatService service;

    public HabitatController(HabitatService service) {
        this.service = service;
    }

    @PostMapping(value = "/habitats")
    public ResponseEntity<Habitat> create(@RequestBody Habitat habitat) {

        Habitat createdHabitat = service.create(
                habitat.getName()
        );

        return ResponseEntity.ok(createdHabitat);
    }

    @DeleteMapping(value = "/habitats/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) {

        service.deleteHabitat(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/habitats/{id}")
    ResponseEntity<HabitatAndAnimalsResponseDTO> findHabitatById(@PathVariable Integer id) {
        HabitatAndAnimalsResponseDTO response = service.findAnimalAndHabitat(id);
        log.info("Habitat found: {}", response);
        return ResponseEntity.ok(response);
    }
}
