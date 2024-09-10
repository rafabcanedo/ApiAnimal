package br.com.meli.animals.repositories;

import br.com.meli.animals.entities.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitatRepository  extends JpaRepository<Habitat, Integer> {

}
