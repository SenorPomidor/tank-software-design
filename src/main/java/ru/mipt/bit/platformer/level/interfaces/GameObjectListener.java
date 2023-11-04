package ru.mipt.bit.platformer.level.interfaces;

import ru.mipt.bit.platformer.entity.Bullet;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

public interface GameObjectListener {

    void onPlayerAdded(PlayerEntity playerEntity, String texture);
    void onObstacleAdded(ObstacleEntity obstacleEntity, String texture);
    void onBulletAdded(Bullet bullet, String texture);
    void onBulletDelete(GameEntity gameEntity);
    void onPlayerDelete(PlayerEntity gameEntity);
    void onPlayerHeated(PlayerEntity playerEntity);
}
