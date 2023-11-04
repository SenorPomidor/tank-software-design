package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;

public class HealthBar implements GameEntity {

    private GridPoint2 currentCoordinates;
    private GridPoint2 destinationCoordinates;
    private float movementProgress;

    public HealthBar(GridPoint2 startCoordinates) {
        currentCoordinates = startCoordinates;
        destinationCoordinates = startCoordinates;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }

    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates;
    }

    public void setCurrentCoordinates(GridPoint2 currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return currentCoordinates;
    }
}
