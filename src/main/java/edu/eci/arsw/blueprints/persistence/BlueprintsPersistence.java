package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Collection;
import java.util.Set;

public interface BlueprintsPersistence {
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    /**
     *
     * @param author blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException;

    Set<Blueprint> getBlueprintsByAuthor (String author) throws  BlueprintNotFoundException;

    public Collection<Blueprint> getAllBlueprint();

    void deleteBlueprint( String author, String name);

}
