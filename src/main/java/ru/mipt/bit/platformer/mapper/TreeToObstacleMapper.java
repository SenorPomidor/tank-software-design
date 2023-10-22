package ru.mipt.bit.platformer.mapper;

import org.awesome.ai.state.immovable.Obstacle;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.mapper.interfaces.ObstacleEntityMapper;

public class TreeToObstacleMapper implements ObstacleEntityMapper {

    @Override
    public Obstacle mapToObstacle(ObstacleEntity tree) {
        return new Obstacle(tree.getCoordinates().x, tree.getCoordinates().y);
    }
}
