package ru.mipt.bit.platformer.level.strategy.interfaces;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;

public interface EntityCreationStrategy<T extends GameEntity> {

    T createEntity(GridPoint2 coordinates);
}
