package edu.fit.nao.module.mapping;

import edu.fit.nao.module.geometry.Position2D;

public class Landmark {

    private final int id;
    private final Position2D position;

    public Landmark(int id, Position2D position) {

        this.id = id;
        this.position = position;
    }

    public int getId() {

        return id;
    }

    public Position2D getPosition() {

        return position;
    }
}
