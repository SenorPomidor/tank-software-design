package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;

public class Tree implements ObstacleEntity {

    private final GridPoint2 currentCoordinates;

    public Tree(GridPoint2 startCoordinates) {
        currentCoordinates = startCoordinates;
    }

    @Override
    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates;
    }

    @Override
    public float getRotation() {
        return 0f;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return currentCoordinates;
    }
}
