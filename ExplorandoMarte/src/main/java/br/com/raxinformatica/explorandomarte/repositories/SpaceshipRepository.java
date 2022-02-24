package br.com.raxinformatica.explorandomarte.repositories;

import br.com.raxinformatica.explorandomarte.models.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceshipRepository extends JpaRepository<Spaceship, Integer> {
}
