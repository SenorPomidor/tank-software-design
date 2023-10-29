package ru.mipt.bit.platformer.renderable.interfaces;

import ru.mipt.bit.platformer.util.TileMovement;

public interface MovingObjectRenderable extends Renderable {

    void updateGameGraphics(TileMovement tileMovement);

}
