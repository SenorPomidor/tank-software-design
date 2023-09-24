package ru.mipt.bit.platformer.renderable.interfaces;

import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.enums.Direction;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

public interface TankRenderable extends Renderable {

    void updateGameGraphics(TileMovement tileMovement);
    void updateTankState(Direction direction, List<GameEntity> gameFields, float deltaTime);

}
