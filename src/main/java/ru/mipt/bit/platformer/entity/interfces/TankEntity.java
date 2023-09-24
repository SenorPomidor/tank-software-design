package ru.mipt.bit.platformer.entity.interfces;

import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public interface TankEntity extends GameEntity {

    float getMovementProgress();
    GridPoint2 getCurrentCoordinates();
    GridPoint2 getDestinationCoordinates();
    void setRotation(float rotation);

    void moveTo(GridPoint2 tankTargetCoordinates);
    void updateMovementState(float deltaTime);

    default boolean isMoving() {
        return !isEqual(getMovementProgress(), 1f);
    }
}
