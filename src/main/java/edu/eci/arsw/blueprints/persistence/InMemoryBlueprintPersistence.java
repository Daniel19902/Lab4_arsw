package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>, Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() throws BlueprintPersistenceException{
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(140, 140),new Point(115, 115),new Point(140, 140),new Point(160, 140),new Point(140, 145)};
        Blueprint bp=new Blueprint("porras", "blueprint",pts);
        saveBlueprint(bp);

        Blueprint bp2=new Blueprint("porras", "blueprint1",pts);
        saveBlueprint(bp2);

        Blueprint bp3=new Blueprint("Jonathan", "blueprint2",pts);
        saveBlueprint(bp3);

        Blueprint bp4=new Blueprint("Jonathan", "blueprint3",pts);
        saveBlueprint(bp4);
    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint blueprint = blueprints.get(new Tuple<>(author, bprintname));
        if (blueprint != null){
            return  blueprint;
        }
        throw new BlueprintNotFoundException("Blueprint not found");
    }

    @Override
    public Set<Blueprint> getAllBlueprint(){
        Set<Blueprint> setBlueprint = new HashSet<>();
        for (Tuple<String,String> tp : blueprints.keySet()) {
            setBlueprint.add(blueprints.get(tp));
        }
        return setBlueprint;
    }

    @Override
    public void deleteBlueprint(String author, String name) {
        blueprints.remove(new Tuple<>(author, name));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> setBlueprint = new HashSet<>();
        for ( Tuple<String,String> tp : blueprints.keySet()){
            if (tp.o1.equals(author)){
                setBlueprint.add(blueprints.get(tp));
            }
        }
        if (!setBlueprint.isEmpty()) {
            return setBlueprint;
        }
        throw new BlueprintNotFoundException("Author not found");
    }
}
