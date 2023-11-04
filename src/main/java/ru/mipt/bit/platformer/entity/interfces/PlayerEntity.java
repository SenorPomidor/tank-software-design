package ru.mipt.bit.platformer.entity.interfces;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.controller.interfaces.Action;
import ru.mipt.bit.platformer.entity.HealthBar;

import java.util.List;

public interface PlayerEntity extends GameEntity {

    float getMovementProgress();
    GridPoint2 getDestinationCoordinates();
    void setRotation(float rotation);
    float getRotation();
    
    void moveTo(GridPoint2 targetCoordinates);
    void updateState(float newMovementProgress);

    void updatePlayerMovement(Action action, List<GameEntity> gameEntities, float deltaTime);
    void shoot(Action action);
    boolean isShoot();
    void setRecharge();
    int getHealth();
    HealthBar getHealthBar();
}
