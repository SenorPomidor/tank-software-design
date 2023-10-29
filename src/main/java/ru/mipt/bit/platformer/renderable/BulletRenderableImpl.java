package ru.mipt.bit.platformer.renderable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.entity.Bullet;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.renderable.interfaces.MovingObjectRenderable;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class BulletRenderableImpl implements MovingObjectRenderable {


    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final Bullet bullet;

    public BulletRenderableImpl(Bullet bullet, String fileNameTexture) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = createBoundingRectangle(textureRegion);
        this.bullet = bullet;
    }

    @Override
    public void drawTexture(Batch batch) {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, bullet.getRotation());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void updateGameGraphics(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                bullet.getCoordinates(),
                bullet.getDestinationCoordinates(),
                bullet.getMovementProgress()
        );
    }

    @Override
    public GameEntity getGameEntity() {
        return bullet;
    }

}