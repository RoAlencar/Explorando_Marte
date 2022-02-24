package br.com.raxinformatica.explorandomarte.repositories;

import br.com.raxinformatica.explorandomarte.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet,Integer> {
}
