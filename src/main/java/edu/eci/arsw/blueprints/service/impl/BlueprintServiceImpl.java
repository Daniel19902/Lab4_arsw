package edu.eci.arsw.blueprints.service.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.service.BlueprintService;
import edu.eci.arsw.blueprints.service.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BlueprintServiceImpl implements BlueprintService {

    @Autowired
    private BlueprintsPersistence bpp;

    @Autowired
    private Filtro f;

    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    public Set<Blueprint> getAllBlueprints(){
        return (Set<Blueprint>) bpp.getAllBlueprint();
    }

    public Set<Blueprint> getAllBlueprintsFiltro(){
        Set<Blueprint> blueprintsFiltre = new HashSet<>();
        Set<Blueprint> blueprints = (Set<Blueprint>) bpp.getAllBlueprint();
        for (Blueprint bp : blueprints){
            blueprintsFiltre.add(f.filtrar(bp));
        }
        return blueprintsFiltre;
    }

    @Override
    public void deleteBlueprint(String author, String name) {
        bpp.deleteBlueprint(author, name);
    }

    /**
     *
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException {
        return bpp.getBlueprint(author,name);
    }

    public Blueprint getFiltrarBlueprint(String author,String name) throws BlueprintNotFoundException {
        Blueprint bp = getBlueprint(author,name);
        return f.filtrar(bp);
    }

    /**
     *
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        return bpp.getBlueprintsByAuthor(author);
    }
}
