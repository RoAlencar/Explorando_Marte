package br.com.raxinformatica.explorandomarte.controllers;

import br.com.raxinformatica.explorandomarte.models.Planet;
import br.com.raxinformatica.explorandomarte.models.SpaceProbe;
import br.com.raxinformatica.explorandomarte.repositories.PlanetRepository;
import br.com.raxinformatica.explorandomarte.repositories.SpaceProbeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/spaceprobe")
public class SpaceProbeController {

    @Autowired
    private SpaceProbeRepository repository;

    @Autowired
    private PlanetRepository planetRepository;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Transactional
    public ResponseEntity crateSpaceprobe(@RequestBody SpaceProbe newSpaceProbe){
        Optional<Planet> planet = this.planetRepository.findById(newSpaceProbe.getPlanet().getId());
        if((newSpaceProbe.getPositionX() > planet.get().getX() || newSpaceProbe.getPositionX() < 0)
          || (newSpaceProbe.getPositionY() > planet.get().getY() || newSpaceProbe.getPositionY() < 0)) {
            return new ResponseEntity("Houston, we have a problem: the spaceprobe is positioned outside the limits of the planet! ", HttpStatus.BAD_REQUEST);
        } else {
            List<SpaceProbe> spaceProbes = this.repository.findAll();
            for (SpaceProbe sp : spaceProbes) {
                if((sp.getPositionX() == newSpaceProbe.getPositionX())
                    && (sp.getPositionY() == newSpaceProbe.getPositionY())){
                    return new ResponseEntity("Houston, we hava a problem: there was an error calculating the route, the new spaceprobewill crash into the spaceprobe of id %d\nPlease redirect it with a new coordinate!"+ sp.getId(),HttpStatus.BAD_REQUEST);
                }
            }
            this.repository.save(newSpaceProbe);
            return new ResponseEntity("Success: we can launch the new space immediately.\n That's one small step for man, one giant leap for mankind",HttpStatus.CREATED);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity readAllSpaceprobe(){
        List<SpaceProbe> spaceProbes = this.repository.findAll();
        if(spaceProbes.size() > 0) {
            return ok(spaceProbes);
        } else {
            return new ResponseEntity("Central command: No spaceprobes have been launched, the mission has not started yet, please wait for the probes to be released.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity readOneSpaceprobe(@PathVariable int id){
        Optional<SpaceProbe> querySpaceprobe = this.repository.findById(id);

        if(querySpaceprobe.isPresent()){
            return new ResponseEntity("Success: Houston, we were able to locate the selected spacecraft", HttpStatus.OK);
        } else {
            return new ResponseEntity("Command center: we were unable to contact the selected spacecraft, please verify that the identification number is correct.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value ="/{id}", produces = "application/json")
    @Transactional
    public ResponseEntity deleteSpaceprobe(@PathVariable int id){
        if(this.repository.existsById(id)){
            this.repository.existsById(id);
            return new ResponseEntity("Success: Command center, selected probe is returning home.\n" +
                    "Mission complete, my comrades",HttpStatus.OK);
        } else {
             return new ResponseEntity("Houston, can't find the selected probe, please verify that the identification number is correct.",HttpStatus.NOT_FOUND);
        }
    }


}
