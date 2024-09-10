package br.com.meli.animals.repositories;

import br.com.meli.animals.entities.TypeAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAnimalRepository extends JpaRepository<TypeAnimal, Integer> {

}
