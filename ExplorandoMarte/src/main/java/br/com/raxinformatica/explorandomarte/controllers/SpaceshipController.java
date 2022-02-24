package br.com.raxinformatica.explorandomarte.controllers;

import br.com.raxinformatica.explorandomarte.models.Planet;
import br.com.raxinformatica.explorandomarte.models.Spaceship;
import br.com.raxinformatica.explorandomarte.repositories.PlanetRepository;
import br.com.raxinformatica.explorandomarte.repositories.SpaceshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spaceship")
public class SpaceshipController {

    @Autowired
    private SpaceshipRepository repository;

    @Autowired
    private PlanetRepository planetRepository;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Transactional
    public ResponseEntity crateSpaceship(@RequestBody Spaceship newSpaceship){
        Optional<Planet> planet = this.planetRepository.findById(newSpaceship.getPlanet().getId());
        if((newSpaceship.getPositionX() > planet.get().getX() || newSpaceship.getPositionX() < 0)
          || (newSpaceship.getPositionY() > planet.get().getY() || newSpaceship.getPositionY() < 0)) {
            return new ResponseEntity("Houston, we have a problem: the spaceship is positioned outside the limits of the planet! ", HttpStatus.BAD_REQUEST);
        } else {
            List<Spaceship> spaceships = this.repository.findAll();
            for (Spaceship sp : spaceships) {
                if((sp.getPositionX() == newSpaceship.getPositionX())
                    && (sp.getPositionY() == newSpaceship.getPositionY())){
                    return new ResponseEntity("Houston, we hava a problem: there was an error calculating the route, the new spaceship will crash into the spaceship of id %d\nPlease redirect it with a new coordinate!"+ sp.getId(),HttpStatus.BAD_REQUEST);
                }
            }
            this.repository.save(newSpaceship);
            return new ResponseEntity("Success: we can launch the new space immediately.\n That's one small step for man, one giant leap for mankind",HttpStatus.CREATED);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity readAllSpaceships(){
        List<Spaceship> spaceships = this.repository.findAll();
        if(spaceships.size() > 0) {
            return ResponseEntity.ok(spaceships);
        } else {
            return new ResponseEntity("Central command: No spaceships have been launched, the mission has not started yet, please wait for the ships to be released.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity readOneSpaceship(@PathVariable int id){
        Optional<Spaceship> querySpaceship = this.repository.findById(id);

        if(querySpaceship.isPresent()){
            return new ResponseEntity("Success: Houston, we were able to locate the selected spacecraft", HttpStatus.OK);
        } else {
            return new ResponseEntity("Command center: we were unable to contact the selected spacecraft, please verify that the identification number is correct.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value ="/{id}", produces = "application/json")
    @Transactional
    public ResponseEntity deleteSpaceship(@PathVariable int id){
        if(this.repository.existsById(id)){
            this.repository.existsById(id);
            return new ResponseEntity("Success: Command center, selected ship is returning home.\n" +
                    "Mission complete, my comrades",HttpStatus.OK);
        } else {
             return new ResponseEntity("Houston, can't find the selected ship, please verify that the identification number is correct.",HttpStatus.NOT_FOUND);
        }
    }


}
