package ru.mipt.bit.platformer.renderable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.renderable.interfaces.PlayerRenderable;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class PlayerRenderableImpl implements PlayerRenderable {

    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;
    private final PlayerEntity playerEntity;

    public PlayerRenderableImpl(PlayerEntity playerEntity, String fileNameTexture) {
        texture = new Texture(fileNameTexture);
        textureRegion = new TextureRegion(texture);
        rectangle = createBoundingRectangle(textureRegion);
        this.playerEntity = playerEntity;
    }

    @Override
    public void drawTexture(Batch batch) {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, playerEntity.getRotation());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void updateGameGraphics(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                playerEntity.getCoordinates(),
                playerEntity.getDestinationCoordinates(),
                playerEntity.getMovementProgress()
        );
    }

    @Override
    public PlayerEntity getGameEntity() {
        return playerEntity;
    }
}
