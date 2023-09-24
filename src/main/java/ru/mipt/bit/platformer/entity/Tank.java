package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.interfces.TankEntity;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank implements TankEntity {

    private static final float MOVEMENT_SPEED = 0.4f;
    private static final float MOVEMENT_COMPLETED = 1f;
    private static final int MOVEMENT_STARTED = 0;

    private final GridPoint2 currentCoordinates;
    private GridPoint2 destinationCoordinates;
    private float movementProgress;
    private float rotation = 0f;

    public Tank(GridPoint2 startCoordinates) {
        currentCoordinates = startCoordinates;
        destinationCoordinates = startCoordinates;
    }

    @Override
    public void moveTo(GridPoint2 tankTargetCoordinates) {
        destinationCoordinates = tankTargetCoordinates;
        movementProgress = MOVEMENT_STARTED;
    }

    @Override
    public void updateMovementState(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(movementProgress, MOVEMENT_COMPLETED)) {
            currentCoordinates.set(destinationCoordinates);
        }
    }

    @Override
    public float getMovementProgress() {
        return movementProgress;
    }

    @Override
    public GridPoint2 getCurrentCoordinates() {
        return currentCoordinates;
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return currentCoordinates;
    }
}
