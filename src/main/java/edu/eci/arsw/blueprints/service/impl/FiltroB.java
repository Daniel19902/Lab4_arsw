package edu.eci.arsw.blueprints.service.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.service.Filtro;

import java.util.LinkedList;
import java.util.List;

public class FiltroB implements Filtro {

    @Override
    public Blueprint filtrar(Blueprint blueprint) {
        List<Point> points = new LinkedList<>();
        for (int i = 0; i < blueprint.getPoints().size(); i++){
            if(i%2 == 0){
                points.add(blueprint.getPoints().get(i));
            }
        }
        blueprint.setPoints(points);
        return blueprint;
    }

}
