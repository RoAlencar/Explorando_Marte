package br.com.raxinformatica.explorandomarte.controllers;

import br.com.raxinformatica.explorandomarte.models.SpaceProbe;
import br.com.raxinformatica.explorandomarte.repositories.SpaceProbeRepository;
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
    private SpaceProbeRepository repository;

    @PutMapping(value = "/{idSpaceprobe}/{coordinates}", produces = "application/json")
    @Transactional
    public ResponseEntity moveSpaceship(@PathVariable("idSpaceprobe") int idSpaceprobe, @PathVariable ("coordinates") String coordinates){
        Optional<SpaceProbe> spaceprobe = repository.findById(idSpaceprobe);
        List<SpaceProbe> spaceProbeList = repository.findAll();
        if(repository.existsById(idSpaceprobe)){
            for (char coordinate : coordinates.toCharArray()) {
                switch (coordinate) {
                    case 'L':
                        spaceprobe.get().turnLeft();
                        this.repository.save(spaceprobe.get());
                        break;
                    case 'R':
                        spaceprobe.get().turnRight();
                        this.repository.save(spaceprobe.get());
                        break;
                    case 'M':
                        spaceprobe.get().makeMove();
                        for (SpaceProbe sp : spaceProbeList){
                            if(((spaceprobe.get().getPositionY() == sp.getPositionY()) && (spaceprobe.get().getPositionX() == sp.getPositionX())) && !sp.equals(spaceprobe.get())) {
                                throw new RuntimeException("Houston, we hava a problem: there was an error calculating the route, the new spaceship will crash into the spaceship of id %d\nPlease redirect it with a new coordinate!" + sp.getId());
                            }
                        }
                        this.repository.save(spaceprobe.get());
                        break;
                    default:
                        throw new RuntimeException("Invalid movement, allowed coordinates are: L, R and M.\nplease try again.");
                }
            }
            return ResponseEntity.ok().body(spaceprobe.get());
        } else{
            return notFound().build();
        }
    }
}
