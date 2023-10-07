package ru.mipt.bit.platformer.renderable.interfaces;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;

public interface Renderable {

    void dispose();
    void drawTexture(Batch batch);
    GameEntity getGameEntity();

}
