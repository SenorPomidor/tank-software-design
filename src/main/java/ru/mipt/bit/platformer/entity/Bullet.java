package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.controller.DirectionKeyBoardAction;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.common.CommonVariables.MAX_X;
import static ru.mipt.bit.platformer.common.CommonVariables.MAX_Y;

public class Bullet implements GameEntity {

    private static final float MOVEMENT_COMPLETED = 1f;
    private static final int MOVEMENT_STARTED = 0;

    private final GridPoint2 currentCoordinates;
    private GridPoint2 destinationCoordinates;
    private float movementProgress;
    private float rotation = 0f;

    private boolean encountered = true;

    public Bullet(GridPoint2 startCoordinates, float rotation) {
        currentCoordinates = startCoordinates;
        this.rotation = rotation;
        destinationCoordinates = startCoordinates;
    }

    public void moveTo(GridPoint2 targetCoordinates) {
        destinationCoordinates = targetCoordinates;
        movementProgress = MOVEMENT_STARTED;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public float getRotation() {
        return rotation;
    }

    public void updateState(float newMovementProgress) {
        movementProgress = newMovementProgress;
        if (isEqual(movementProgress, MOVEMENT_COMPLETED)) {
            currentCoordinates.set(destinationCoordinates);
        }
    }

    // проверка на то, что пуля достигла края карты или врезалась в какой-либо объект
    public void updateBulletMovement(List<GameEntity> gameEntities) {
        if (isEqualMethod()) {
            GridPoint2 destinationCoordinates = DirectionKeyBoardAction.getValueByRotation(rotation).cpy().add(currentCoordinates);
            if (isOccupied(gameEntities, destinationCoordinates) && outOfBoundChecker(destinationCoordinates)) {
                moveTo(destinationCoordinates);
            }
        }
    }

    private boolean isEqualMethod() {
        return isEqual(movementProgress, 1f);
    }

    private boolean outOfBoundChecker(GridPoint2 destinationCoordinates) {
        encountered = destinationCoordinates.x < MAX_X && destinationCoordinates.y < MAX_Y &&
                destinationCoordinates.x >= 0 && destinationCoordinates.y >= 0;
        return encountered;
    }

    private boolean isOccupied(List<GameEntity> gameEntities, GridPoint2 destinationCoordinates) {
        Optional<GameEntity> anyObject = gameEntities.stream()
                .filter(object -> object.getCoordinates().equals(destinationCoordinates) ||
                        object.getCoordinates().equals(currentCoordinates))
                .findAny();

        if (anyObject.isPresent()) {
            GameEntity gameEntity = anyObject.get();
            if (gameEntity instanceof Tank) {
                Tank tank = (Tank) gameEntity;
                tank.setHealth(tank.getHealth() - 1);
            }
        }

        encountered = anyObject.isEmpty();

        return anyObject.isEmpty();
    }

    public GridPoint2 getCoordinates() {
        return currentCoordinates;
    }

    public boolean encountered() {
        return encountered;
    }

}
