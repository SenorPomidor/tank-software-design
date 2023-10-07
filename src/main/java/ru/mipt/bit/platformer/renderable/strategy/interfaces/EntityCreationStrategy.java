package ru.mipt.bit.platformer.renderable.strategy.interfaces;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.renderable.interfaces.Renderable;

public interface EntityCreationStrategy<T extends Renderable> {

    T createEntity(GridPoint2 coordinates);
}
