package ru.mipt.bit.platformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.controller.ShootingAction;
import ru.mipt.bit.platformer.controller.interfaces.Action;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.controller.DirectionKeyBoardAction;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.common.CommonVariables.MAX_X;
import static ru.mipt.bit.platformer.common.CommonVariables.MAX_Y;

public class Tank implements PlayerEntity {

    private static final float MOVEMENT_COMPLETED = 1f;
    private static final int MOVEMENT_STARTED = 0;
    private static final int RECHARGE = 500;

    private int health = 3;

    private final GridPoint2 currentCoordinates;
    private GridPoint2 destinationCoordinates;
    private float movementProgress;
    private float rotation = 0f;

    public boolean isShoot = false;
    public Date rechargeEnd;

    public Tank(GridPoint2 startCoordinates) {
        currentCoordinates = startCoordinates;
        destinationCoordinates = startCoordinates;
    }

    @Override
    public void moveTo(GridPoint2 targetCoordinates) {
        destinationCoordinates = targetCoordinates;
        movementProgress = MOVEMENT_STARTED;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public void updateState(float newMovementProgress) {
        movementProgress = newMovementProgress;
        if (isEqual(movementProgress, MOVEMENT_COMPLETED)) {
            currentCoordinates.set(destinationCoordinates);
        }
    }

    @Override
    public void updatePlayerMovement(Action action, List<GameEntity> gameEntities, float deltaTime) {
        if (isEqualMethod() && action != null) {
            DirectionKeyBoardAction directionKeyBoardActionImpl = (DirectionKeyBoardAction) action;
            GridPoint2 destinationCoordinates = directionKeyBoardActionImpl.apply(currentCoordinates);
            if (isOccupied(gameEntities, destinationCoordinates) && outOfBoundChecker(destinationCoordinates)) {
                moveTo(destinationCoordinates);
            }
            setRotation(directionKeyBoardActionImpl.getRotation());
        }
    }

    @Override
    public void shoot(Action action) {
//        ShootingAction directionActionImpl = (ShootingAction) action;

        if (rechargeEnd == null) {
            rechargeEnd = new Date(Calendar.getInstance().getTime().getTime());
        }

        Date instant = Calendar.getInstance().getTime();

        if (rechargeEnd.getTime() <= instant.getTime() - RECHARGE && movementProgress == 1) {
            rechargeEnd = new Date(Calendar.getInstance().getTime().getTime() + RECHARGE);
            isShoot = true;
            System.out.println("Пиф-Паф");
        } else if (!isEqualMethod()) {
            System.out.println("Едем");
        } else {
            System.out.println("Перезарядка");
        }
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public float getMovementProgress() {
        return movementProgress;
    }

    @Override
    public GridPoint2 getCoordinates() {
        return currentCoordinates;
    }

    @Override
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public boolean isShoot() {
        return isShoot;
    }

    public void setRecharge() {
        isShoot = !isShoot;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    private boolean isEqualMethod() {
        return isEqual(movementProgress, 1f);
    }

    private boolean outOfBoundChecker(GridPoint2 destinationCoordinates) {
        return destinationCoordinates.x < MAX_X && destinationCoordinates.y < MAX_Y &&
                destinationCoordinates.x >= 0 && destinationCoordinates.y >= 0;
    }

    private boolean isOccupied(List<GameEntity> gameEntities, GridPoint2 destinationCoordinates) {
        Optional<GameEntity> anyObject = gameEntities.stream()
                .filter(object -> {
                    if (object instanceof PlayerEntity) {
                        PlayerEntity playerEntity = (PlayerEntity) object;
                        return playerEntity.getDestinationCoordinates().equals(destinationCoordinates);
                    }
                    return object.getCoordinates().equals(destinationCoordinates);
                })
                .findAny();
        return anyObject.isEmpty();
    }
}
