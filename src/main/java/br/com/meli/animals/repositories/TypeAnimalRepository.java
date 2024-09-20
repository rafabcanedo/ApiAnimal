package br.com.meli.animals.repositories;

import br.com.meli.animals.entities.TypeAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeAnimalRepository extends JpaRepository<TypeAnimal, Integer> {
    // @Query(value = "SELECT DISTINCT ta.* FROM tipo_animal ta JOIN animal a ON ta.id = a.tipo_id WHERE a.habitat_id = :habitat_id", nativeQuery = true)
    //List<TypeAnimal> findTypesByHabitatId(@Param("habitat_id") Integer habitatId);
    Optional<TypeAnimal> findTypeAnimalByName(String typeAnimalName);
}
