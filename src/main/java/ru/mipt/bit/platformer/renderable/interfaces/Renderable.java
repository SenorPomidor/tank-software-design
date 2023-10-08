package ru.mipt.bit.platformer.renderable.interfaces;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface Renderable {

    void dispose();
    void drawTexture(Batch batch);

}
