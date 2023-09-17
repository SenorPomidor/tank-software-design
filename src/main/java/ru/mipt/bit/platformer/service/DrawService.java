package ru.mipt.bit.platformer.service;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.entity.GameObject;
import ru.mipt.bit.platformer.entity.Tank;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class DrawService {

    public void drawGameObject(Batch batch, GameObject gameObject) {
        float rotation = 0f;

        if (gameObject instanceof Tank) {
            rotation = ((Tank) gameObject).getRotation();
        }

        drawTextureRegionUnscaled(batch, gameObject.getGraphics(), gameObject.getRectangle(), rotation);
    }
}
