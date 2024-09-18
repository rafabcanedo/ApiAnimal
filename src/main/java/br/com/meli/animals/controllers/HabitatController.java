package br.com.meli.animals.controllers;

import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.services.HabitatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequiredArgsConstructor
public class HabitatController {

    private final HabitatService service;
    //private final HabitatRepository habitatRepository;

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
    public ResponseEntity<Habitat> findHabitatById(@PathVariable Integer id) {

       Optional<Habitat> findHabitat = service.findByHabitatId(id);

        return findHabitat.map(habitat -> ResponseEntity.status(200).body(habitat)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
