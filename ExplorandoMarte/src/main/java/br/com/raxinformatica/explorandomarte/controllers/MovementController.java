package br.com.raxinformatica.explorandomarte.controllers;

import br.com.raxinformatica.explorandomarte.models.Spaceship;
import br.com.raxinformatica.explorandomarte.repositories.SpaceshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/movement")
public class MovementController {

    @Autowired
    private SpaceshipRepository repository;

    @PutMapping(value = "/{idSpaceship}/{coordinates}", produces = "application/json")
    @Transactional
    public ResponseEntity moveSpaceship(@PathVariable("idSpaceship") int idSpaceship, @PathVariable ("coordinates") String coordinates){
        Optional<Spaceship> spaceship = repository.findById(idSpaceship);
        List<Spaceship> spaceshipList = repository.findAll();
        if(repository.existsById(idSpaceship)){
            for (char coordinate : coordinates.toCharArray()) {
                switch (coordinate) {
                    case 'L':
                        spaceship.get().turnLeft();
                        this.repository.save(spaceship.get());
                        break;
                    case 'R':
                        spaceship.get().turnRight();
                        this.repository.save(spaceship.get());
                        break;
                    case 'M':
                        spaceship.get().makeMove();
                        for (Spaceship sp : spaceshipList){
                            if(((spaceship.get().getPositionY() == sp.getPositionY()) && (spaceship.get().getPositionX() == sp.getPositionX())) && !sp.equals(spaceship.get())) {
                                throw new RuntimeException("Houston, we hava a problem: there was an error calculating the route, the new spaceship will crash into the spaceship of id %d\nPlease redirect it with a new coordinate!" + sp.getId());
                            }
                        }
                        this.repository.save(spaceship.get());
                        break;
                    default:
                        throw new RuntimeException("Invalid movement, allowed coordinates are: L, R and M.\nplease try again.");
                }
            }
            return ResponseEntity.ok().body(spaceship.get());
        } else{
            return notFound().build();
        }
    }
}
