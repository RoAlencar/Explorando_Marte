package br.com.raxinformatica.explorandomarte.repositories;

import br.com.raxinformatica.explorandomarte.model.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceshipRepository extends JpaRepository<Spaceship, Integer> {
}
