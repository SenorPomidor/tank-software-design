package ru.mipt.bit.platformer.entity.state;

import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

public interface PlayerState {

    float MOVEMENT_SPEED = 0.4f;

    float move(PlayerEntity tank, float deltaTime);
    void shoot(PlayerEntity tank);

}
