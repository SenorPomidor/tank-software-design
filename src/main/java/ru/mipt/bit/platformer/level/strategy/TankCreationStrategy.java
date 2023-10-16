package ru.mipt.bit.platformer.level.strategy;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.level.strategy.interfaces.EntityCreationStrategy;

public class TankCreationStrategy implements EntityCreationStrategy<PlayerEntity> {

    @Override
    public PlayerEntity createEntity(GridPoint2 coordinates) {
        return new Tank(coordinates);
    }
}
