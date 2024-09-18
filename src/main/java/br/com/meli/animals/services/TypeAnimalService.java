package br.com.meli.animals.services;

import br.com.meli.animals.entities.TypeAnimal;
import br.com.meli.animals.repositories.AnimalRepository;
import br.com.meli.animals.repositories.TypeAnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeAnimalService {

    private final TypeAnimalRepository repository;
    private final AnimalRepository animalRepository;

    public TypeAnimal create(final String name) {
        TypeAnimal typeAnimal = new TypeAnimal();

        typeAnimal.setName(name);

        return repository.save(typeAnimal);
    }

    public TypeAnimal update(final String name, final Integer id) {

        Optional<TypeAnimal> typeAnimal = repository.findById(id);

        if(typeAnimal.isPresent()) {
            typeAnimal.get().setName(name);

            return repository.save(typeAnimal.get());
        }

        return null;
    }


    public void deleteTypeAnimal(final Integer id) {
        repository.deleteById(id);
    }

    public TypeAnimal findById(final Integer id) {
        Optional<TypeAnimal> typeAnimal = repository.findById(id);

        if(typeAnimal.isPresent()) {
            return typeAnimal.get();
        }

        return null;
    }

    public Optional<TypeAnimal> findByTypeId(final Integer id) {
        return repository.findById(id);
    }

    public List<TypeAnimal> findTypesByHabitatId(final Integer id) {
        return repository.findTypesByHabitatId(id);
    }

}
