/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;



import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.service.BlueprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 *
 * @author hcadavid
 */
@RequestMapping("/blueprints")
@RestController
public class BlueprintAPIController {
    @Autowired
    private BlueprintService bps;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllBlueprintFiltre(){
        return new ResponseEntity<>(bps.getAllBlueprintsFiltro(), HttpStatus.ACCEPTED) ;
    }

    @RequestMapping("/{author}")
    public ResponseEntity<?> getAuthor(@PathVariable String author){
        try {
            Set<Blueprint> blueprints = bps.getBlueprintsByAuthor(author);
            return  new ResponseEntity<>(blueprints, HttpStatus.ACCEPTED);
        }catch (BlueprintNotFoundException e){
            return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/{author}/{name}")
    public ResponseEntity<?> getAuthor(@PathVariable String author, @PathVariable String name){
        try {
            Blueprint blueprint = bps.getBlueprint(author, name);
            return new ResponseEntity<>(blueprint, HttpStatus.CREATED);
        }catch (BlueprintNotFoundException e){
            return new ResponseEntity<>("Blueprint not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addBlueprint(@RequestBody Blueprint blueprint){
        System.out.println(blueprint.toString());
        try {
            bps.addNewBlueprint(blueprint);
            return new ResponseEntity<>(blueprint, HttpStatus.CREATED);
        }catch (BlueprintPersistenceException e){
            return new ResponseEntity<>("Error al crear bluepirnt", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{author}/{name}")
    public ResponseEntity<?> updateBlueprint(@PathVariable String author, @PathVariable String name, @RequestBody Blueprint blueprint){
        bps.deleteBlueprint(author, name);
        try {
            bps.addNewBlueprint(blueprint);
            return new ResponseEntity<>(blueprint, HttpStatus.CREATED);
        }catch (BlueprintPersistenceException e){
            return new ResponseEntity<>("Error al actualizar",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

