package br.com.meli.animals.services;

import br.com.meli.animals.dto.types.AnimalTypeResponseDTO;
import br.com.meli.animals.dto.types.TypeAndAnimalsResponseDTO;
import br.com.meli.animals.entities.TypeAnimal;
import lombok.extern.slf4j.Slf4j;
import br.com.meli.animals.repositories.TypeAnimalRepository;
import br.com.meli.animals.services.exceptions.AnimalTypeNotFoundException;
import br.com.meli.animals.services.interfaces.IAnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TypeAnimalService {

    private final TypeAnimalRepository repository;
    private final IAnimalService animalService;

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

    public TypeAndAnimalsResponseDTO findTypeAnimalById(Integer id){

        Optional<TypeAnimal> typeAnimal = repository.findById(id);

        if(typeAnimal.isPresent()){
            return new TypeAndAnimalsResponseDTO(typeAnimal.get().getName(),
                    animalService.animalToTDO(typeAnimal.get().getAnimals()));
        }

        log.error("Type Animal not found");
        throw new AnimalTypeNotFoundException("Type Animal Not Found");
    }

    public AnimalTypeResponseDTO animalTypeToDTO(final TypeAnimal typeAnimal) {
        return new AnimalTypeResponseDTO(typeAnimal.getId(), typeAnimal.getName());
    }

}
