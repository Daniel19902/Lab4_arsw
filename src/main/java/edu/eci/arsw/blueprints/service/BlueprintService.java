package edu.eci.arsw.blueprints.service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;

import java.util.Set;

public interface BlueprintService {

    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    public Set<Blueprint> getAllBlueprints()throws BlueprintNotFoundException;

    public Blueprint getBlueprint(String author,String name)throws BlueprintNotFoundException;

    public Blueprint getFiltrarBlueprint(String author,String name) throws BlueprintNotFoundException;

    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;

    public Set<Blueprint> getAllBlueprintsFiltro();

    void deleteBlueprint(String author, String name);
}
