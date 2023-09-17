package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;

public class Tank extends GameObject {

    private float rotation;
    private float movementProgress;
    private GridPoint2 destinationCoordinates;

    public Tank(String texturePath, GridPoint2 initialCoordinates) {
        super(texturePath);
        this.coordinates = initialCoordinates;
        this.destinationCoordinates = new GridPoint2(coordinates);
        this.movementProgress = 1f;
        this.rotation = 0f;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public void setMovementProgress(float movementProgress) {
        this.movementProgress = movementProgress;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }
}

