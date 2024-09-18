package br.com.meli.animals.services;

import br.com.meli.animals.entities.Habitat;
import br.com.meli.animals.repositories.HabitatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabitatService {

    private final HabitatRepository repository;

    public Habitat create(final String name) {
        Habitat habitat = new Habitat();

        habitat.setName(name);

        return repository.save(habitat);
    }

    public Habitat update(final String name, final Integer id) {

        Optional<Habitat> habitat = repository.findById(id);

        if(habitat.isPresent()) {
            habitat.get().setName(name);

            return repository.save(habitat.get());
        }

        return null;
    }


    public void deleteHabitat(Integer id) {
        repository.deleteById(id);
    }

    public Habitat findById(Integer id) {
        Optional<Habitat> habitat = repository.findById(id);

        if(habitat.isPresent()) {
            return habitat.get();
        }

        return null;
    }

    public Optional<Habitat> findByHabitatId(final Integer id){

        return repository.findById(id);

    }
}
