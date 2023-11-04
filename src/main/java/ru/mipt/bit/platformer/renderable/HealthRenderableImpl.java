package ru.mipt.bit.platformer.renderable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.entity.HealthBar;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.renderable.interfaces.MovingObjectRenderable;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class HealthRenderableImpl implements MovingObjectRenderable {

    private final HealthBar healthBar;
    private TextureRegion textureRegion;
    private Rectangle rectangle;
    private Texture texture;

    public HealthRenderableImpl(HealthBar healthBar, String textureString) {
        this.texture = new Texture(textureString);
        textureRegion = new TextureRegion(texture);
        rectangle = createBoundingRectangle(textureRegion);
        this.healthBar = healthBar;
    }

    public void setNewTexture(String newTexture) {
        this.texture = new Texture(newTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = createBoundingRectangle(textureRegion);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void drawTexture(Batch batch) {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, 0f);
    }

    @Override
    public void updateGameGraphics(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                healthBar.getCoordinates(),
                healthBar.getDestinationCoordinates(),
                healthBar.getMovementProgress()
        );
    }

    @Override
    public GameEntity getGameEntity() {
        return healthBar;
    }
}
