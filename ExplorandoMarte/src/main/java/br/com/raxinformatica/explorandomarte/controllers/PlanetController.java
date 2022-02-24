package br.com.raxinformatica.explorandomarte.controllers;

import br.com.raxinformatica.explorandomarte.models.Planet;
import br.com.raxinformatica.explorandomarte.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("planet")
public class PlanetController {

    @Autowired
    private PlanetRepository repository;

    @PostMapping(produces ="application/json", consumes ="application/json")
    @Transactional
    public ResponseEntity createPlanet(@RequestBody Planet newPlanet){
        this.repository.save(newPlanet);
        return created(null).build();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity readAllCreatedPlanets() {
        List<Planet> planets = this.repository.findAll();
        if (planets.size() > 0){
            return ok(planets);
        }else{
            return new ResponseEntity("Critical failure: Please insert at least one planet!", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity readOneCreatedPlanet(@PathVariable int id){
        Optional<Planet> queryMap = this.repository.findById(id);
        if(queryMap.isPresent()){
            return ok(queryMap.get());
        }else{
            return new ResponseEntity("Critical failure: There must be at least one planet created", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value ="{id}", produces = "application/json", consumes = "application/json")
    @Transactional
    public ResponseEntity updatePlanet(@RequestBody Planet planet, @PathVariable int id){
        if (this.repository.existsById(id)){
            planet.setId(id);
            this.repository.save(planet);
            return new ResponseEntity("Sucessful: the selected planet was been updated!",HttpStatus.OK);
        } else {
            return new ResponseEntity("Critical failure: Unable to update the selected planet",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity deletePlanet(@PathVariable int id){
        if(this.repository.existsById(id)){
            this.repository.existsById(id);
            return new ResponseEntity("Sucessfull: the selected planet was been deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Critical failure: Unable to delete the selected planet", HttpStatus.NOT_FOUND);
        }
    }



}
