package ru.mipt.bit.platformer.entity.state;

import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class MediumState implements PlayerState {

    @Override
    public float move(PlayerEntity tank, float deltaTime) {
        return continueProgress(tank.getMovementProgress(), deltaTime, MOVEMENT_SPEED * 3 / 2);
    }

    @Override
    public void shoot(PlayerEntity tank) {
        tank.shoot();
    }
}
