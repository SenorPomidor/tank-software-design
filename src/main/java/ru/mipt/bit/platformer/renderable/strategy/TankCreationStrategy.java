package ru.mipt.bit.platformer.renderable.strategy;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.renderable.PlayerRenderableImpl;
import ru.mipt.bit.platformer.renderable.interfaces.PlayerRenderable;
import ru.mipt.bit.platformer.renderable.strategy.interfaces.EntityCreationStrategy;

import static ru.mipt.bit.platformer.common.CommonVariables.TANK_IMAGE;

public class TankCreationStrategy implements EntityCreationStrategy<PlayerRenderable> {

    @Override
    public PlayerRenderable createEntity(GridPoint2 coordinates) {
        return new PlayerRenderableImpl(TANK_IMAGE, new Tank(coordinates));
    }
}
