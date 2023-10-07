package ru.mipt.bit.platformer.renderable.strategy;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.Tree;
import ru.mipt.bit.platformer.renderable.ObstacleRenderableImpl;
import ru.mipt.bit.platformer.renderable.interfaces.ObstacleRenderable;
import ru.mipt.bit.platformer.renderable.strategy.interfaces.EntityCreationStrategy;

import static ru.mipt.bit.platformer.common.CommonVariables.TREE_IMAGE;

public class TreeCreationStrategy implements EntityCreationStrategy<ObstacleRenderable> {

    @Override
    public ObstacleRenderable createEntity(GridPoint2 coordinates) {
        return new ObstacleRenderableImpl(TREE_IMAGE, new Tree(coordinates));
    }
}
