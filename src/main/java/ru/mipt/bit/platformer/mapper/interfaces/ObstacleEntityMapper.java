package ru.mipt.bit.platformer.mapper.interfaces;

import org.awesome.ai.state.immovable.Obstacle;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;

public interface ObstacleEntityMapper {

    Obstacle mapToObstacle(ObstacleEntity entity);
}
