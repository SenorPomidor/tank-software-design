package ru.mipt.bit.platformer.level.interfaces;

import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

public interface GameObjectListener {

    void onPlayerAdded(PlayerEntity playerEntity, String texture);
    void onObstacleAdded(ObstacleEntity obstacleEntity, String texture);
}
