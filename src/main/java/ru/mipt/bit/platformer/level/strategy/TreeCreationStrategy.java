package ru.mipt.bit.platformer.level.strategy;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.Tree;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.level.strategy.interfaces.EntityCreationStrategy;

public class TreeCreationStrategy implements EntityCreationStrategy<ObstacleEntity> {

    @Override
    public ObstacleEntity createEntity(GridPoint2 coordinates) {
        return new Tree(coordinates);
    }
}
