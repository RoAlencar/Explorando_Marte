package br.com.raxinformatica.explorandomarte.repositories;

import br.com.raxinformatica.explorandomarte.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet,Integer> {
}
